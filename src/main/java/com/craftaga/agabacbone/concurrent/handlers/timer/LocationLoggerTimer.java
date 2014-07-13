package com.craftaga.agabacbone.concurrent.handlers.timer;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.GetPlayersCurrentPosition;
import com.craftaga.agabacbone.commands.GetSessionId;
import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.persistance.LogLocationCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.PlayerQueueConstructor;
import org.bukkit.Location;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class LocationLoggerTimer extends PlayerQueueConstructor {

    public LocationLoggerTimer(ClassPathXmlApplicationContext context) {
        super(context);
    }

    @Override
    public CommandQueue getCommandQueue() {
        CommandQueue commandQueue = new CommandQueue(getUserSession().getSessionHandler().getPluginManager());

        IValueHolderCommand<Integer> getSession = new GetSessionId(
                commandQueue,
                getUserSession()
        );

        IValueHolderCommand<Location> getLocation = new GetPlayersCurrentPosition (
                commandQueue,
                getUserSession().getUser()
        );

        Command locationLog = new LogLocationCommand(
                commandQueue,
                getLocation,
                getUserSession().getPersistenceManager().getLocationPersistence(),
                getSession
        );

        commandQueue.addCommand(getLocation);
        commandQueue.addCommand(getSession);
        commandQueue.addCommand(locationLog);

        return commandQueue;
    }
}
