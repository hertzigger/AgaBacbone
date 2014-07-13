package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 25/03/14
 */
public interface IUserSession extends ICommandQueueScheduler {
    Player getUser();

    void executeQueue(CommandQueue commandQueue);

    ISessionHandler getSessionHandler();

    void setSessionHandler(ISessionHandler sessionHandler);

    Object getState(String name);

    void setState(String name, Object state);

    IPersistenceManager getPersistenceManager();

    void setPersistenceManager(IPersistenceManager persistenceManager);

    void startSession();

    void setUser(Player user);

    TaskExecutor getTaskExecutor();

    void setTaskExecutor(TaskExecutor taskExecutor);

    ThreadPoolTaskScheduler getThreadPoolTaskScheduler();

    void setThreadPoolTaskScheduler(ThreadPoolTaskScheduler threadPoolTaskScheduler);

    void close();

    int getSessionId();

    void setSessionId(int sessionId);

    int getUsernameId();

    UUID getUserId();

    void changeWorld(World world);

    int createSnapshot();

    ClassPathXmlApplicationContext getContext();

    void setContext(ClassPathXmlApplicationContext context);
}
