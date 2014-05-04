package com.craftaga.agabacbone.listener;

import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.PluginManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;


/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public final class LoginListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogin(PlayerLoginEvent playerLoginEvent)
    {
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent)
    {
        IPluginManager pluginManager = PluginManager.getInstance();
        pluginManager.getSessionHandler().addUserSession(playerJoinEvent.getPlayer());
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onLogout(PlayerQuitEvent playerQuitEvent)
    {
        IPluginManager pluginManager = PluginManager.getInstance();
        pluginManager.getSessionHandler().removeUserSession(playerQuitEvent.getPlayer());
    }
}
