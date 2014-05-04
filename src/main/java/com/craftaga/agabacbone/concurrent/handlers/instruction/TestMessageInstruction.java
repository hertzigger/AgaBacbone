package com.craftaga.agabacbone.concurrent.handlers.instruction;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.IParameterBag;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class TestMessageInstruction implements IInstructionHandler
{
    @Override
    public CommandQueue getEmptyArgsCommandQueue() {
        CommandQueue queue =  new CommandQueue();
        SendMessageToPlayerCommand errorMessage = new SendMessageToPlayerCommand(
                queue,
                "Test Message"
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

    @Override
    public IUserSession getUserSession() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setUserSession(IUserSession userSession) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
