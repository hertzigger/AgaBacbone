package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public interface IUsernameHasSessionPersistence extends IMysqlPersistence {
    void add(final int usernameId, final int sessionId) throws SQLException;

    boolean rowExists(int usernameId, int sessionId) throws SQLException;
}
