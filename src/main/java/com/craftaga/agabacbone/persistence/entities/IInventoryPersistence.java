package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.IMysqlPersistence;

import java.sql.SQLException;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public interface IInventoryPersistence extends IMysqlPersistence {
    int add() throws SQLException;
}
