package com.craftaga.agabacbone.persistence;

import com.craftaga.agabacbone.persistence.entities.ChatPersistence;
import com.craftaga.agabacbone.persistence.entities.EnchantmentPersistence;
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
import com.craftaga.agabacbone.persistence.entities.InstructionPersistence;
import com.craftaga.agabacbone.persistence.entities.InventoryHasItemStackPersistence;
import com.craftaga.agabacbone.persistence.entities.InventoryPersistence;
import com.craftaga.agabacbone.persistence.entities.IpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IServerPersistence;
import com.craftaga.agabacbone.persistence.entities.ISessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserHasIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernameHasSessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernamePersistence;
import com.craftaga.agabacbone.persistence.entities.IWorldPersistence;
import com.craftaga.agabacbone.persistence.entities.ItemStackHasEnchantmentPersistence;
import com.craftaga.agabacbone.persistence.entities.ItemStackPersistence;
import com.craftaga.agabacbone.persistence.entities.KillPersistence;
import com.craftaga.agabacbone.persistence.entities.LocationPersistence;
import com.craftaga.agabacbone.persistence.entities.PlayerSnapshotPersistence;
import com.craftaga.agabacbone.persistence.entities.ServerPersistence;
import com.craftaga.agabacbone.persistence.entities.SessionPersistence;
import com.craftaga.agabacbone.persistence.entities.UserHasIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.UserPersistence;
import com.craftaga.agabacbone.persistence.entities.UsernameHasSessionPersistence;
import com.craftaga.agabacbone.persistence.entities.UsernamePersistence;
import com.craftaga.agabacbone.persistence.entities.WorldPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public class PersistenceManager extends MysqlPersistence<PersistenceManager> implements IPersistenceManager {
    private BoneCPDataSource dataSource;


    private IIpAddressPersistence ipAddressPersistence;
    private IUserHasIpAddressPersistence userHasIpAddressPersistence;
    private IUserPersistence userPersistence;
    private IUsernamePersistence usernamePersistence;
    private IUsernameHasSessionPersistence usernameHasSessionPersistence;
    private ISessionPersistence sessionPersistence;
    private ILocationPersistence locationPersistence;
    private IWorldPersistence worldPersistence;
    private IServerPersistence serverPersistence;
    private IInstructionPersistence instructionPersistence;
    private IChatPersistence chatPersistence;
    private IKillPersistence killPersistence;
    private IPlayerSnapshotPersistence playerSnapshotPersistence;
    private IInventoryPersistence inventoryPersistence;;
    private IInventoryHasItemStackPersistence inventoryHasItemStackPersistence;
    private IItemStackPersistence itemStackPersistence;
    private IItemStackHasEnchantmentPersistence itemStackHasEnchantmentPersistence;
    private IEnchantmentPersistence enchantmentPersistence;

    @Override
    public void setup() {
        if (getDataSource() != null) {
            ipAddressPersistence = new IpAddressPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            userHasIpAddressPersistence = new UserHasIpAddressPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            userPersistence = new UserPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            usernamePersistence = new UsernamePersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            usernameHasSessionPersistence = new UsernameHasSessionPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            sessionPersistence = new SessionPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            locationPersistence = new LocationPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            worldPersistence = new WorldPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            serverPersistence = new ServerPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            instructionPersistence = new InstructionPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            chatPersistence = new ChatPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            killPersistence = new KillPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            playerSnapshotPersistence = new PlayerSnapshotPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            inventoryPersistence = new InventoryPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            inventoryHasItemStackPersistence = new InventoryHasItemStackPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            itemStackPersistence = new ItemStackPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            itemStackHasEnchantmentPersistence = new ItemStackHasEnchantmentPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
            enchantmentPersistence = new EnchantmentPersistence().setDatabaseName(getDatabaseName()).setDataSource(getDataSource());
        } else {
            throw new IllegalArgumentException("A valid datasource has not been supplied to the persistence manager" +
                    " use setDatasource to set this");
        }

    }

    @Override
    public IIpAddressPersistence getIpAddressPersistence() {
        return ipAddressPersistence;
    }

    @Override
    public void setIpAddressPersistence(IIpAddressPersistence ipAddressPersistence) {
        this.ipAddressPersistence = ipAddressPersistence;
    }

    @Override
    public IUserHasIpAddressPersistence getUserHasIpAddressPersistence() {
        return userHasIpAddressPersistence;
    }

    @Override
    public void setUserHasIpAddressPersistence(IUserHasIpAddressPersistence userHasIpAddressPersistence) {
        this.userHasIpAddressPersistence = userHasIpAddressPersistence;
    }

    @Override
    public IUserPersistence getUserPersistence() {
        return userPersistence;
    }

    @Override
    public void setUserPersistence(IUserPersistence userPersistence) {
        this.userPersistence = userPersistence;
    }

    @Override
    public IUsernamePersistence getUsernamePersistence() {
        return usernamePersistence;
    }

    @Override
    public void setUsernamePersistence(IUsernamePersistence usernamePersistence) {
        this.usernamePersistence = usernamePersistence;
    }

    @Override
    public IUsernameHasSessionPersistence getUsernameHasSessionPersistence() {
        return usernameHasSessionPersistence;
    }

    @Override
    public void setUsernameHasSessionPersistence(IUsernameHasSessionPersistence usernameHasSessionPersistence) {
        this.usernameHasSessionPersistence = usernameHasSessionPersistence;
    }

    @Override
    public ISessionPersistence getSessionPersistence() {
        return sessionPersistence;
    }

    @Override
    public void setSessionPersistence(ISessionPersistence sessionPersistence) {
        this.sessionPersistence = sessionPersistence;
    }

    @Override
    public ILocationPersistence getLocationPersistence() {
        return locationPersistence;
    }

    @Override
    public void setLocationPersistence(ILocationPersistence locationPersistence) {
        this.locationPersistence = locationPersistence;
    }

    @Override
    public IWorldPersistence getWorldPersistence() {
        return worldPersistence;
    }

    @Override
    public void setWorldPersistence(IWorldPersistence worldPersistence) {
        this.worldPersistence = worldPersistence;
    }

    @Override
    public IServerPersistence getServerPersistence() {
        return serverPersistence;
    }

    @Override
    public void setServerPersistence(IServerPersistence serverPersistence) {
        this.serverPersistence = serverPersistence;
    }

    @Override
    public IInstructionPersistence getInstructionPersistence() {
        return instructionPersistence;
    }

    @Override
    public void setInstructionPersistence(IInstructionPersistence instructionPersistence) {
        this.instructionPersistence = instructionPersistence;
    }

    @Override
    public IChatPersistence getChatPersistence() {
        return chatPersistence;
    }

    @Override
    public void setChatPersistence(IChatPersistence chatPersistence) {
        this.chatPersistence = chatPersistence;
    }

    @Override
    public IKillPersistence getKillPersistence() {
        return killPersistence;
    }

    @Override
    public void setKillPersistence(IKillPersistence killPersistence) {
        this.killPersistence = killPersistence;
    }

    @Override
    public IPlayerSnapshotPersistence getPlayerSnapshotPersistence() {
        return playerSnapshotPersistence;
    }

    @Override
    public void setPlayerSnapshotPersistence(IPlayerSnapshotPersistence playerSnapshotPersistence) {
        this.playerSnapshotPersistence = playerSnapshotPersistence;
    }

    @Override
    public IInventoryPersistence getInventoryPersistence() {
        return inventoryPersistence;
    }

    @Override
    public void setInventoryPersistence(IInventoryPersistence inventoryPersistence) {
        this.inventoryPersistence = inventoryPersistence;
    }

    @Override
    public IInventoryHasItemStackPersistence getInventoryHasItemStackPersistence() {
        return inventoryHasItemStackPersistence;
    }

    @Override
    public void setInventoryHasItemStackPersistence(IInventoryHasItemStackPersistence inventoryHasItemStackPersistence) {
        this.inventoryHasItemStackPersistence = inventoryHasItemStackPersistence;
    }

    @Override
    public IItemStackPersistence getItemStackPersistence() {
        return itemStackPersistence;
    }

    @Override
    public void setItemStackPersistence(IItemStackPersistence itemStackPersistence) {
        this.itemStackPersistence = itemStackPersistence;
    }

    @Override
    public IItemStackHasEnchantmentPersistence getItemStackHasEnchantmentPersistence() {
        return itemStackHasEnchantmentPersistence;
    }

    @Override
    public void setItemStackHasEnchantmentPersistence(IItemStackHasEnchantmentPersistence itemStackHasEnchantmentPersistence) {
        this.itemStackHasEnchantmentPersistence = itemStackHasEnchantmentPersistence;
    }

    @Override
    public IEnchantmentPersistence getEnchantmentPersistence() {
        return enchantmentPersistence;
    }

    @Override
    public void setEnchantmentPersistence(IEnchantmentPersistence enchantmentPersistence) {
        this.enchantmentPersistence = enchantmentPersistence;
    }

    @Override
    protected PersistenceManager getThis() {
        return this;
    }
}
