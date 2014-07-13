package com.craftaga.agabacbone.concurrent;

import java.util.HashMap;
import java.util.Set;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class TimerManager implements ITimerManager {
    private HashMap<String, IPlayerQueueConstructor> timerHandlerHashMap;

    public TimerManager()
    {
        timerHandlerHashMap = new HashMap<String, IPlayerQueueConstructor>();
    }

    @Override
    public TimerManager addTimerHandler(String name, IPlayerQueueConstructor timerHandler)
    {
        timerHandlerHashMap.put(name, timerHandler);
        return this;
    }

    @Override
    public IPlayerQueueConstructor getTimerHandler(String name)
    {
        return timerHandlerHashMap.get(name);
    }

    @Override
    public Set<String> getTimerNameList()
    {
        return timerHandlerHashMap.keySet();
    }
}
