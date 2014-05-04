package com.craftaga.agabacbone.persistence.entities;

import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 17/04/14
 */
public interface IServerPersistence {

    void createServer(String serverName) throws SQLException;

    Boolean serverExists(String serverName) throws SQLException;

    int getServerId(String serverName) throws SQLException;
}
