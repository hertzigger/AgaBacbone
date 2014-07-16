package com.craftaga.agabacbone.concurrent.schedule;

import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 16/07/2014
 */
public interface IScheduledCommandQueue {
    CommandQueue getCommandQueue();

    void setCommandQueue(CommandQueue commandQueue);

    long getInterval();

    void setInterval(long interval);
}
