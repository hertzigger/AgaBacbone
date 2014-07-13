package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class ItemStackHasEnchantmentPersistence extends MysqlPersistence<ItemStackHasEnchantmentPersistence> implements IItemStackHasEnchantmentPersistence {


    @Override
    protected ItemStackHasEnchantmentPersistence getThis() {
        return this;
    }
}
