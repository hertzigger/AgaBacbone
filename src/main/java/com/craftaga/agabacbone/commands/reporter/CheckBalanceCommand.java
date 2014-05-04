package com.craftaga.agabacbone.commands.reporter;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.bukkit.command.CommandSender;
import org.springframework.context.ApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 14/12/13
 */
public class CheckBalanceCommand extends Command implements IFloatReporter {

    private CommandSender sender;
    private ApplicationContext ctx = null;

    public CheckBalanceCommand(CommandQueue commandQueue, CommandSender sender)
    {
        super(commandQueue);
        this.sender = sender;
    }
    @Override
    public void execute() {
        //getDefaultCommandQueue().getPlugin().getDao().getPlayerDao().isUserInDatabase(sender.getName());

    }

    @Override
    public float getFloat() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
