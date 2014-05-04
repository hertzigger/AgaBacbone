package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public abstract class TimerHandler implements ITimerHandler {
    private IUserSession userSession;

    @Override
    public IUserSession getUserSession() {
        return userSession;
    }

    @Override
    public void setUserSession(IUserSession userSession) {
        this.userSession = userSession;
    }

    public void run()
    {
        userSession.executeQueue(getCommandQueue());
    }
}
