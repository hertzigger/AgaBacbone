package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IUsernamePersistence extends IMysqlPersistence {
    int addOrFetch(UUID userId, String name) throws SQLException, IOException;

    int fetchUsername(UUID userId, String name) throws SQLException, IOException;

    void setLogout(int usernameId) throws SQLException, IOException;
}
