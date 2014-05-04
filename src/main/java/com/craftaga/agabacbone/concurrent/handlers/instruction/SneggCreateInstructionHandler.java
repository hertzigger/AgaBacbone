package com.craftaga.agabacbone.concurrent.handlers.instruction;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IParameterBag;
import com.craftaga.agabacbone.concurrent.InstructionHandler;
import com.craftaga.agabacbone.concurrent.Parameter;
import com.craftaga.agabacbone.concurrent.ParameterBag;
import org.bukkit.ChatColor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * Currently a test Instruction Handler that simulates what currently works in snegg plugin
 *
 * Instructions
 *
 * Create: replys with a msg
 * Create <name:string>: replys with a msg and the passed in name
 * Create <name:string> [x:int] [y:int] [z:int]: replys with a msg containing name, x, y and z
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class SneggCreateInstructionHandler extends InstructionHandler {
    private IParameterBag parameterBag;
    private ClassPathXmlApplicationContext stringContext;

    public SneggCreateInstructionHandler()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
        parameterBag = new ParameterBag();
        parameterBag.addParameter(new Parameter("name", true));
        parameterBag.addParameter(new Parameter("x", false));
        parameterBag.addParameter(new Parameter("y", false));
        parameterBag.addParameter(new Parameter("z", false));
    }
    @Override
    public CommandQueue getEmptyArgsCommandQueue() {
        CommandQueue queue =  new CommandQueue();
        SendMessageToPlayerCommand msg1 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + stringContext.getMessage("instruction.sneggCreate.usage1", null, Locale.ENGLISH)
        );
        SendMessageToPlayerCommand msg2 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.RED + stringContext.getMessage("instruction.sneggCreate.usage2", null, Locale.ENGLISH)
        );
        SendMessageToPlayerCommand msg3 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.RED + stringContext.getMessage("instruction.sneggCreate.usage3", null, Locale.ENGLISH)
        );
        queue.addCommand(msg1);
        queue.addCommand(msg2);
        queue.addCommand(msg3);
        return queue;
    }

    @Override
    public CommandQueue getArgsCommandQueue() {
        CommandQueue queue =  new CommandQueue();
        StringBuilder msg = new StringBuilder();
        msg.append("You want to create an arena called " + parameterBag.getParameter("name").getValue());
        Boolean validOptionalArgs = false;
        // check for optional x,y and z as a set and only process if they all exist
        if(((parameterBag.getParameter("x").getValue() != null) &&
                (parameterBag.getParameter("y").getValue() != null)) &&
                (parameterBag.getParameter("z").getValue() != null)) {
            try {
                int x = Integer.parseInt(parameterBag.getParameter("x").getValue());
                int y = Integer.parseInt(parameterBag.getParameter("y").getValue());
                int z = Integer.parseInt(parameterBag.getParameter("z").getValue());
                if(x != 0 && y != 0 && z != 0) {
                    validOptionalArgs = true;
                    msg.append(" with the location of x:" + x + " y:" + y + " z:" + z);
                }
            } catch (NumberFormatException e) {
                return getEmptyArgsCommandQueue();
            }
        }
        if (!validOptionalArgs) {
            if(parameterBag.getParameter("x").getValue() != null ||
                    parameterBag.getParameter("y").getValue() != null ||
                    parameterBag.getParameter("x").getValue() != null)
            {
                return getEmptyArgsCommandQueue();
            }
        }
        SendMessageToPlayerCommand errorMessage = new SendMessageToPlayerCommand(
                queue,
                msg.toString()
        );
        queue.addCommand(errorMessage);
        return queue;
    }

    @Override
    public CommandQueue getInvalidUsageCommandQueue()
    {
        CommandQueue queue = getEmptyArgsCommandQueue();
        SendMessageToPlayerCommand msg = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + stringContext.getMessage(
                        "instruction.sneggCreate.cmdError",
                        new String[] {getUserSession().getUser().getName()},
                        Locale.ENGLISH)
        );
        queue.addToFront(msg);
        return queue;
    }

    @Override
    public IParameterBag getParameterBag() {
        return parameterBag;
    }


}
