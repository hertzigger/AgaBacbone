package com.craftaga.agabacbone.commands;

/**
 * Command interface for all commands, commands should be seperated into small
 * operations then added to a CommandQueue. A command is a single action
 *
 * i.e. SendMessageToConsoleCommand
 *
 * A CommandQueue is a Sequence of actions
 *
 * i.e. AddMoneyToPlayerCommand -> LogMoneyChangeEventToDatabaseCommand
 *
 * @author Jonathan
 * @since 08/11/13
 */
public interface ICommand {
    public void execute();
}