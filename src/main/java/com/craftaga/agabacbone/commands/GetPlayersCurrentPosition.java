package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class GetPlayersCurrentPosition extends Command implements IValueHolderCommand<Location> {
    private Player player;
    private Location location;

    public GetPlayersCurrentPosition(final CommandQueue commandQueue, final Player player) {
        super(commandQueue);
        this.player = player;
    }

    @Override
    public void setValue(Location location) {
        this.location = location;
    }

    @Override
    public Location getValue() {
        return location;
    }

    @Override
    public void execute() {
        setValue(player.getLocation());
    }
}
