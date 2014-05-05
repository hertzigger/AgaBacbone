package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.session.IUserSession;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class GetSessionId extends Command implements IValueHolderCommand<Integer> {

    private IUserSession session;
    private Integer value;

    public GetSessionId(CommandQueue commandQueue, IUserSession session) {
        super(commandQueue);
        this.session = session;
    }

    @Override
    public void setValue(Integer integer) {
        value = integer;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void execute() {
        setValue(session.getSessionId());
    }
}
