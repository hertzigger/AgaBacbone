package com.craftaga.agabacbone.concurrent.handlers.instruction;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IParameterBag;
import com.craftaga.agabacbone.concurrent.InstructionHandler;
import org.bukkit.ChatColor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class TimerInstructionHandler extends InstructionHandler {

    private ClassPathXmlApplicationContext stringContext;

    public TimerInstructionHandler()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
    }

    @Override
    public CommandQueue getEmptyArgsCommandQueue() {
        CommandQueue queue =  new CommandQueue();
        SendMessageToPlayerCommand msg1 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + stringContext.getMessage("instruction.timer.usage1", null, Locale.ENGLISH)
        );
        SendMessageToPlayerCommand msg2 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.RED + stringContext.getMessage("instruction.timer.usage2", null, Locale.ENGLISH)
        );
        queue.addCommand(msg1);
        queue.addCommand(msg2);
        return queue;
    }

    @Override
    public IParameterBag getParameterBag() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommandQueue getArgsCommandQueue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommandQueue getInvalidUsageCommandQueue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
