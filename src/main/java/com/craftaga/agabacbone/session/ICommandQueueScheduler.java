package com.craftaga.agabacbone.session;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public interface ICommandQueueScheduler {

    void scheduleTimerHandlerAtFixedRate(IPlayerScheduledTimerHandler scheduledTimerHandler);

    void removeScheduledHandle(IPlayerScheduledTimerHandler scheduledTimerHandler);

    void removeAllScheduledJobs();

    void immediatelyEndAllScheduledJobs();
}
