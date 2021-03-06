package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 25/03/14
 */
public abstract class InstructionHandler implements IInstructionHandler{
    private IUserSession userSession;

    @Override
    public IUserSession getUserSession() {
        return userSession;
    }

    @Override
    public void setUserSession(IUserSession userSession) {
        this.userSession = userSession;
    }
}
