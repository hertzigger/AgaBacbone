package com.craftaga.agabacbone.concurrent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class ParameterBag implements IParameterBag {
    private List<IParameter> parameterList;
    private HashMap<String, IParameter> parameterHashMap;
    private int requiredParameterCount;

    public ParameterBag()
    {
        parameterList = new ArrayList<IParameter>();
        parameterHashMap = new HashMap<String, IParameter>();
        requiredParameterCount = 0;
    }

    @Override
    public void addParameter(IParameter parameter)
    {
        if (parameter.getRequired()) {
            requiredParameterCount++;
        }
        parameterHashMap.put(parameter.getName(), parameter);
        parameterList.add(parameter);
    }

    @Override
    public int getParameterCount()
    {
        return parameterList.size();
    }

    @Override
    public int getRequiredParameterCount()
    {
        return requiredParameterCount;
    }

    @Override
    public Iterator<IParameter> iterator() {
        return parameterList.iterator();
    }

    @Override
    public IParameter getParameter(String name)
    {
        return parameterHashMap.get(name);
    }
}
