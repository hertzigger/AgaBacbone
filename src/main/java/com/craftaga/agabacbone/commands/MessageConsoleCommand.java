package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.bukkit.command.CommandSender;

/**
 * description
 *
 * @author Jonathan
 * @since 08/11/13
 */
public class MessageConsoleCommand extends SendMessageToPlayerCommand {

    public MessageConsoleCommand(CommandQueue commandQueue, String message)
    {
        super(commandQueue, message);
    }

    public MessageConsoleCommand(
            CommandQueue commandQueue,
            final Messenger messenger)
    {
        super(commandQueue, messenger, null);
    }

    public void execute()
    {
        if(getMessenger() != null) {
            setMessage(getMessenger().getMessage().toString());
            this.addToServerThread(new Runnable() {
                @Override
                public void run() {
                    sendMessage();
                }
            });
        }
    }

    protected void sendMessage()
    {
        this.getDefaultCommandQueue().getPlugin().sendServerInfoMessage(getMessage());
    }
}
