package com.craftaga.agabacbone.commands.decision;

import com.craftaga.agabacbone.commands.ICommand;
import com.craftaga.agabacbone.commands.queue.SubQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 10/11/13
 */
public interface IDecisionCommand extends ICommand {
    public void setSuccess(SubQueue subQueue);
    public SubQueue getSuccess();
    public void setFailure(SubQueue subQueue);
    public SubQueue getFailure();
}
