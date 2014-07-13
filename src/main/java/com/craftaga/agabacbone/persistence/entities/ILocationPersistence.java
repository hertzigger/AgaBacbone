package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.io.IOException;
import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface ILocationPersistence extends IMysqlPersistence {
    int addLocation(double x, double y, double z, float pitch, float yaw, int sessionId) throws SQLException, IOException;
}
