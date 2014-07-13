package com.craftaga.agabacbone.session;

import com.craftaga.agabacbone.IPlayerNameResolver;
import com.craftaga.agabacbone.concurrent.IPluginManager;
import com.craftaga.agabacbone.concurrent.IMethod;
import com.craftaga.agabacbone.concurrent.IWorldManager;
import com.craftaga.agabacbone.persistence.IPersistenceManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 25/03/14
 */
public interface ISessionHandler extends ICommandQueueScheduler {

    IPluginManager getPluginManager();

    void setPluginManager(IPluginManager pluginManager);

    void setupInstructions();

    CommandSender getCommandSender();

    void setCommandSender(CommandSender commandSender);

    IMethod getCommandHandler();

    void removeUserSession(Player player);

    void addUserSession(Player player);

    boolean onCommand(
            Player player,
            Command command,
            String label,
            String[] args);

    IUserSession getUserSession(Player player);

    boolean checkPlayerHasSession(Player player);

    boolean checkPlayerHasSession(String player);

    IPlayerNameResolver getPlayerNameResolver();

    void setPlayerNameResolver(IPlayerNameResolver playerNameResolver);

    IUserSession getUserSession(String playerName);

    IPersistenceManager getPersistenceManager();

    void setPersistenceManager(IPersistenceManager persistenceManager);

    void setWorldManager(IWorldManager worldManager);

    IWorldManager getWorldManager();

    void setContext(ClassPathXmlApplicationContext context);
}
