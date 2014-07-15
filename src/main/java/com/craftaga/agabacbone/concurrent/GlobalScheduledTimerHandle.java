package com.craftaga.agabacbone.concurrent;

/**
 * description
 *
 * @author Jonathan
 * @since 15/07/2014
 */
public class GlobalScheduledTimerHandle implements IGlobalScheduledTimerHandle {
    private IQueueConstructor queueConstructor;
    private long interval;

    @Override
    public IQueueConstructor getTimerHandler() {
        return queueConstructor;
    }

    @Override
    public void setTimerHandler(IQueueConstructor queueConstructor) {
        this.queueConstructor = queueConstructor;
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
