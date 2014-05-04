package com.craftaga.agabacbone.concurrent.handlers.instruction;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IParameterBag;
import com.craftaga.agabacbone.concurrent.InstructionHandler;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class SneggInstructionHandler extends InstructionHandler {
    @Override
    public CommandQueue getEmptyArgsCommandQueue() {
        CommandQueue queue =  new CommandQueue();
        SendMessageToPlayerCommand errorMessage = new SendMessageToPlayerCommand(
                queue,
                "Silky smoothe new test method, oh and you said snegg"
        );
        queue.addCommand(errorMessage);
        return queue;
    }

    @Override
    public IParameterBag getParameterBag() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommandQueue getArgsCommandQueue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommandQueue getInvalidUsageCommandQueue()
    {
        return this.getEmptyArgsCommandQueue();
    }
}
