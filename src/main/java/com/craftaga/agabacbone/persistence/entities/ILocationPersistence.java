package com.craftaga.agabacbone.persistence.entities;

import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface ILocationPersistence {
    int addLocation(double x, double y, double z, float pitch, float yaw, int sessionId) throws SQLException;
}
