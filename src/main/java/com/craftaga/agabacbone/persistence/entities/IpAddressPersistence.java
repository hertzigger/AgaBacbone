package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.concurrent.IWorldManager;
import com.craftaga.agabacbone.concurrent.WorldManager;
import com.craftaga.agabacbone.persistence.MysqlPersistence;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public class IpAddressPersistence extends MysqlPersistence implements IIpAddressPersistence {

    public static final String IP_ADDRESS_EXISTS = "select idIpAddress from ipAddress WHERE address=?";
    public static final String ADD_IP_ADDRESS = "insert into ipAddress (address, created, modified) VALUES (?,?,?)";

    public IpAddressPersistence(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addIpAddress(String ipAddress) throws SQLException {
        PreparedStatement insert = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            insert = connection.prepareStatement(IP_ADDRESS_EXISTS);
            insert.setString(1, ipAddress);
            insert.setDate(2, new Date(new java.util.Date().getTime()));
            insert.setDate(3, new Date(new java.util.Date().getTime()));
            insert.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (insert != null) {
                insert.close();
            }
            if (connection != null) {
                connection.rollback();
            }
        }
    }

    @Override
    public int fetchIpAddressId(InetAddress ipAddress) throws SQLException
    {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(IP_ADDRESS_EXISTS);
            statement.setString(1, ipAddress.getHostAddress());
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return resultSet.getInt("idIpAddress");
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
        return 0;
    }

    @Override
    public int addOrFetch(InetAddress address) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            int ipId = fetchIpAddressId(address);
            if (ipId == 0) {
                statement = connection.prepareStatement(ADD_IP_ADDRESS, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, address.getHostAddress());
                statement = setModifiedAndCreated(statement, 2, 3);
                statement.executeUpdate();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    ipId = rs.getInt(1);
                }
            }
            connection.commit();
            return ipId;
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
