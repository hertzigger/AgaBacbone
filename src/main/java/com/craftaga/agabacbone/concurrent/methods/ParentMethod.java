package com.craftaga.agabacbone.concurrent.methods;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IInstructionHandler;
import com.craftaga.agabacbone.concurrent.Method;
import com.craftaga.agabacbone.concurrent.handlers.instruction.ParentInstructionHandler;
import com.craftaga.agabacbone.session.IUserSession;
import org.bukkit.ChatColor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 24/03/14
 */
public class ParentMethod extends Method {
    @Override
    public IInstructionHandler getInstructionHandler(IUserSession userSession) {
        IInstructionHandler instructionHandler = new ParentInstructionHandler();
        instructionHandler.setUserSession(userSession);
        return instructionHandler;
    }
}
