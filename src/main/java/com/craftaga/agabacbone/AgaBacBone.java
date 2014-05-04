package com.craftaga.agabacbone;

import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.PluginManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Entry point for the plugin
 *
 * @author Jonathan
 * @since 08/11/13
 */
public class AgaBacBone extends JavaPlugin {

    public static final int THREAD_COUNT = 30;
    private IPluginManager pluginManager;

    public void onEnable()
    {
        getLogger().info("agabacbone successfully loaded");
        pluginManager = PluginManager.getInstance(this);
        pluginManager.setPlugin(this);
        pluginManager.setupListeners();
        pluginManager.setupInstructions();

    }

    @Override
    public boolean onCommand(
            final CommandSender sender,
            final Command command,
            final String label,
            final String[] args)
    {
        return this.pluginManager.onCommand(sender, command, label, args);
    }

    @Override
    public void onDisable() {
        pluginManager.getSessionHandler().immediatelyEndAllScheduledJobs();
    }
}
