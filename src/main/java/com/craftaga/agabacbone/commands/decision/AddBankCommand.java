package com.craftaga.agabacbone.commands.decision;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 17/12/13
 */
public class AddBankCommand extends Command
{
    private String name;

    public AddBankCommand(CommandQueue commandQueue, String name)
    {
        super(commandQueue);
        this.name = name;
    }
    @Override
    public void execute() {
        //getDefaultCommandQueue().getPlugin().getDao().getBankDao().addBank(name);
    }
}
