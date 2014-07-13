package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.concurrent.IPlayerQueueConstructor;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class ScheduledTimerHandler implements IScheduledTimerHandler {
    private IPlayerQueueConstructor timerHandler;
    private long interval;

    public ScheduledTimerHandler(IPlayerQueueConstructor timerHandler, long interval) {
        this.timerHandler = timerHandler;
        this.interval = interval;
    }

    @Override
    public IPlayerQueueConstructor getTimerHandler() {
        return timerHandler;
    }

    @Override
    public void setTimerHandler(IPlayerQueueConstructor timerHandler) {
        this.timerHandler = timerHandler;
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
