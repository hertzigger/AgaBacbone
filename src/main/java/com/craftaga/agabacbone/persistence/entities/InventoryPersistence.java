package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.craftaga.agabacbone.persistence.NoRowsAffectedException;
import com.jolbox.bonecp.BoneCPDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

/**
 * description
 *
 * @author Jonathan
 * @since 05/05/14
 */
public class InventoryPersistence extends MysqlPersistence<InventoryPersistence> implements IInventoryPersistence {
    private static final String INSERT = "INSERT INTO agabacbone.inventory (created) VALUES (?)";

    @Override
    public int add() throws SQLException {
        Connection conn = getDataSource().getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
        ResultSet rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    protected InventoryPersistence getThis() {
        return this;
    }
}
