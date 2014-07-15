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
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class UserHasIpAddressPersistence extends MysqlPersistence<UserHasIpAddressPersistence> implements IUserHasIpAddressPersistence {
    public static final String ROW_EXISTS = "SELECT idIpAddress, uuid FROM AgaBacbone.userHasIpAddress WHERE idIpAddress=? AND uuid=?";
    public static final String ADD_LINK = "INSERT INTO AgaBacbone.userHasIpAddress (idIpAddress, created, modified, uuid) VALUES (?,?,?,?)";

    @Override
    public Boolean rowExists(final int ipId, final UUID userId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(ROW_EXISTS);
            statement.setInt(1, ipId);
            statement.setString(2, userId.toString());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
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
    public int addOrFetch(final int ipId, final UUID userId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        int idRow = 0;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            if (!rowExists(ipId, userId)) {
                statement = connection.prepareStatement(ADD_LINK, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, ipId);
                statement = setModifiedAndCreated(statement, 2, 3);
                statement.setString(4, userId.toString());
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    idRow = rs.getInt(1);
                }
            }
            connection.commit();
            return idRow;
        } catch (SQLException e) {
            if (connection != null) {
                connection.rollback();
            }
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
    protected UserHasIpAddressPersistence getThis() {
        return this;
    }
}
