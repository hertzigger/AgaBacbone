package com.craftaga.agabacbone.concurrent.schedule;

import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class ScheduledCommandQueue implements IScheduledCommandQueue {
    private CommandQueue commandQueue;
    private long interval;

    public ScheduledCommandQueue(CommandQueue commandQueue, long interval)
    {
        this.commandQueue = commandQueue;
        this.interval = interval;
    }

    @Override
    public CommandQueue getCommandQueue() {
        return commandQueue;
    }

    @Override
    public void setCommandQueue(CommandQueue commandQueue) {
        this.commandQueue = commandQueue;
    }

    @Override
    public long getInterval() {
        return interval;
    }

    @Override
    public void setInterval(long interval) {
        this.interval = interval;
    }
}
