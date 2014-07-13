package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.IPlayerNameResolver;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.IMethod;
import com.craftaga.agabacbone.concurrent.IWorldManager;
import com.craftaga.agabacbone.concurrent.methods.ParentMethod;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author Jonathan
 * @since 25/03/14
 */
public class SessionHandler implements ISessionHandler {

    final private IMethod commandHandler = new ParentMethod();
    final private ConcurrentHashMap<UUID, IUserSession> userSessionHashMap = new ConcurrentHashMap<>();
    final private List<IScheduledTimerHandler> scheduledCommandQueueList = new ArrayList<IScheduledTimerHandler>();
    private static final TaskExecutor taskExecutor;
    private static final ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private IPlayerNameResolver playerNameResolver;

    private IPluginManager pluginManager;
    private IPersistenceManager persistenceManager;
    private IWorldManager worldManager;
    private ClassPathXmlApplicationContext context;

    static {
        ClassLoader cl = UserSession.class.getClassLoader();
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(new String[]{"hibernate-beans.xml"}, false);
        ctx.setClassLoader(cl);
        ctx.refresh();
        taskExecutor = (TaskExecutor) ctx.getBean("taskExecutor");
        threadPoolTaskScheduler = (ThreadPoolTaskScheduler) ctx.getBean("threadPoolTaskScheduler");
    }

    @Override
    public IPersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    @Override
    public void setPersistenceManager(IPersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    @Override
    public IWorldManager getWorldManager() {
        return worldManager;
    }

    @Override
    public void setContext(ClassPathXmlApplicationContext context) {
        this.context = context;
    }

    @Override
    public void setWorldManager(IWorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public IMethod getCommandHandler() {
        return commandHandler;
    }

    private CommandSender commandSender;

    @Override
    public CommandSender getCommandSender() {
        return commandSender;
    }

    @Override
    public void setCommandSender(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    @Override
    public void setupInstructions()
    {
    }

    @Override
    public IPlayerNameResolver getPlayerNameResolver() {
        return playerNameResolver;
    }

    @Override
    public void setPlayerNameResolver(IPlayerNameResolver playerNameResolver) {
        this.playerNameResolver = playerNameResolver;
    }

    @Override
    public boolean onCommand(
            final Player player,
            final Command command,
            final String label,
            final String[] args)
    {
        String[] allArgs = new String[args.length + 1];
        allArgs[0] = command.getName();
        int i = 1;
        for (String s : args) {
            allArgs[i] = s;
            i++;
        }
        IUserSession userSession = null;
        if (!userSessionHashMap.containsKey(player.getUniqueId())) {
            addUserSession(player);
        } else {
            userSession = userSessionHashMap.get(player.getUniqueId());
        }
        CommandQueue cmdQueue = commandHandler.getCommandQueue(allArgs, userSession);
        if (cmdQueue != null) {
            cmdQueue.setPlugin(pluginManager);
            cmdQueue.setSender(player);
            userSession.executeQueue(cmdQueue);
            return true;
        }
        return false;
    }

    @Override
    public IPluginManager getPluginManager() {
        return pluginManager;
    }

    @Override
    public void setPluginManager(IPluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    @Override
    public boolean checkPlayerHasSession(Player player)
    {
        return userSessionHashMap.containsKey(player.getUniqueId());
    }

    @Override
    public boolean checkPlayerHasSession(String player) {
        return userSessionHashMap.containsKey(playerNameResolver.getUniqueId(player));
    }

    @Override
    public IUserSession getUserSession(Player player)
    {
        if (userSessionHashMap.containsKey(player.getUniqueId())) {
            return userSessionHashMap.get(player.getUniqueId());
        } else {
            return null;
        }
    }

    @Override
    public IUserSession getUserSession(String playerName)
    {
        UUID userUUID = playerNameResolver.getUniqueId(playerName);
        if (userSessionHashMap.containsKey(userUUID)) {
            return userSessionHashMap.get(userUUID);
        } else {
            return null;
        }
    }

    @Override
    public void scheduleTimerHandlerAtFixedRate(IScheduledTimerHandler scheduledTimerHandler) {
        for (Map.Entry<UUID, IUserSession> entry : userSessionHashMap.entrySet()) {
            if ((entry.getValue().getUser() instanceof Player)) {
                entry.getValue().scheduleTimerHandlerAtFixedRate(scheduledTimerHandler);
            }
        }
        scheduledCommandQueueList.add(scheduledTimerHandler);
    }

    @Override
    public void removeScheduledHandle(IScheduledTimerHandler scheduledTimerHandler) {
        for (Map.Entry<UUID, IUserSession> entry : userSessionHashMap.entrySet()) {
            IUserSession session = entry.getValue();
            session.removeScheduledHandle(scheduledTimerHandler);
        }
    }

    @Override
    public void removeAllScheduledJobs() {
        for (Map.Entry<UUID, IUserSession> entry : userSessionHashMap.entrySet()) {
            IUserSession session = entry.getValue();
            session.removeAllScheduledJobs();
        }
    }

    @Override
    public void immediatelyEndAllScheduledJobs()
    {
        for (Map.Entry<UUID, IUserSession> entry : userSessionHashMap.entrySet()) {
            IUserSession session = entry.getValue();
            session.removeAllScheduledJobs();
        }
    }

    @Override
    public void addUserSession(Player player)
    {
        if (!userSessionHashMap.containsValue(player.getUniqueId())) {
            IUserSession userSession = new UserSession();
            userSession.setSessionHandler(this);
            userSession.setContext(context);
            userSession.setTaskExecutor(taskExecutor);
            userSession.setThreadPoolTaskScheduler(threadPoolTaskScheduler);
            userSession.setUser(player);
            userSession.setPersistenceManager(persistenceManager);
            userSession.startSession();
            userSession.createSnapshot();
            userSessionHashMap.put(player.getUniqueId(), userSession);
            for (IScheduledTimerHandler scheduledTimerHandler : scheduledCommandQueueList) {
                userSession.scheduleTimerHandlerAtFixedRate(scheduledTimerHandler);
            }
        }
    }

    @Override
    public void removeUserSession(Player player)
    {
        if (userSessionHashMap.containsKey(player.getUniqueId())) {
            IUserSession session = userSessionHashMap.get(player.getUniqueId());
            session.removeAllScheduledJobs();
            session.close();
            userSessionHashMap.remove(player.getUniqueId());
        }
    }


}
