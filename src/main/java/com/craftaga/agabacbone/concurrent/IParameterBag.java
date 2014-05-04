package com.craftaga.agabacbone.concurrent;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public interface IParameterBag extends Iterable<IParameter> {
    void addParameter(IParameter parameter);

    int getParameterCount();

    int getRequiredParameterCount();

    IParameter getParameter(String name);
}
