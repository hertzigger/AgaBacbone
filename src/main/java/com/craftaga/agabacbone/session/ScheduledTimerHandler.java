package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.concurrent.ITimerHandler;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public class ScheduledTimerHandler implements IScheduledTimerHandler {
    private ITimerHandler timerHandler;
    private long interval;

    public ScheduledTimerHandler(ITimerHandler timerHandler, long interval) {
        this.timerHandler = timerHandler;
        this.interval = interval;
    }

    @Override
    public ITimerHandler getTimerHandler() {
        return timerHandler;
    }

    @Override
    public void setTimerHandler(ITimerHandler timerHandler) {
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
