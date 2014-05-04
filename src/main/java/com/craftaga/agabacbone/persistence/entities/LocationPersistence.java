package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class LocationPersistence extends MysqlPersistence implements ILocationPersistence {
    public LocationPersistence(DataSource dataSource) {
        super(dataSource);
    }
}
