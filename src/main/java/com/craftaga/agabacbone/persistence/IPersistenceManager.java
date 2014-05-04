package com.craftaga.agabacbone.persistence;

import com.craftaga.agabacbone.persistence.entities.IIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.ILocationPersistence;
import com.craftaga.agabacbone.persistence.entities.IServerPersistence;
import com.craftaga.agabacbone.persistence.entities.ISessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserHasIpAddressPersistence;
import com.craftaga.agabacbone.persistence.entities.IUserPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernameHasSessionPersistence;
import com.craftaga.agabacbone.persistence.entities.IUsernamePersistence;
import com.craftaga.agabacbone.persistence.entities.IWorldPersistence;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IPersistenceManager {
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
}
