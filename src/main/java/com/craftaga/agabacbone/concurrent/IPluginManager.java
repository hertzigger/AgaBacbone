package com.craftaga.agabacbone.concurrent;

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
    JavaPlugin getPlugin();

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
}
