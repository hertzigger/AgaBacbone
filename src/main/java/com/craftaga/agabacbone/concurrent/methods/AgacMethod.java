package com.craftaga.agabacbone.concurrent.methods;

import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.Method;
import com.craftaga.agabacbone.concurrent.handlers.instruction.TestMessageInstruction;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class AgacMethod extends Method {

    @Override
    public IInstructionHandler getInstructionHandler(IUserSession userSession) {
        TestMessageInstruction testMessageInstruction = new TestMessageInstruction();
        testMessageInstruction.setUserSession(userSession);
        return new TestMessageInstruction();
    }
}
