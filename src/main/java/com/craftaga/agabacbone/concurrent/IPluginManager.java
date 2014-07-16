package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.schedule.IGlobalScheduledTimerHandle;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import com.craftaga.agabacbone.session.ISessionHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * description
 *
 * @author Jonathan
 * @since 06/04/14
 */
public interface IPluginManager {

    void setPlugin(JavaPlugin plugin);

    boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args);

    void addToServerThread(Runnable runner);

    void sendServerInfoMessage(String message);

    ISessionHandler getSessionHandler();

    ITimerManager getTimerManager();

    void setupListeners();

    void setupInstructions();

    IPersistenceManager getPersistenceManager();

    JavaPlugin getPlugin();

    void scheduleTimerHandlerAtFixedRate(IGlobalScheduledTimerHandle globalScheduledTimerHandle);

    void removeScheduledHandle(IGlobalScheduledTimerHandle globalScheduledTimerHandle);

    void executeQueue(CommandQueue commandQueue);
}
