package com.craftaga.agabacbone;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * description
 *
 * @author Jonathan
 * @since 10/03/14
 */
public interface IAgaSender {
    Player getPlayer();
    CommandQueue getLastRunCommand();
    List<CommandQueue> getRunCommands();
}
