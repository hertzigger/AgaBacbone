package com.craftaga.agabacbone.commands.queue;

import com.craftaga.agabacbone.commands.ICommand;

import java.util.LinkedList;

/**
 * description
 *
 * @author Jonathan
 * @since 10/11/13
 */
public class SubQueue {
    protected LinkedList<ICommand> commandQueue;

    public SubQueue()
    {
        commandQueue = new LinkedList<ICommand>();
    }

    public void execute()
    {
        for (ICommand command : commandQueue) {
            command.execute();
        }
    }

    public void addCommand(ICommand command)
    {
        this.commandQueue.add(command);
    }

    public LinkedList<ICommand> getCommandQueue() {
        return commandQueue;
    }

    public void setCommandQueue(LinkedList<ICommand> commandQueue) {
        this.commandQueue = commandQueue;
    }
}
