package com.craftaga.agabacbone.concurrent.schedule;

import com.craftaga.agabacbone.concurrent.schedule.IPlayerQueueConstructor;
import com.craftaga.agabacbone.concurrent.schedule.IPlayerScheduledTimerHandler;

/**
 * description
 *
 * @author Jonathan
 * @since 16/07/2014
 */
public class PlayerScheduledTimerHandler implements IPlayerScheduledTimerHandler {
    protected IPlayerQueueConstructor timerHandler;
    protected long interval;

    public PlayerScheduledTimerHandler(IPlayerQueueConstructor timerHandler, long interval) {
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
