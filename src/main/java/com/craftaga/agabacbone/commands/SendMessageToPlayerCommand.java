package com.craftaga.agabacbone.commands;

import com.craftaga.agabacbone.commands.queue.CommandQueue;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 * description
 *
 * @author Jonathan
 * @since 10/11/13
 */
public class SendMessageToPlayerCommand extends Command {

    private Messenger messenger;
    private CommandSender receiver;
    private String message;

    public SendMessageToPlayerCommand(
            CommandQueue commandQueue,
            final Messenger messenger,
            final CommandSender receiver)
    {
        this(commandQueue, receiver);
        this.messenger = messenger;
    }

    public SendMessageToPlayerCommand(
            CommandQueue commandQueue,
            final String message,
            final CommandSender receiver
    )
    {
        this(commandQueue, receiver);
        this.message = message;
    }

    public SendMessageToPlayerCommand(
            CommandQueue commandQueue,
            final String message
    )
    {
        super(commandQueue);
        this.message = message;
    }

    private SendMessageToPlayerCommand(
            CommandQueue commandQueue,
            final CommandSender receiver
    )
    {
        super(commandQueue);
        this.receiver = receiver;
    }
    @Override
    public void execute() {
        if (this.messenger != null) {
            this.message = this.messenger.getMessage().toString();
        }
        if (receiver == null) {
            this.receiver = defaultCommandQueue.getSender();
        }
        boolean online = false;
        for (Player player : defaultCommandQueue.getPlugin().getPlugin().getServer().getOnlinePlayers())
        {
            if(player == receiver) {
                online = true;
                break;
            }
        }
        this.defaultCommandQueue.getPlugin().addToServerThread(new Runnable() {
            @Override
            public void run() {
                sendMessage();
            }
        });
    }

    protected void sendMessage()
    {
        receiver.sendMessage(getMessage());
    }

    public Messenger getMessenger() {
        return messenger;
    }

    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public CommandSender getReceiver() {
        return receiver;
    }

    public void setReceiver(CommandSender receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
