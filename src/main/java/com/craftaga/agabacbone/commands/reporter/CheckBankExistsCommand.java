package com.craftaga.agabacbone.commands.reporter;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 17/12/13
 */
public class CheckBankExistsCommand extends Command implements IBooleanReporter {
    private Boolean bankExists;
    private String name;

    public CheckBankExistsCommand(CommandQueue commandQueue, String name)
    {
        super(commandQueue);
        this.name = name;
    }

    @Override
    public void execute() {
        //getDefaultCommandQueue().getPlugin().getDao().getBankDao().isBankInDatabase(name);
    }

    @Override
    public Boolean getBoolean() {
        return bankExists;
    }
}
