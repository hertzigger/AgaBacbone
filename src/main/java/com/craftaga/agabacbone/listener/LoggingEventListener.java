package com.craftaga.agabacbone.listener;

import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.PluginManager;
import com.craftaga.agabacbone.session.IUserSession;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public final class LoggingEventListener implements Listener {

//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onPlayerIssueInstructionEvent(final PlayerCommandPreprocessEvent playerCommandPreprocessEvent)
//    {
//        IPluginManager pluginManager = PluginManager.getInstance();
//        IUserSession session = pluginManager.getSessionHandler().getUserSession(playerCommandPreprocessEvent.getPlayer());
//        pluginManager.getPersistenceManager().getInstructionPersistence().add(
//                playerCommandPreprocessEvent.getMessage(),
//                session.getSessionId()
//        );
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onPlayerKillEvent(final PlayerDeathEvent playerDeathEvent)
//    {
//        Player killer = playerDeathEvent.getEntity().getKiller();
//        Player victim = playerDeathEvent.getEntity();
//        if (killer != null) {
//            IPluginManager pluginManager = PluginManager.getInstance();
//            IUserSession kSession = pluginManager.getSessionHandler().getUserSession(killer);
//            IUserSession vSession = pluginManager.getSessionHandler().
//            pluginManager.getPersistenceManager().getKillPersistence().add(session.getSessionId(), pKiller.get)
//        }
//    }
//
//    @EventHandler(priority = EventPriority.MONITOR)
//    public void onPlayerChatEvent(final AsyncPlayerChatEvent asyncPlayerChatEvent)
//    {
//
//    }
}
