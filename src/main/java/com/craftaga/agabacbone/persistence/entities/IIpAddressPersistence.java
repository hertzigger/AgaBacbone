package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IIpAddressPersistence extends IMysqlPersistence {
    int addOrFetch(InetAddress address) throws SQLException, IOException;

    void addIpAddress(String ipAddress) throws SQLException, IOException;

    int fetchIpAddressId(InetAddress ipAddress) throws SQLException, IOException;
}
