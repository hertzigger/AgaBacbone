package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class KillPersistence extends MysqlPersistence<KillPersistence> implements IKillPersistence {

    @Override
    protected KillPersistence getThis() {
        return this;
    }
}
