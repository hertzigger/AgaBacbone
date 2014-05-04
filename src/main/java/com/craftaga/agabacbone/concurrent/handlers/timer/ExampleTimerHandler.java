package com.craftaga.agabacbone.concurrent.handlers.timer;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.TimerHandler;
import org.bukkit.ChatColor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class ExampleTimerHandler extends TimerHandler {

    private ClassPathXmlApplicationContext stringContext;

    public ExampleTimerHandler()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
    }

    @Override
    public CommandQueue getCommandQueue()
    {
        CommandQueue queue = new CommandQueue(getUserSession().getSessionHandler().getPluginManager());
        SendMessageToPlayerCommand msg = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + stringContext.getMessage(
                        "timer.example.msg",
                        new String[] {getUserSession().getUser().getName()},
                        Locale.ENGLISH),
                getUserSession().getUser()
        );
        queue.addCommand(msg);
        return queue;
    }
}
