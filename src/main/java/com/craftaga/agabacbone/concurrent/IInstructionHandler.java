package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 10/03/14
 */
public interface IInstructionHandler {
    CommandQueue getEmptyArgsCommandQueue();

    IParameterBag getParameterBag();

    CommandQueue getArgsCommandQueue();

    CommandQueue getInvalidUsageCommandQueue();

    IUserSession getUserSession();

    void setUserSession(IUserSession userSession);
}
