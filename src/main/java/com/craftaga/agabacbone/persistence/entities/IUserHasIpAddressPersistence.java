package com.craftaga.agabacbone.persistence.entities;

import java.sql.SQLException;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IUserHasIpAddressPersistence {
    int addOrFetch(int ipId, UUID userId) throws SQLException;

    Boolean rowExists(int ipId, UUID userId) throws SQLException;
}
