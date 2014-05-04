package com.craftaga.agabacbone.concurrent.methods;

import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.Method;
import com.craftaga.agabacbone.concurrent.handlers.instruction.SneggInstructionHandler;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class SneggMethod extends Method {

    @Override
    public IInstructionHandler getInstructionHandler(IUserSession userSession) {
        SneggInstructionHandler sneggInstructionHandler = new SneggInstructionHandler();
        sneggInstructionHandler.setUserSession(userSession);
        return sneggInstructionHandler;
    }
}
