package com.craftaga.agabacbone.persistence;

import com.craftaga.agabacbone.concurrent.PluginManager;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IMysqlPersistence {
    BoneCPDataSource getDataSource();

    String getDatabaseName();

    MysqlPersistence setDatabaseName(String databaseName);

    void setPlugin(PluginManager pluginManager);

    PluginManager getPlugin();

    String getStatement(String name) throws IOException;

    MysqlPersistence setDataSource(BoneCPDataSource dataSource);
}
