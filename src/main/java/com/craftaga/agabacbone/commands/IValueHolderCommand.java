package com.craftaga.agabacbone.commands;

/**
 * description
 *
 * @author Jonathan
 * @since 11/04/14
 */
public interface IValueHolderCommand<T> extends ICommand {
    void setValue(T t);
    T getValue();
}
