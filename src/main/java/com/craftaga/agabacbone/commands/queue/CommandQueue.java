package com.craftaga.agabacbone.commands.queue;

import com.craftaga.agabacbone.commands.ICommand;
import com.craftaga.agabacbone.concurrent.IPluginManager;
import org.bukkit.command.CommandSender;
import org.springframework.core.task.TaskExecutor;

import java.util.LinkedList;

/**
 * description
 *
 * @author Jonathan
 * @since 08/11/13
 */
public class CommandQueue implements Runnable {

    protected LinkedList<ICommand> commandQueue;
    private TaskExecutor executor;
    private IPluginManager plugin;
    private CommandSender sender;

    public CommandQueue(final TaskExecutor executor, final IPluginManager plugin)
    {
        this(plugin);
        this.executor = executor;
    }

    public CommandQueue(final IPluginManager plugin)
    {
        this();
        this.plugin = plugin;
    }

    public CommandQueue()
    {
        commandQueue = new LinkedList<ICommand>();
    }

    @Override
    public void run() {
        for (ICommand command : commandQueue) {
            command.execute();
        }
    }

    public void execute()
    {
        this.executor.execute(this);
    }

    public IPluginManager getPlugin() {
        return plugin;
    }

    public void setPlugin(final IPluginManager plugin) {
        this.plugin = plugin;
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

    public CommandSender getSender() {
        return sender;
    }

    public void setSender(CommandSender sender) {
        this.sender = sender;
    }

    public void addToFront(ICommand msg) {
        this.commandQueue.addFirst(msg);
    }
}
