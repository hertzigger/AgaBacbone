package com.craftaga.agabacbone.concurrent;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class Parameter implements IParameter {
    private String name;
    private String value;
    private Boolean required;

    public Parameter()
    {
    }

    public Parameter(String name, Boolean required)
    {
        this.name = name;
        this.required = required;
    }
    @Override
    public Boolean getRequired() {
        return required;
    }

    @Override
    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }


}
