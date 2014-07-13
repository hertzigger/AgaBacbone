package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.io.IOException;
import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 17/04/14
 */
public interface IServerPersistence extends IMysqlPersistence {

    void createServer(String serverName) throws SQLException, IOException;

    Boolean serverExists(String serverName) throws SQLException, IOException;

    int getServerId(String serverName) throws SQLException, IOException;
}
