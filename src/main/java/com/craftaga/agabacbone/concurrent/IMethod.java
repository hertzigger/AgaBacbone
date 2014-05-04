package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 22/03/14
 */
public interface IMethod {

    int getMethodCount();

    void addMethod(IMethod method);

    String getName();

    IMethod setName(String name);

    IInstructionHandler getInstructionHandler(IUserSession userSession);

    CommandQueue getCommandQueue(String[] args, IUserSession userSession);
}
