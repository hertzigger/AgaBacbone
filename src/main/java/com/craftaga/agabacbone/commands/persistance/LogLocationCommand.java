package com.craftaga.agabacbone.commands.persistance;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.persistence.entities.ILocationPersistence;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.Location;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Atomic method to log a current Location to database
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class LogLocationCommand extends Command {
    private IValueHolderCommand<Location> locationHolder;
    private ILocationPersistence locationPersistence;
    private IValueHolderCommand<Integer> sessionId;
    public LogLocationCommand(
            final CommandQueue commandQueue,
            final IValueHolderCommand<Location> location,
            final ILocationPersistence locationPersistence,
            final IValueHolderCommand<Integer> sessionId
    ) {
        super(commandQueue);
        this.locationHolder = location;
        this.locationPersistence = locationPersistence;
        this.sessionId = sessionId;
    }

    @Override
    public void execute()
    {
        Location location = locationHolder.getValue();
        try {
            locationPersistence.addLocation(
                    location.getX(),
                    location.getY(),
                    location.getZ(),
                    location.getPitch(),
                    location.getYaw(),
                    sessionId.getValue());
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }  catch (IOException e) {
            getDefaultCommandQueue().getPlugin().getPlugin().getLogger().info(ExceptionUtils.getStackTrace(e));
        }
    }
}
