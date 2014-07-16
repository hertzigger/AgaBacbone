package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.schedule.IPlayerCommandQueueScheduler;
import com.craftaga.agabacbone.concurrent.schedule.IPlayerQueueConstructor;
import com.craftaga.agabacbone.concurrent.schedule.IPlayerScheduledTimerHandler;
import com.craftaga.agabacbone.concurrent.handlers.session.CreateSnapshotHandler;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Stores the current state of the user and the past commands used
 *
 * @author Jonathan
 * @since 25/03/14
 */
public class UserSession implements IUserSession, IPlayerCommandQueueScheduler {
    private TaskExecutor taskExecutor;
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private Player user;
    private ISessionHandler sessionHandler;
    private IPersistenceManager persistenceManager;
    final private HashMap<IPlayerScheduledTimerHandler, ScheduledFuture> scheduledFutureHashMap = new HashMap<IPlayerScheduledTimerHandler, ScheduledFuture>();
    final private ConcurrentHashMap<String, Object> states = new ConcurrentHashMap<String, Object>();

    private int sessionId;
    private int usernameId;
    private UUID userId;

    private ClassPathXmlApplicationContext context;

    @Override
    public ClassPathXmlApplicationContext getContext() {
        return context;
    }

    @Override
    public void setContext(ClassPathXmlApplicationContext context) {
        this.context = context;
    }

    @Override
    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    @Override
    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @Override
    public ThreadPoolTaskScheduler getThreadPoolTaskScheduler() {
        return threadPoolTaskScheduler;
    }

    @Override
    public void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
    }

    @Override
    public void close() {
        try {
            persistenceManager.getSessionPersistence().logout(getSessionId());
            persistenceManager.getUserPersistence().setLogout(getUserId());
            persistenceManager.getUsernamePersistence().setLogout(getUsernameId());
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            getSessionHandler().getPluginManager().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        }
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
    public void startSession() {
        int ipId = 0;
        try {
            ipId = persistenceManager.getIpAddressPersistence().addOrFetch(getUser().getAddress().getAddress());
            userId = persistenceManager.getUserPersistence().login(getUser().getUniqueId());
            persistenceManager.getUserHasIpAddressPersistence().addOrFetch(ipId, userId);
            usernameId = persistenceManager.getUsernamePersistence().addOrFetch(userId, getUser().getName());
            setSessionId(persistenceManager.getSessionPersistence().addSession(
                    new Date(), getSessionHandler().getWorldManager().getWorldId(getUser().getWorld())
            ));
            persistenceManager.getUsernameHasSessionPersistence().add(usernameId, sessionId);
        } catch (SQLException e) {
             getSessionHandler().getPluginManager().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            getSessionHandler().getPluginManager().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public ISessionHandler getSessionHandler() {
        return sessionHandler;
    }

    @Override
    public void setSessionHandler(ISessionHandler sessionHandler) {
        this.sessionHandler = sessionHandler;
    }

    @Override
    public Player getUser() {
        return user;
    }

    @Override
    public void setUser(Player user) {
        this.user = user;
    }

    @Override
    public void executeQueue(CommandQueue commandQueue)
    {
        taskExecutor.execute(commandQueue);
    }

    @Override
    public void scheduleTimerHandlerAtFixedRate(IPlayerScheduledTimerHandler scheduledTimerHandler)
    {
        scheduledTimerHandler.getTimerHandler().setUserSession(this);
        scheduledFutureHashMap.put(scheduledTimerHandler,
                threadPoolTaskScheduler.scheduleAtFixedRate(
                        scheduledTimerHandler.getTimerHandler().getCommandQueue(),
                        scheduledTimerHandler.getInterval()
                ));
    }

    @Override
    public void removeScheduledHandle(IPlayerScheduledTimerHandler scheduledTimerHandler)
    {
        scheduledFutureHashMap.get(scheduledTimerHandler).cancel(false);
    }

    @Override
    public void removeAllScheduledJobs()
    {
        for (Map.Entry<IPlayerScheduledTimerHandler, ScheduledFuture> entry : scheduledFutureHashMap.entrySet()) {
            entry.getValue().cancel(false);
        }
    }

    @Override
    public synchronized void setState(String name, Object state)
    {
        states.put(name, state);
    }

    @Override
    public Object getState(String name)
    {
        if(states.containsKey(name)) {
            return states.get(name);
        }
        return null;
    }

    public void immediatelyEndAllScheduledJobs()
    {
        for (Map.Entry<IPlayerScheduledTimerHandler, ScheduledFuture> entry : scheduledFutureHashMap.entrySet()) {
            entry.getValue().cancel(true);
        }
    }

    @Override
    public int getSessionId() {
        return sessionId;
    }

    @Override
    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public int getUsernameId() {
        return usernameId;
    }

    @Override
    public UUID getUserId() {
        return userId;
    }

    @Override
    public void changeWorld(World world) {
        try {
            persistenceManager.getSessionPersistence().logout(getSessionId());
            setSessionId(persistenceManager.getSessionPersistence().addSession(
                    new Date(), getSessionHandler().getWorldManager().getWorldId(world)
            ));
            persistenceManager.getUsernameHasSessionPersistence().add(getUsernameId(), getSessionId());
        } catch (SQLException e) {
            getSessionHandler().getPluginManager().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        } catch (IOException e) {
            getSessionHandler().getPluginManager().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        }
    }

    @Override
    public int createSnapshot()
    {
        IPlayerQueueConstructor playerQueueConstructor = new CreateSnapshotHandler(getContext(), this);
        playerQueueConstructor.setUserSession(this);
        executeQueue(playerQueueConstructor.getCommandQueue());
        return 0;
    }
}
