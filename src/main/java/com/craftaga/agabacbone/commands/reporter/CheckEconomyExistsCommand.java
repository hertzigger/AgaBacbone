package com.craftaga.agabacbone.commands.reporter;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 17/12/13
 */
public class CheckEconomyExistsCommand extends Command implements IBooleanReporter
{
    private Boolean economyExists;
    private String name;

    public CheckEconomyExistsCommand(CommandQueue commandQueue, String name)
    {
        super(commandQueue);
        this.name = name;
        this.economyExists = false;
    }

    @Override
    public void execute()
    {
        //this.economyExists = getDefaultCommandQueue().getPlugin().getDao().getEconomyDao().isEconomyInDatabase(name);
    }

    @Override
    public Boolean getBoolean()
    {
        return economyExists;
    }
}
