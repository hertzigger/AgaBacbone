package com.craftaga.agabacbone.concurrent.handlers.session;

import com.craftaga.agabacbone.commands.IValueHolderCommand;
import com.craftaga.agabacbone.commands.persistance.CreateInventory;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.PlayerQueueConstructor;
import com.craftaga.agabacbone.session.UserSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class CreateSnapshotHandler extends PlayerQueueConstructor {
    private UserSession sessionToSnapshot;
    public CreateSnapshotHandler(ClassPathXmlApplicationContext context, UserSession sessionToSnapshot) {
        super(context);
        this.sessionToSnapshot = sessionToSnapshot;
    }

    @Override
    public CommandQueue getCommandQueue() {
        CommandQueue commandQueue = new CommandQueue(getUserSession().getSessionHandler().getPluginManager());

        IValueHolderCommand<Integer> inventory = new CreateInventory(
                commandQueue,
                getUserSession().getPersistenceManager().getInventoryPersistence()
        );

        commandQueue.addCommand(inventory);

        return commandQueue;
    }
}
