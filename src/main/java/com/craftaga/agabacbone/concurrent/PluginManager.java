package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.IPlayerNameResolver;
import com.craftaga.agabacbone.PlayerNameResolver;
import com.craftaga.agabacbone.concurrent.handlers.timer.ExampleTimerHandler;
import com.craftaga.agabacbone.concurrent.handlers.timer.LocationLoggerTimer;
import com.craftaga.agabacbone.concurrent.methods.TimerAddMethod;
import com.craftaga.agabacbone.concurrent.methods.TimerMethod;
import com.craftaga.agabacbone.listener.LoggingEventListener;
import com.craftaga.agabacbone.listener.WorldListener;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.craftaga.agabacbone.persistence.entities.IServerPersistence;
import com.craftaga.agabacbone.persistence.entities.IWorldPersistence;
import com.craftaga.agabacbone.persistence.PersistenceManager;
import com.craftaga.agabacbone.persistence.entities.ServerPersistence;
import com.craftaga.agabacbone.listener.LoginListener;
import com.craftaga.agabacbone.persistence.entities.WorldPersistence;
import com.craftaga.agabacbone.session.ISessionHandler;
import com.craftaga.agabacbone.session.ScheduledTimerHandler;
import com.craftaga.agabacbone.session.SessionHandler;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.FileUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.reader.StreamReader;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URL;
import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 09/11/13
 */
@Service
public class PluginManager implements IPluginManager {
    private JavaPlugin plugin;
    final private ISessionHandler sessionHandler = new SessionHandler();
    final private ITimerManager timerManager = new TimerManager();
    final private IPlayerNameResolver playerNameResolver = new PlayerNameResolver();
    private IPersistenceManager persistenceManager;


    private IWorldManager worldManager = new WorldManager();
    private static IPluginManager pluginManager;
    private BoneCPDataSource dataSource;
    private int serverId;
    private ClassPathXmlApplicationContext context;


    private PluginManager(JavaPlugin javaPlugin)
    {
        this.plugin = javaPlugin;
        if (!new File(getPlugin().getDataFolder().toString() + "/config.yml").exists()) {
            firstRun();
        } else {
            ClassLoader cl = ServerPersistence.class.getClassLoader();
            context = new ClassPathXmlApplicationContext(new String[]{"hibernate-beans.xml"}, false);
            context.setClassLoader(cl);
            context.refresh();
            boolean databaseReady = true;
            dataSource = (BoneCPDataSource) context.getBean("mainDataSource");
            dataSource.setPassword(plugin.getConfig().getConfigurationSection("database").getString("password"));
            dataSource.setUsername(plugin.getConfig().getConfigurationSection("database").getString("username"));
            if(!plugin.getConfig().getConfigurationSection("database").getBoolean("created")) {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                URL setupUrl = loader.getResource("setup.sql");
                try {
                    dataSource.getConnection().prepareStatement(IOUtils.toString(plugin.getResource("setup.sql"), "UTF-8"));
                } catch (SQLException e) {
                    e.printStackTrace();
                    databaseReady = false;
                } catch (IOException e) {
                    e.printStackTrace();
                    databaseReady = false;
                }
            }
            if (databaseReady) {
                persistenceManager = new PersistenceManager();
                persistenceManager.setDataSource(dataSource);
                persistenceManager.setPlugin(this);
                persistenceManager.setup();
                IServerPersistence serverPersistence = persistenceManager.getServerPersistence();
                IWorldPersistence worldPersistence = persistenceManager.getWorldPersistence();
                try {
                    if (!serverPersistence.serverExists(plugin.getServer().getServerName())) {
                        serverPersistence.createServer(plugin.getServer().getServerName());
                    }
                    serverId = serverPersistence.getServerId(plugin.getServer().getServerName());
                    worldManager = worldPersistence.addOrFetch(plugin.getServer().getWorlds(), serverId);
                } catch (SQLException e) {
                    getPlugin().getLogger().info(e.getMessage());
                    getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
                } catch (IOException e) {
                    getPlugin().getLogger().info(e.getMessage());
                    getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
                }

                sessionHandler.setPluginManager(this);
                sessionHandler.setPlayerNameResolver(playerNameResolver);
                sessionHandler.setPersistenceManager(persistenceManager);
                sessionHandler.setWorldManager(worldManager);
                sessionHandler.setContext(context);
                timerManager.addTimerHandler("example", new ExampleTimerHandler(context));
                IMethod timerMethod = new TimerMethod().setName("timer");
                IMethod timerAddMethod = new TimerAddMethod().setName("addSession");
                timerMethod.addMethod(timerAddMethod);
                sessionHandler.getCommandHandler().addMethod(timerMethod);
                sessionHandler.scheduleTimerHandlerAtFixedRate(new ScheduledTimerHandler(new LocationLoggerTimer(context), 5000));
            }
            else
            {
                getPlugin().getLogger().info("Database initialisation failed. AgaBacbone Not running");
            }
        }
    }

    private void firstRun()
    {
        getPlugin().saveDefaultConfig();
        getPlugin().getLogger().info("Defailt Configuration has been created in " + plugin.getDataFolder() +
                " please update and reload the plugin");
    }

    public synchronized static IPluginManager getInstance(JavaPlugin javaPlugin)
    {
        if (pluginManager == null) {
            pluginManager = new PluginManager(javaPlugin);
        }
        return pluginManager;
    }

    public synchronized static IPluginManager getInstance()
    {
        return pluginManager;
    }

    @Override
    public ISessionHandler getSessionHandler() {
        return sessionHandler;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }

    @Override
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(
            final CommandSender sender,
            final Command command,
            final String label,
            final String[] args)
    {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            return sessionHandler.onCommand(player, command, label, args);
        }
        return false;
    }

    @Override
    public void addToServerThread(final Runnable runner)
    {
        this.plugin.getServer().getScheduler().runTask(this.plugin, runner);
    }

    @Override
    public void sendServerInfoMessage(final String message)
    {
        this.plugin.getLogger().info(message);
    }

    @Override
    public ITimerManager getTimerManager()
    {
        return timerManager;
    }

    @Override
    public void setupListeners()
    {
        getPlugin().getServer().getPluginManager().registerEvents(new LoginListener(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new WorldListener(), getPlugin());
        getPlugin().getServer().getPluginManager().registerEvents(new LoggingEventListener(), getPlugin());

    }

    public static void main(String[] args)
    {
    }

    @Override
    public void setupInstructions()
    {
        sessionHandler.setupInstructions();
    }

    @Override
    public IPersistenceManager getPersistenceManager() {
        return persistenceManager;
    }
}
