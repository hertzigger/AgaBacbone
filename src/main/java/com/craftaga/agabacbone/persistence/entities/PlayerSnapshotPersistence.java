package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCPDataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class PlayerSnapshotPersistence extends MysqlPersistence<PlayerSnapshotPersistence> implements IPlayerSnapshotPersistence {

    @Override
    protected PlayerSnapshotPersistence getThis() {
        return this;
    }
}
