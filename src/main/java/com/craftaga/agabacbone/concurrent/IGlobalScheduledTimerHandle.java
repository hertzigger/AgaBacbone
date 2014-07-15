package com.craftaga.agabacbone.concurrent;

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
