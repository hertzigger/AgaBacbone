package com.craftaga.agabacbone.concurrent.methods;

import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.Method;
import com.craftaga.agabacbone.concurrent.handlers.instruction.TimerInstructionHandler;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class TimerMethod extends Method {

    @Override
    public IInstructionHandler getInstructionHandler(IUserSession userSession) {
        IInstructionHandler instructionHandler = new TimerInstructionHandler();
        instructionHandler.setUserSession(userSession);
        return instructionHandler;
    }
}
