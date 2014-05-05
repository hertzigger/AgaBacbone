package com.craftaga.agabacbone.persistence.entities;

import java.sql.SQLException;
import java.util.Date;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface ISessionPersistence {
    int addSession(Date date, int worldId) throws SQLException;

    void logout(int sessionId) throws SQLException;
}
