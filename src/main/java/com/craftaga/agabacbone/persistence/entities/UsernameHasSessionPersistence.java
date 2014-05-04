package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;

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
public class UsernameHasSessionPersistence extends MysqlPersistence implements IUsernameHasSessionPersistence {
    public static final String FETCH_ROW = "SELECT FROM usernameHasSession WHERE idUserName=? AND idSession=?";
    public static final String ADD_ROW = "INSERT INTO usernameHasSession (idUsername, created, modified, idSession) " +
            " VALUES (?,?,?,?)";

    public UsernameHasSessionPersistence(DataSource dataSource) {
        super(dataSource);
    }

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
            ResultSet resultSet = statement.executeQuery();
            statement.setInt(4, sessionId);
            connection.commit();
            while (resultSet.next()) {
                return true;
            }
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.rollback();
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
                connection.rollback();
            }
        }
    }
}
