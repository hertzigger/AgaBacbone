package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 15/07/2014
 */
public interface IQueueConstructor extends Runnable {
    CommandQueue getCommandQueue();

    ClassPathXmlApplicationContext getContext();

    void setContext(ClassPathXmlApplicationContext context);
}
