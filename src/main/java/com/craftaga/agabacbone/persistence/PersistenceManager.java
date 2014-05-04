package com.craftaga.agabacbone.persistence;

import com.craftaga.agabacbone.persistence.entities.IIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.ILocationPersistence;
import com.craftaga.agabacbone.persistence.entities.IpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IServerPersistence;
import com.craftaga.agabacbone.persistence.entities.ISessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserHasIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernameHasSessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernamePersistence;
import com.craftaga.agabacbone.persistence.entities.IWorldPersistence;
import com.craftaga.agabacbone.persistence.entities.LocationPersistence;
import com.craftaga.agabacbone.persistence.entities.ServerPersistence;
import com.craftaga.agabacbone.persistence.entities.SessionPersistence;
import com.craftaga.agabacbone.persistence.entities.UserHasIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.UserPersistence;
import com.craftaga.agabacbone.persistence.entities.UsernameHasSessionPersistence;
import com.craftaga.agabacbone.persistence.entities.UsernamePersistence;
import com.craftaga.agabacbone.persistence.entities.WorldPersistence;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public class PersistenceManager extends MysqlPersistence implements IPersistenceManager {
    private DataSource dataSource;

    private IIpAddressPersistence ipAddressPersistence;
    private IUserHasIpAddressPersistence userHasIpAddressPersistence;
    private IUserPersistence userPersistence;
    private IUsernamePersistence usernamePersistence;
    private IUsernameHasSessionPersistence usernameHasSessionPersistence;
    private ISessionPersistence sessionPersistence;
    private ILocationPersistence locationPersistence;
    private IWorldPersistence worldPersistence;
    private IServerPersistence serverPersistence;

    public PersistenceManager(DataSource dataSource)
    {
        super(dataSource);
        ipAddressPersistence = new IpAddressPersistence(dataSource);
        userHasIpAddressPersistence = new UserHasIpAddressPersistence(dataSource);
        userPersistence = new UserPersistence(dataSource);
        usernamePersistence = new UsernamePersistence(dataSource);
        usernameHasSessionPersistence = new UsernameHasSessionPersistence(dataSource);
        sessionPersistence = new SessionPersistence(dataSource);
        locationPersistence = new LocationPersistence(dataSource);
        worldPersistence = new WorldPersistence(dataSource);
        serverPersistence = new ServerPersistence(dataSource);
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
}
