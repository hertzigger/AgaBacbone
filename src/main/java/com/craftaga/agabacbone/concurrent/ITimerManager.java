package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.concurrent.schedule.IPlayerQueueConstructor;

import java.util.Set;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public interface ITimerManager {
    TimerManager addTimerHandler(String name, IPlayerQueueConstructor timerHandler);

    IPlayerQueueConstructor getTimerHandler(String name);

    Set<String> getTimerNameList();
}
