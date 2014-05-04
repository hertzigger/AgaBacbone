package com.craftaga.agabacbone.commands.reporter;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 15/12/13
 */
public class CheckPlayerExistsCommand extends Command implements IBooleanReporter {
    private Boolean playerExists;
    private String username;

    public CheckPlayerExistsCommand(CommandQueue commandQueue, String username)
    {
        super(commandQueue);
        this.username = username;
        this.playerExists = false;
    }

    @Override
    public Boolean getBoolean()
    {
        return playerExists;
    }

    @Override
    public void execute()
    {
        //getDefaultCommandQueue().getPlugin().getDao().getPlayerDao().isUserInDatabase(username);
    }
}
