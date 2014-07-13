package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public interface IPlayerQueueConstructor extends Runnable {

    IUserSession getUserSession();

    void setUserSession(IUserSession userSession);

    CommandQueue getCommandQueue();

    ClassPathXmlApplicationContext getContext();

    void setContext(ClassPathXmlApplicationContext context);
}
