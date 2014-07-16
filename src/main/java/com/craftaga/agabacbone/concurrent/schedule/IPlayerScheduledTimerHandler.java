package com.craftaga.agabacbone.concurrent.schedule;

/**
 * description
 *
 * @author Jonathan
 * @since 16/07/2014
 */
public interface IPlayerScheduledTimerHandler {
    IPlayerQueueConstructor getTimerHandler();

    void setTimerHandler(IPlayerQueueConstructor timerHandler);

    long getInterval();

    void setInterval(long interval);
}
