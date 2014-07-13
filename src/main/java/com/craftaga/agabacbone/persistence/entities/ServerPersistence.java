package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.commons.io.IOUtils;

import javax.sql.DataSource;
import java.io.IOException;
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
public class ServerPersistence extends MysqlPersistence<ServerPersistence> implements IServerPersistence {

    @Override
    public void createServer(String serverName) throws SQLException, IOException {
        PreparedStatement insert = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            insert = connection.prepareStatement(getStatement("ServerCreate"));
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
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public Boolean serverExists(String serverName) throws SQLException, IOException {
        PreparedStatement servers = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            servers = connection.prepareStatement(getStatement("ServerExists"));
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
            if (connection != null) {
                connection.close();
            }
        }
        return false;
    }

    @Override
    public int getServerId(String serverName) throws SQLException, IOException
    {
        PreparedStatement server = null;
        Connection connection = null;
        int serverId = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            server = connection.prepareStatement(getStatement("ServerExists"));
            server.setString(1, serverName);
            ResultSet resultSet = server.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                serverId = resultSet.getInt("idServer");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (server != null) {
                server.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return serverId;
    }

    @Override
    protected ServerPersistence getThis() {
        return this;
    }
}
