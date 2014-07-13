package com.craftaga.agabacbone.persistence;

import com.craftaga.agabacbone.concurrent.PluginManager;
import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public abstract class MysqlPersistence<T extends MysqlPersistence> implements IMysqlPersistence {

    private BoneCPDataSource dataSource;
    private String databaseName;
    private PluginManager plugin;
    private static final String SQL_EXTENSION = ".sql";
    private static final String STATEMENT_DIRECTORY = "/mysql/statements/";

    @Override
    public BoneCPDataSource getDataSource() {
        return dataSource;
    }

    public T setDataSource(BoneCPDataSource dataSource) {
        this.dataSource = dataSource;
        return getThis();
    }

    protected PreparedStatement setModifiedAndCreated(final PreparedStatement preparedStatement, final int creationField, final int modifiedField) throws SQLException {
        PreparedStatement newStatement = preparedStatement;
        newStatement.setTimestamp(creationField, new Timestamp(new java.util.Date().getTime()));
        newStatement.setTimestamp(modifiedField, new Timestamp(new java.util.Date().getTime()));
        return newStatement;
    }

    public PreparedStatement updateQuery(final Connection connection, final PreparedStatement preparedStatement) throws SQLException {
        Integer rowsAffected = 0;
        if (preparedStatement != null && connection != null) {
            try {
                connection.setAutoCommit(false);
                rowsAffected = preparedStatement.executeUpdate();
                connection.commit();
                if (rowsAffected < 1) {
                    throw new NoRowsAffectedException("The query affected no rows");
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }
        } else {
            throw new IllegalArgumentException("Prepared Statement Cannot be null");
        }
        return preparedStatement;
    }

    @Override
    public String getDatabaseName() {
        return databaseName;
    }

    @Override
    public T setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
        return getThis();
    }


    @Override
    public void setPlugin(PluginManager pluginManager) {
        plugin = pluginManager;
    }

    @Override
    public PluginManager getPlugin() {
        return plugin;
    }

    @Override
    public String getStatement(String name) throws IOException {
        if (this.getClass().getResource(STATEMENT_DIRECTORY + name + SQL_EXTENSION) != null) {
            return IOUtils.toString(this.getClass().getResourceAsStream(STATEMENT_DIRECTORY + name + SQL_EXTENSION), "UTF-8");
        } else {
            throw new IOException(STATEMENT_DIRECTORY + name + SQL_EXTENSION + " does not exist");
        }
    }

    protected abstract T getThis();
}
