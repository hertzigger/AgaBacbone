package com.craftaga.agabacbone.commands.queue;

import com.craftaga.agabacbone.concurrent.PluginManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 16/07/2014
 */
public abstract class QueueConstructor implements IQueueConstructor {
    private ClassPathXmlApplicationContext context;
    private PluginManager pluginManager;

    public QueueConstructor(final ClassPathXmlApplicationContext context, final PluginManager pluginManager)
    {
        this.context = context;
        this.pluginManager = pluginManager;
    }

    @Override
    public ClassPathXmlApplicationContext getContext()
    {
        return context;
    }

    @Override
    public void setContext(ClassPathXmlApplicationContext context)
    {
        this.context = context;
    }

    @Override
    public void run()
    {
        pluginManager.executeQueue(getCommandQueue());
    }
}
