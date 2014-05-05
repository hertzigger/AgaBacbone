package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.session.IUserSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public abstract class TimerHandler implements ITimerHandler {
    private IUserSession userSession;
    private ClassPathXmlApplicationContext context;

    public TimerHandler(final ClassPathXmlApplicationContext context)
    {
        this.context = context;
    }

    @Override
    public IUserSession getUserSession() {
        return userSession;
    }

    @Override
    public void setUserSession(final IUserSession userSession) {
        this.userSession = userSession;
    }

    public void run()
    {
        userSession.executeQueue(getCommandQueue());
    }

    @Override
    public ClassPathXmlApplicationContext getContext() {
        return context;
    }

    @Override
    public void setContext(ClassPathXmlApplicationContext context) {
        this.context = context;
    }
}
