package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;
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
public class UserPersistence extends MysqlPersistence implements IUserPersistence {
    public static final String ADD_USER = "INSERT INTO user (uuid, created, modified, firstLogin)" +
            " VALUES (?,?,?,?)";
    public static final String FETCH_USER = "SELECT uuid FROM user WHERE uuid=?";
    public static final String LOGOUT = "UPDATE user SET lastLogout=?, modified=? WHERE uuid=?";

    public UserPersistence(BoneCPDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public UUID fetchUser(UUID uuid) throws SQLException
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(FETCH_USER);
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
    public UUID login(UUID uniqueId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        UUID returnUuid = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            returnUuid = fetchUser(uniqueId);
            if (returnUuid == null) {
                statement = connection.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, uniqueId.toString());
                statement = setModifiedAndCreated(statement, 2, 3);
                statement.setTimestamp(4, new Timestamp(new Date().getTime()));
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    returnUuid = UUID.fromString(rs.getString(1));
                }
            }
            connection.commit();
            return returnUuid;
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
    public void setLogout(UUID userId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(LOGOUT, Statement.RETURN_GENERATED_KEYS);
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
}
