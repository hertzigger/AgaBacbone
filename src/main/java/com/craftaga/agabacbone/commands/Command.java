package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * description
 *
 * @author Jonathan
 * @since 09/11/13
 */
@Configurable
public abstract class Command implements ICommand
{
    protected CommandQueue defaultCommandQueue;

    public Command(final CommandQueue commandQueue)
    {
        this.defaultCommandQueue = commandQueue;
    }

    public CommandQueue getDefaultCommandQueue() {
        return defaultCommandQueue;
    }

    public void setDefaultCommandQueue(final CommandQueue defaultCommandQueue) {
        this.defaultCommandQueue = defaultCommandQueue;
    }

    protected void addToServerThread(final Runnable runner)
    {
        this.getDefaultCommandQueue().getPlugin().addToServerThread(runner);
    }
}
