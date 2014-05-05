package com.craftaga.agabacbone.persistence;

import com.jolbox.bonecp.BoneCPDataSource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public class MysqlPersistence implements IMysqlPersistence {

    private BoneCPDataSource dataSource;

    public MysqlPersistence(BoneCPDataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    @Override
    public BoneCPDataSource getDataSource() {
        return dataSource;
    }

    protected PreparedStatement setModifiedAndCreated(final PreparedStatement preparedStatement, final int creationField, final int modifiedField) throws SQLException {
        PreparedStatement newStatement = preparedStatement;
        newStatement.setTimestamp(creationField, new Timestamp(new java.util.Date().getTime()));
        newStatement.setTimestamp(modifiedField, new Timestamp(new java.util.Date().getTime()));
        return newStatement;
    }
}
