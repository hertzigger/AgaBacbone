package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface ISessionPersistence extends IMysqlPersistence {
    int addSession(Date date, int worldId) throws SQLException, IOException;

    void logout(int sessionId) throws SQLException, IOException;
}
