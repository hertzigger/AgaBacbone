package com.craftaga.agabacbone.persistence.entities;

import java.net.InetAddress;
import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IIpAddressPersistence {
    int addOrFetch(InetAddress address) throws SQLException;

    void addIpAddress(String ipAddress) throws SQLException;

    int fetchIpAddressId(InetAddress ipAddress) throws SQLException;
}
