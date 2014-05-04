package com.craftaga.agabacbone.commands.decision;

import com.craftaga.agabacbone.commands.Command;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.reporter.IBooleanReporter;
import com.craftaga.agabacbone.commands.queue.SubQueue;

/**
 * description
 *
 * @author Jonathan
 * @since 15/12/13
 */
public class SimpleBooleanDecisionCommand extends Command implements IDecisionCommand {
    private SubQueue failureQueue;
    private SubQueue successQueue;
    private IBooleanReporter booleanReporter;

    public SimpleBooleanDecisionCommand(
            CommandQueue commandQueue,
            IBooleanReporter booleanReporter,
            SubQueue successQueue, SubQueue
            failureQueue
    )
    {
        super(commandQueue);
        this.successQueue = successQueue;
        this.failureQueue = failureQueue;
        this.booleanReporter = booleanReporter;
    }

    @Override
    public void setSuccess(SubQueue subQueue) {
        successQueue = subQueue;
    }

    @Override
    public SubQueue getSuccess() {
        return successQueue;
    }

    @Override
    public void setFailure(SubQueue subQueue) {
        failureQueue = subQueue;
    }

    @Override
    public SubQueue getFailure() {
        return failureQueue;
    }

    @Override
    public void execute() {
        if (booleanReporter.getBoolean()) {
            successQueue.execute();
        } else {
            failureQueue.execute();
        }
    }
}
