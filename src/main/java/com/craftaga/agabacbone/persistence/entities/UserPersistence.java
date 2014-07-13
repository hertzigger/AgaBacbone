package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public class UserPersistence extends MysqlPersistence<UserPersistence> implements IUserPersistence {

    @Override
    public UUID fetchUser(UUID uuid) throws SQLException, IOException
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getStatement("UserFetch"));
            statement.setString(1, uuid.toString());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return UUID.fromString(resultSet.getString("uuid"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    @Override
    public UUID login(UUID uniqueId) throws SQLException, IOException {
        PreparedStatement statement = null;
        Connection connection = null;
        UUID returnUuid = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            returnUuid = fetchUser(uniqueId);
            if (returnUuid == null) {
                statement = connection.prepareStatement(getStatement("UserAdd"), Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, uniqueId.toString());
                statement = setModifiedAndCreated(statement, 2, 3);
                statement.setTimestamp(4, new Timestamp(new Date().getTime()));
                statement.executeUpdate();
            }
            connection.commit();
            return fetchUser(uniqueId);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void setLogout(UUID userId) throws SQLException, IOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getStatement("UserLogout"), Statement.RETURN_GENERATED_KEYS);
            Timestamp time = new Timestamp(new Date().getTime());
            statement.setTimestamp(1, time);
            statement.setTimestamp(2, time);
            statement.setString(3, userId.toString());
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    protected UserPersistence getThis() {
        return this;
    }
}
