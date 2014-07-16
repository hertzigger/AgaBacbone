package com.craftaga.agabacbone.concurrent.schedule;

/**
 * description
 *
 * @author Jonathan
 * @since 16/07/2014
 */
public interface IPlayerCommandQueueScheduler {
    void scheduleTimerHandlerAtFixedRate(IPlayerScheduledTimerHandler scheduledTimerHandler);

    void removeScheduledHandle(IPlayerScheduledTimerHandler scheduledTimerHandler);

    void removeAllScheduledJobs();

    void immediatelyEndAllScheduledJobs();
}
