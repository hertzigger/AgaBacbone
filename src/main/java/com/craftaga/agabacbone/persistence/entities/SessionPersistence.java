package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;

import javax.sql.DataSource;
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
public class SessionPersistence extends MysqlPersistence implements ISessionPersistence {
    public static final String ADD_SESSION = "INSERT INTO session (login, created, modified, idWorld) " +
            "VALUES (?,?,?,?)";

    public SessionPersistence(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int addSession(final Date date,final int worldId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        int sessionId = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ADD_SESSION, Statement.RETURN_GENERATED_KEYS);
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
                connection.rollback();
            }
        }
    }
}
