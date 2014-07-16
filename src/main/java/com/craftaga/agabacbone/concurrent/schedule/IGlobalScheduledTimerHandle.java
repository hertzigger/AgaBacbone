package com.craftaga.agabacbone.concurrent.schedule;

import com.craftaga.agabacbone.commands.queue.IQueueConstructor;

/**
 * description
 *
 * @author Jonathan
 * @since 15/07/2014
 */
public interface IGlobalScheduledTimerHandle {
    IQueueConstructor getTimerHandler();

    void setTimerHandler(IQueueConstructor timerHandler);

    long getInterval();

    void setInterval(long interval);
}
