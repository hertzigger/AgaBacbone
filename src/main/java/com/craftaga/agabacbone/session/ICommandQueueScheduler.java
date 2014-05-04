package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.IPlayerNameResolver;
import com.craftaga.agabacbone.commands.queue.CommandQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public interface ICommandQueueScheduler {

    void scheduleTimerHandlerAtFixedRate(IScheduledTimerHandler scheduledTimerHandler);

    void removeScheduledHandle(IScheduledTimerHandler scheduledTimerHandler);

    void removeAllScheduledJobs();

    void immediatelyEndAllScheduledJobs();
}
