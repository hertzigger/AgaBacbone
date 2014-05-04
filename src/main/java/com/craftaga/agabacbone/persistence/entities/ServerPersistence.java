package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * description
 *
 * @author Jonathan
 * @since 17/04/14
 */
public class ServerPersistence extends MysqlPersistence implements IServerPersistence {

    private static final String SERVER_EXISTS = "select * from Server WHERE name=?";
    private static final String CREATE_SERVER = "insert into Server (idServer, name, created, modified) VALUES (null,?,?,?)";
    private static final String SERVER_FETCH_ID = "select idServer from Server WHERE name=?";

    public ServerPersistence(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void createServer(String serverName) throws SQLException {
        PreparedStatement insert = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            insert = connection.prepareStatement(CREATE_SERVER);
            insert.setString(1, serverName);
            insert = setModifiedAndCreated(insert, 2, 3);
            insert.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (insert != null) {
                insert.close();
            }
        }
    }

    @Override
    public Boolean serverExists(String serverName) throws SQLException {
        PreparedStatement servers = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            servers = connection.prepareStatement(SERVER_EXISTS);
            servers.setString(1, serverName);
            ResultSet resultsSet = servers.executeQuery();
            connection.commit();
            while (resultsSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (servers != null) {
                servers.close();
            }
        }
        return false;
    }

    @Override
    public int getServerId(String serverName) throws SQLException {
        PreparedStatement server = null;
        Connection connection = null;
        int serverId = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            server = connection.prepareStatement(SERVER_FETCH_ID);
            server.setString(1, serverName);
            ResultSet resultSet = server.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                serverId = resultSet.getInt("idServer");
            }
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            if (server != null) {
                server.close();
            }
            if (connection != null) {
                connection.rollback();
            }
        }
        return serverId;
    }
}
