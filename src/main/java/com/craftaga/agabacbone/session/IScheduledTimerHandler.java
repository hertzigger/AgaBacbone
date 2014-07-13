package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.concurrent.IPlayerQueueConstructor;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public interface IScheduledTimerHandler {
    IPlayerQueueConstructor getTimerHandler();

    void setTimerHandler(IPlayerQueueConstructor timerHandler);

    long getInterval();

    void setInterval(long interval);
}
