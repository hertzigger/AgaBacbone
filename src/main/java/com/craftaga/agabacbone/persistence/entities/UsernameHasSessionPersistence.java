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

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class UsernameHasSessionPersistence extends MysqlPersistence<UsernameHasSessionPersistence> implements IUsernameHasSessionPersistence {
    public static final String FETCH_ROW = "SELECT idUsername FROM AgaBacbone.usernameHasSession WHERE idUsername=? AND idSession=?";
    public static final String ADD_ROW = "INSERT INTO AgaBacbone.usernameHasSession (idUsername, created, modified, idSession) " +
            " VALUES (?,?,?,?)";

    @Override
    public boolean rowExists(final int usernameId, final int sessionId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        int rowId = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(FETCH_ROW);
            statement.setInt(1, usernameId);
            statement.setInt(2, sessionId);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return true;
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
        return false;
    }

    @Override
    public void add(final int usernameId, final int sessionId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            if (!rowExists(usernameId, sessionId)) {
                statement = connection.prepareStatement(ADD_ROW, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, usernameId);
                statement = setModifiedAndCreated(statement, 2, 3);
                statement.setInt(4, sessionId);
                statement.executeUpdate();
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
    }

    @Override
    protected UsernameHasSessionPersistence getThis() {
        return this;
    }
}
