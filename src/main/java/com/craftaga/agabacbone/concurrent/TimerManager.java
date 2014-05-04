package com.craftaga.agabacbone.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class TimerManager implements ITimerManager {
    private HashMap<String, ITimerHandler> timerHandlerHashMap;

    public TimerManager()
    {
        timerHandlerHashMap = new HashMap<String, ITimerHandler>();
    }

    @Override
    public TimerManager addTimerHandler(String name, ITimerHandler timerHandler)
    {
        timerHandlerHashMap.put(name, timerHandler);
        return this;
    }

    @Override
    public ITimerHandler getTimerHandler(String name)
    {
        return timerHandlerHashMap.get(name);
    }

    @Override
    public Set<String> getTimerNameList()
    {
        return timerHandlerHashMap.keySet();
    }
}
