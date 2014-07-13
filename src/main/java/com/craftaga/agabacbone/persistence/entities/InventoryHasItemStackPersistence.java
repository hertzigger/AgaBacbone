package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class InventoryHasItemStackPersistence extends MysqlPersistence<InventoryHasItemStackPersistence> implements IInventoryHasItemStackPersistence {

    @Override
    protected InventoryHasItemStackPersistence getThis() {
        return this;
    }
}
