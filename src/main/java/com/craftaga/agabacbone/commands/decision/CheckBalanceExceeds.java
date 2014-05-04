package com.craftaga.agabacbone.commands.decision;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.ICommand;
import com.craftaga.agabacbone.commands.Messenger;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.queue.SubQueue;
import com.craftaga.agabacbone.commands.reporter.IFloatReporter;
import org.bukkit.command.CommandSender;

/**
 * description
 *
 * @author Jonathan
 * @since 10/11/13
 */
public class CheckBalanceExceeds extends Command implements ICommand, IDecisionCommand, Messenger {
    public static final float BALANCE = 1000;
    private float checkAmount;
    private StringBuilder message;
    private CommandSender sender;
    private SubQueue successQueue;
    private SubQueue failureQueue;
    private IFloatReporter floatReporter;

    public CheckBalanceExceeds(CommandQueue commandQueue, CommandSender sender, IFloatReporter floatReporter)
    {
        super(commandQueue);
        this.sender = sender;
        this.message = new StringBuilder();
        this.floatReporter = floatReporter;
    }

    public void execute()
    {
        //Go get balance
        floatReporter.execute();
        float balance = floatReporter.getFloat();
        if(balance >= this.checkAmount) {
            this.message.append("You have the money for this transaction");
            this.successQueue.execute();
        } else {
            this.message.append("Your balance is insufficient to cover this transaction");
            this.failureQueue.execute();
        }
    }

    public CheckBalanceExceeds setEntity(CommandSender sender)
    {
        this.sender = sender;
        return this;
    }

    public void setAmountToCheck(float balance)
    {
        this.checkAmount = balance;
    }

    @Override
    public void setSuccess(SubQueue subQueue) {
        this.successQueue = subQueue;
    }

    @Override
    public SubQueue getSuccess() {
        return this.successQueue;
    }

    @Override
    public void setFailure(SubQueue subQueue) {
        this.failureQueue = subQueue;
    }

    @Override
    public SubQueue getFailure() {
        return this.failureQueue;
    }

    @Override
    public StringBuilder getMessage() {
        return this.message;
    }
}
