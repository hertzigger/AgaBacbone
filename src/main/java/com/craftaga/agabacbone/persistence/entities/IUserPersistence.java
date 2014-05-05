package com.craftaga.agabacbone.persistence.entities;

import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IUserPersistence {

    UUID fetchUser(UUID uuid) throws SQLException;

    UUID login(UUID uniqueId) throws SQLException;

    void setLogout(UUID userId) throws SQLException;
}
