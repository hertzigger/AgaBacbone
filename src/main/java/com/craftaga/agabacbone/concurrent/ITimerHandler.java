package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public interface ITimerHandler extends Runnable {

    IUserSession getUserSession();

    void setUserSession(IUserSession userSession);

    CommandQueue getCommandQueue();
}
