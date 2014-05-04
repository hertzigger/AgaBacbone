package com.craftaga.agabacbone.persistence;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
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

    private DataSource dataSource;

    public MysqlPersistence(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    protected PreparedStatement setModifiedAndCreated(final PreparedStatement preparedStatement, final int creationField, final int modifiedField) throws SQLException {
        PreparedStatement newStatement = preparedStatement;
        newStatement.setTimestamp(creationField, new Timestamp(new java.util.Date().getTime()));
        newStatement.setTimestamp(modifiedField, new Timestamp(new java.util.Date().getTime()));
        return newStatement;
    }
}
