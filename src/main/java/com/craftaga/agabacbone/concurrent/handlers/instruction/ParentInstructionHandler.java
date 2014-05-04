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
 * @since 10/04/14
 */
public class ParentInstructionHandler extends InstructionHandler {
    private ClassPathXmlApplicationContext stringContext;

    public ParentInstructionHandler()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
    }

    @Override
    public CommandQueue getEmptyArgsCommandQueue() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
        CommandQueue queue = new CommandQueue();
        SendMessageToPlayerCommand msg = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD +
                        stringContext.getMessage(
                                "instruction.parent.cmdError",
                                new String[] {},
                                Locale.ENGLISH)
        );
        return queue;
    }
}
