package com.craftaga.agabacbone.concurrent.methods;

import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.Method;
import com.craftaga.agabacbone.concurrent.handlers.instruction.SneggCreateInstructionHandler;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class SneggCreateMethod extends Method {

    @Override
    public IInstructionHandler getInstructionHandler(IUserSession userSession) {
        SneggCreateInstructionHandler sneggCreateInstructionHandler = new SneggCreateInstructionHandler();
        sneggCreateInstructionHandler.setUserSession(userSession);
        return sneggCreateInstructionHandler;
    }
}
