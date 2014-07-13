package com.craftaga.agabacbone.listener;

import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.PluginManager;
import com.craftaga.agabacbone.session.IUserSession;
import com.craftaga.agabacbone.session.UserSession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public final class WorldListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChangeWorldEvent(final PlayerChangedWorldEvent playerChangedWorldEvent)
    {
        IPluginManager pluginManager = PluginManager.getInstance();
        IUserSession session = pluginManager.getSessionHandler().getUserSession(playerChangedWorldEvent.getPlayer());
        session.changeWorld(playerChangedWorldEvent.getPlayer().getWorld());
    }
}
