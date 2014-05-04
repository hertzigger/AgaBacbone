package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 17/12/13
 */
public class AddEconomyCommand extends Command
{
    private String name;
    private String plural;
    private String singlelar;

    public AddEconomyCommand(CommandQueue commandQueue, String name, String plural, String singlelar)
    {
        super(commandQueue);
        this.name = name;
        this.plural = plural;
        this.singlelar = singlelar;
    }
    @Override
    public void execute() {
        //getDefaultCommandQueue().getPlugin().getDao().getEconomyDao().addEconomy(name, plural, singlelar);
    }
}
