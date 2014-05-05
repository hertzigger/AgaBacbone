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
public class LocationPersistence extends MysqlPersistence implements ILocationPersistence {
    public static final String LOG_LOCATION = "INSERT INTO location (x, y, z, pitch, yaw, created, modified, idSession)" +
            "VALUES (?,?,?,?,?,?,?,?)";

    public LocationPersistence(BoneCPDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int addLocation(
            final double x,
            final double y,
            final double z,
            final float pitch,
            final float yaw,
            final int sessionId
    ) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        int locationId = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(LOG_LOCATION, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, x);
            statement.setDouble(2, y);
            statement.setDouble(3, z);
            statement.setDouble(4, pitch);
            statement.setDouble(5, yaw);
            statement = setModifiedAndCreated(statement, 6, 7);
            statement.setInt(8, sessionId);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                locationId = rs.getInt(1);
            }
            connection.commit();
            return locationId;
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
