package com.craftaga.agabacbone.concurrent.handlers.timer;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.commands.queue.PlayerQueueConstructor;
import org.bukkit.ChatColor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class ExampleTimerHandler extends PlayerQueueConstructor {

    public ExampleTimerHandler(ClassPathXmlApplicationContext context) {
        super(context);
    }


    @Override
    public CommandQueue getCommandQueue()
    {
        CommandQueue queue = new CommandQueue(getUserSession().getSessionHandler().getPluginManager());
        SendMessageToPlayerCommand msg = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + getContext().getMessage(
                        "timer.example.msg",
                        new String[] {getUserSession().getUser().getName()},
                        Locale.ENGLISH),
                getUserSession().getUser()
        );
        queue.addCommand(msg);
        return queue;
    }
}
