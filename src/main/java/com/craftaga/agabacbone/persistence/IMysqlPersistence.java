package com.craftaga.agabacbone.persistence;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IMysqlPersistence {
    DataSource getDataSource();
}
