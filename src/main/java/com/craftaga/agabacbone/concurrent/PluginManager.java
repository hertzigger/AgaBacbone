package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.IPlayerNameResolver;
import com.craftaga.agabacbone.PlayerNameResolver;
import com.craftaga.agabacbone.concurrent.handlers.timer.ExampleTimerHandler;
import com.craftaga.agabacbone.concurrent.methods.TimerAddMethod;
import com.craftaga.agabacbone.concurrent.methods.TimerMethod;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import com.craftaga.agabacbone.persistence.entities.IServerPersistence;
import com.craftaga.agabacbone.persistence.entities.IWorldPersistence;
import com.craftaga.agabacbone.persistence.PersistenceManager;
import com.craftaga.agabacbone.persistence.entities.ServerPersistence;
import com.craftaga.agabacbone.listener.LoginListener;
import com.craftaga.agabacbone.persistence.entities.WorldPersistence;
import com.craftaga.agabacbone.session.ISessionHandler;
import com.craftaga.agabacbone.session.SessionHandler;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
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
    final private IPersistenceManager persistenceManager;


    private IWorldManager worldManager = new WorldManager();
    private static IPluginManager pluginManager;
    private DataSource dataSource;
    private int serverId;


    private PluginManager(JavaPlugin javaPlugin)
    {
        this.plugin = javaPlugin;
        ClassLoader cl = ServerPersistence.class.getClassLoader();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"hibernate-beans.xml"}, false);
        ctx.setClassLoader(cl);
        ctx.refresh();
        dataSource = (DataSource) ctx.getBean("mainDataSource");
        persistenceManager = new PersistenceManager(dataSource);
        IServerPersistence serverPersistence = persistenceManager.getServerPersistence();
        IWorldPersistence worldPersistence = persistenceManager.getWorldPersistence();
        try {
            if (!serverPersistence.serverExists(plugin.getServer().getServerName())) {
                serverPersistence.createServer(plugin.getServer().getServerName());
            }
            serverId = serverPersistence.getServerId(plugin.getServer().getServerName());
            worldManager = worldPersistence.addOrFetch(plugin.getServer().getWorlds(), serverId);
        } catch (SQLException e) {
            getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        }

        sessionHandler.setPluginManager(this);
        sessionHandler.setPlayerNameResolver(playerNameResolver);
        sessionHandler.setPersistenceManager(persistenceManager);
        sessionHandler.setWorldManager(worldManager);
        timerManager.addTimerHandler("example", new ExampleTimerHandler());
        IMethod timerMethod = new TimerMethod().setName("timer");
        IMethod timerAddMethod = new TimerAddMethod().setName("addSession");
        timerMethod.addMethod(timerAddMethod);
        sessionHandler.getCommandHandler().addMethod(timerMethod);
//        Collection<WorldEntity> worldEntities = worldPersistence.getWorldsForServerEntity(serverEntity);
//        for (org.bukkit.WorldPersistence world : getPlugin().getServer().getWorlds()) {
//            Boolean present = false;
//            for (WorldEntity worldEntity: worldEntities) {
//                if (worldEntity.getName().equals(world.getName())) {
//                    present = true;
//                }
//            }
//            if (!present) {
//                worldPersistence.createWorld(world.getName(), serverEntity);
//            }
//        }

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
    }

    public static void main(String[] args)
    {
    }

    @Override
    public void setupInstructions()
    {
        sessionHandler.setupInstructions();
    }
}
