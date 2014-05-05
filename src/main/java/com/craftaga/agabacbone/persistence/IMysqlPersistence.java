package com.craftaga.agabacbone.persistence;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IMysqlPersistence {
    BoneCPDataSource getDataSource();
}
