package com.craftaga.agabacbone.concurrent;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public interface IParameter {
    String getName();

    void setName(String name);

    String getValue();

    void setValue(String value);

    Boolean getRequired();

    void setRequired(Boolean required);
}
