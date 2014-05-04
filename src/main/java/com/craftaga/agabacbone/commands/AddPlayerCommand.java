package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 15/12/13
 */
public class AddPlayerCommand extends Command {
    private String username;

    public AddPlayerCommand(CommandQueue commandQueue, String username)
    {
        super(commandQueue);
        this.username = username;
    }
    @Override
    public void execute() {
        //getDefaultCommandQueue().getPlugin().getDao().getPlayerDao().addPlayer(username);
    }
}
