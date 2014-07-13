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

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class SessionPersistence extends MysqlPersistence<SessionPersistence> implements ISessionPersistence {
    public static final String ADD_SESSION = "INSERT INTO agabacbone.session (login, created, modified, idWorld) " +
            "VALUES (?,?,?,?)";
    public static final String LOGOUT = "UPDATE agabacbone.session SET logout=?, modified=? WHERE idSession=?";

    @Override
    public int addSession(final Date date, final int worldId) throws SQLException, IOException {
        PreparedStatement statement = null;
        Connection connection = null;
        int sessionId = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getStatement("SessionAdd"), Statement.RETURN_GENERATED_KEYS);
            statement.setTimestamp(1, new Timestamp(new Date().getTime()));
            statement = setModifiedAndCreated(statement, 2, 3);
            statement.setInt(4, worldId);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                sessionId = rs.getInt(1);
            }
            connection.commit();
            return sessionId;
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
    public void logout(final int sessionId) throws SQLException, IOException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(getStatement("SessionUpdate"), Statement.RETURN_GENERATED_KEYS);
            Timestamp time = new Timestamp(new Date().getTime());
            statement.setTimestamp(1, time);
            statement.setTimestamp(2, time);
            statement.setInt(3, sessionId);
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
    protected SessionPersistence getThis() {
        return this;
    }
}
