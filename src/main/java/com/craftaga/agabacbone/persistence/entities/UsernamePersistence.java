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
 * @since 04/05/14
 */
public class UsernamePersistence extends MysqlPersistence implements IUsernamePersistence {
    public static final String FETCH_USERNAME = "SELECT idUsername FROM username WHERE username=? AND uuid=?";
    public static final String ADD_USERNAME = "INSERT INTO username (username, created, modified, firstLogin, uuid)" +
            " VALUES (?,?,?,?,?)";
    public static final String LOGOUT = "UPDATE username SET lastLogout=?, modified=? WHERE idUsername=?";


    public UsernamePersistence(BoneCPDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int fetchUsername(final UUID userId, final String name) throws SQLException
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(FETCH_USERNAME);
            statement.setString(1, name);
            statement.setString(2, userId.toString());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return resultSet.getInt("idUsername");
            }
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
        return 0;
    }

    @Override
    public void setLogout(int usernameId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(LOGOUT, Statement.RETURN_GENERATED_KEYS);
            Timestamp time = new Timestamp(new Date().getTime());
            statement.setTimestamp(1, time);
            statement.setTimestamp(2, time);
            statement.setInt(3, usernameId);
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
    public int addOrFetch(final UUID userId, final String name) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            int usernameId = fetchUsername(userId, name);
            if (usernameId == 0) {
                statement = connection.prepareStatement(ADD_USERNAME, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, name);
                statement = setModifiedAndCreated(statement, 2, 3);
                statement.setTimestamp(4, new Timestamp(new Date().getTime()));
                statement.setString(5, userId.toString());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    usernameId = rs.getInt(1);
                }
            }
            connection.commit();
            return usernameId;
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
