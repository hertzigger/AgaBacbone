package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public interface IScheduledCommandQueue {
    CommandQueue getCommandQueue();

    void setCommandQueue(CommandQueue commandQueue);

    long getInterval();

    void setInterval(long interval);
}
