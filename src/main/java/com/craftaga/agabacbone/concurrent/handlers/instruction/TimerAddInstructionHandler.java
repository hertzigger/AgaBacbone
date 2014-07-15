package com.craftaga.agabacbone.concurrent.handlers.instruction;

import com.craftaga.agabacbone.commands.SendMessageToPlayerCommand;
import com.craftaga.agabacbone.commands.queue.CommandQueue;
import com.craftaga.agabacbone.concurrent.IParameterBag;
import com.craftaga.agabacbone.concurrent.IPlayerQueueConstructor;
import com.craftaga.agabacbone.concurrent.ITimerManager;
import com.craftaga.agabacbone.concurrent.InstructionHandler;
import com.craftaga.agabacbone.concurrent.Parameter;
import com.craftaga.agabacbone.concurrent.ParameterBag;
import com.craftaga.agabacbone.session.ICommandQueueScheduler;
import com.craftaga.agabacbone.session.PlayerScheduledTimerHandler;
import org.bukkit.ChatColor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

/**
 * description
 *
 * @author Jonathan
 * @since 09/04/14
 */
public class TimerAddInstructionHandler extends InstructionHandler {

    private IParameterBag parameterBag;
    private ClassPathXmlApplicationContext stringContext;

    public TimerAddInstructionHandler()
    {
        ClassLoader cl = this.getClass().getClassLoader();
        stringContext = new ClassPathXmlApplicationContext(new String[]{"i18n/messageContext.xml"}, false);
        stringContext.setClassLoader(cl);
        stringContext.refresh();
        parameterBag = new ParameterBag();
        parameterBag.addParameter(new Parameter("timername", true));
        parameterBag.addParameter(new Parameter("interval", true));
        parameterBag.addParameter(new Parameter("player", false));
    }

    @Override
    public CommandQueue getEmptyArgsCommandQueue() {
        CommandQueue queue =  new CommandQueue();
        SendMessageToPlayerCommand msg1 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + stringContext.getMessage("instruction.timer.add.usage1", null, Locale.ENGLISH)
        );
        SendMessageToPlayerCommand msg2 = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.RED + stringContext.getMessage("instruction.timer.add.usage2", null, Locale.ENGLISH)
        );
        queue.addCommand(msg1);
        queue.addCommand(msg2);
        return queue;
    }

    @Override
    public IParameterBag getParameterBag() {
        return parameterBag;
    }

    @Override
    public CommandQueue getArgsCommandQueue() {
        CommandQueue commandQueue = new CommandQueue();
        StringBuilder msg = new StringBuilder();

        //validate required variables
        Boolean variablesValid = true;
        String timerName = parameterBag.getParameter("timername").getValue();
        if (!validateTimerName(timerName)) {
            msg.append("/n" + stringContext.getMessage(
                    "instruction.timer.add.invalid.parameter.timerName",
                    new String[] {timerName},
                    Locale.ENGLISH
            ));
            variablesValid = false;
        }

        long interval = 100;
        try {
            interval = Long.parseLong(parameterBag.getParameter("interval").getValue());
        } catch (NumberFormatException e) {
            msg.append("/n" + stringContext.getMessage("error.notNumber", new String[] {timerName}, Locale.ENGLISH));
            variablesValid = false;
        }

        String playerName = parameterBag.getParameter("player").getValue();
        if (playerName != null) {
            if (!getUserSession().getSessionHandler().checkPlayerHasSession(playerName)) {
                msg.append (
                        "/n" +
                         stringContext.getMessage("error.playerSessionNotFound",
                         new String[] {playerName},
                         Locale.ENGLISH
                ));
                variablesValid = false;
            }
        }
        String playerScope = "";
        if(!variablesValid) {
            msg.insert(
                    0,
                    "" +
                    ChatColor.WHITE +
                    ChatColor.BOLD +
                    stringContext.getMessage("instruction.cmdError",new String[] {playerName}, Locale.ENGLISH
            ));
            msg.append("/n" + stringContext.getMessage("instruction.timer.add.usage1", null, Locale.ENGLISH));
            msg.append("/n" + stringContext.getMessage("instruction.timer.add.usage2", null, Locale.ENGLISH));
        } else {
            IPlayerQueueConstructor timerHandler =
                    getUserSession().
                    getSessionHandler().
                    getPluginManager().
                    getTimerManager().
                    getTimerHandler(timerName);
            ICommandQueueScheduler session;

            if (playerName != null) {
                session = getUserSession().getSessionHandler().getUserSession(playerName);
                playerScope = playerName;
            } else {
                session = getUserSession().getSessionHandler();
                playerScope = "all players";
            }
            session.scheduleTimerHandlerAtFixedRate(new PlayerScheduledTimerHandler(timerHandler, interval));
        }

        msg.append("Timer has been successfully schedules for " + playerScope);

        commandQueue.addCommand(new SendMessageToPlayerCommand(
                commandQueue,
                msg.toString()
        ));
        return commandQueue;
    }

    @Override
    public CommandQueue getInvalidUsageCommandQueue() {
        CommandQueue queue = getEmptyArgsCommandQueue();
        SendMessageToPlayerCommand msg = new SendMessageToPlayerCommand(
                queue,
                "" + ChatColor.WHITE + ChatColor.BOLD + stringContext.getMessage(
                        "instruction.cmdError",
                        new String[] {getUserSession().getUser().getName()},
                        Locale.ENGLISH)
        );
        queue.addToFront(msg);
        return queue;
    }

    private Boolean validateTimerName(String timerName)
    {
        Boolean validTimerName = false;
        ITimerManager timerManager = getUserSession().getSessionHandler().getPluginManager().getTimerManager();
        for (String s : timerManager.getTimerNameList()) {
            if (s.equalsIgnoreCase(timerName)) {
                validTimerName = true;
                break;
            }
        }
        return validTimerName;
    }
}
