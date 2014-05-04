package com.craftaga.agabacbone.persistence.entities;

import java.sql.SQLException;
import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IUsernamePersistence {
    int addOrFetch(UUID userId, String name) throws SQLException;

    int fetchUsername(UUID userId, String name) throws SQLException;
}
