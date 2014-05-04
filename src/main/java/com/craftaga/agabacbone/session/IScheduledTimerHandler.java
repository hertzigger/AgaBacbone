package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.concurrent.ITimerHandler;

/**
 * description
 *
 * @author Jonathan
 * @since 10/04/14
 */
public interface IScheduledTimerHandler {
    ITimerHandler getTimerHandler();

    void setTimerHandler(ITimerHandler timerHandler);

    long getInterval();

    void setInterval(long interval);
}
