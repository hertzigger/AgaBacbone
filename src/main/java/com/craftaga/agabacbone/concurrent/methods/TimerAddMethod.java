package com.craftaga.agabacbone.concurrent.methods;

import com.craftaga.agabacbone.PlayerNameResolver;
import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.Method;
import com.craftaga.agabacbone.concurrent.handlers.instruction.TimerAddInstructionHandler;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class TimerAddMethod extends Method {
    @Override
    public IInstructionHandler getInstructionHandler(IUserSession userSession) {
        IInstructionHandler instructionHandler = new TimerAddInstructionHandler();
        instructionHandler.setUserSession(userSession);
        return instructionHandler;
    }
}
