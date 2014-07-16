package com.craftaga.agabacbone.commands.queue;

import com.craftaga.agabacbone.concurrent.IPluginManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description
 *
 * @author Jonathan
 * @since 16/07/2014
 */
public abstract class QueueConstructor implements IQueueConstructor {
    private ClassPathXmlApplicationContext context;
    private IPluginManager pluginManager;

    public QueueConstructor(final ClassPathXmlApplicationContext context, final IPluginManager pluginManager)
    {
        this.context = context;
        this.pluginManager = pluginManager;
    }

    @Override
    public ClassPathXmlApplicationContext getContext()
    {
        return context;
    }

    public IPluginManager getPluginManger()
    {
        return this.pluginManager;
    }

    public void setPluginManager(IPluginManager pluginManager)
    {
        this.pluginManager = pluginManager;
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
