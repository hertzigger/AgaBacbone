package com.craftaga.agabacbone.persistence;

import com.craftaga.agabacbone.concurrent.PluginManager;
import com.craftaga.agabacbone.persistence.entities.IChatPersistence;
import com.craftaga.agabacbone.persistence.entities.IEnchantmentPersistence;
import com.craftaga.agabacbone.persistence.entities.IInstructionPersistence;
import com.craftaga.agabacbone.persistence.entities.IInventoryHasItemStackPersistence;
import com.craftaga.agabacbone.persistence.entities.IInventoryPersistence;
import com.craftaga.agabacbone.persistence.entities.IIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IItemStackHasEnchantmentPersistence;
import com.craftaga.agabacbone.persistence.entities.IItemStackPersistence;
import com.craftaga.agabacbone.persistence.entities.IKillPersistence;
import com.craftaga.agabacbone.persistence.entities.ILocationPersistence;
import com.craftaga.agabacbone.persistence.entities.IPlayerSnapshotPersistence;
import com.craftaga.agabacbone.persistence.entities.IServerPersistence;
import com.craftaga.agabacbone.persistence.entities.ISessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserHasIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernameHasSessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernamePersistence;
import com.craftaga.agabacbone.persistence.entities.IWorldPersistence;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IPersistenceManager extends IMysqlPersistence {
    IIpAddressPersistence getIpAddressPersistence();

    void setIpAddressPersistence(IIpAddressPersistence ipAddressPersistence);

    IUserHasIpAddressPersistence getUserHasIpAddressPersistence();

    void setUserHasIpAddressPersistence(IUserHasIpAddressPersistence userHasIpAddressPersistence);

    IUserPersistence getUserPersistence();

    void setUserPersistence(IUserPersistence userPersistence);

    IUsernamePersistence getUsernamePersistence();

    void setUsernamePersistence(IUsernamePersistence usernamePersistence);

    IUsernameHasSessionPersistence getUsernameHasSessionPersistence();

    void setUsernameHasSessionPersistence(IUsernameHasSessionPersistence usernameHasSessionPersistence);

    ISessionPersistence getSessionPersistence();

    void setSessionPersistence(ISessionPersistence sessionPersistence);

    ILocationPersistence getLocationPersistence();

    void setLocationPersistence(ILocationPersistence locationPersistence);

    IWorldPersistence getWorldPersistence();

    void setWorldPersistence(IWorldPersistence worldPersistence);

    IServerPersistence getServerPersistence();

    void setServerPersistence(IServerPersistence serverPersistence);

    IInstructionPersistence getInstructionPersistence();

    void setInstructionPersistence(IInstructionPersistence instructionPersistence);

    IChatPersistence getChatPersistence();

    void setChatPersistence(IChatPersistence chatPersistence);

    IKillPersistence getKillPersistence();

    void setKillPersistence(IKillPersistence killPersistence);

    IPlayerSnapshotPersistence getPlayerSnapshotPersistence();

    void setPlayerSnapshotPersistence(IPlayerSnapshotPersistence playerSnapshotPersistence);

    IInventoryPersistence getInventoryPersistence();

    void setInventoryPersistence(IInventoryPersistence inventoryPersistence);

    IInventoryHasItemStackPersistence getInventoryHasItemStackPersistence();

    void setInventoryHasItemStackPersistence(IInventoryHasItemStackPersistence inventoryHasItemStackPersistence);

    IItemStackPersistence getItemStackPersistence();

    void setItemStackPersistence(IItemStackPersistence itemStackPersistence);

    IItemStackHasEnchantmentPersistence getItemStackHasEnchantmentPersistence();

    void setItemStackHasEnchantmentPersistence(IItemStackHasEnchantmentPersistence itemStackHasEnchantmentPersistence);

    IEnchantmentPersistence getEnchantmentPersistence();

    void setEnchantmentPersistence(IEnchantmentPersistence enchantmentPersistence);

    void setup() throws IllegalArgumentException;
}
