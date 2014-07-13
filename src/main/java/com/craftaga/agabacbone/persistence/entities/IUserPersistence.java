package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IUserPersistence extends IMysqlPersistence {

    UUID fetchUser(UUID uuid) throws SQLException, IOException;

    UUID login(UUID uniqueId) throws SQLException, IOException;

    void setLogout(UUID userId) throws SQLException, IOException;
}
