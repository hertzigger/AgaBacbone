package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.concurrent.IWorldManager;
import com.craftaga.agabacbone.concurrent.WorldManager;
import com.craftaga.agabacbone.persistence.MysqlPersistence;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPDataSource;
import org.bukkit.World;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public class WorldPersistence extends MysqlPersistence implements IWorldPersistence {

    public static final String CREATE_WORLD = "insert into world (name, created, modified, idServer)" +
            " VALUES (?,?,?,?)";
    public static final String WORLD_EXISTS = "select idWorld from World WHERE name=? AND idServer=?";

    public WorldPersistence(BoneCPDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void addWorlds(List<org.bukkit.World> worlds, int serverId) throws SQLException {
        PreparedStatement insert = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            for (org.bukkit.World world : worlds) {
                if (fetchWorldId(world.getName(), serverId) != 0) {
                    insert = connection.prepareStatement(CREATE_WORLD);
                    insert.setString(1, world.getName());
                    insert = setModifiedAndCreated(insert, 2, 3);
                    insert.setInt(4, serverId);
                    insert.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (insert != null) {
                insert.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public int fetchWorldId(String worldName, int serverId) throws SQLException {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(WORLD_EXISTS);
            statement.setString(1, worldName);
            statement.setInt(2, serverId);
            ResultSet resultSet = statement.executeQuery();
            connection.commit();
            while (resultSet.next()) {
                return resultSet.getInt("idWorld");
            }
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return 0;
    }

    @Override
    public IWorldManager addOrFetch(List<World> worlds, int serverId) throws SQLException {
        PreparedStatement insert = null;
        Connection connection = null;
        IWorldManager worldManager = new WorldManager();
        try {
            connection = getDataSource().getConnection();
            connection.setAutoCommit(false);
            for (org.bukkit.World world : worlds) {
                worldManager.addWorld(world, 0);
                int worldId = fetchWorldId(world.getName(), serverId);
                if (worldId == 0) {
                    insert = connection.prepareStatement(CREATE_WORLD, Statement.RETURN_GENERATED_KEYS);
                    insert.setString(1, world.getName());
                    insert = setModifiedAndCreated(insert, 2, 3);
                    insert.setInt(4, serverId);
                    insert.executeUpdate();
                    ResultSet rs = insert.getGeneratedKeys();
                    if (rs.next()) {
                        worldManager.getWorld(world).setId(rs.getInt(1));
                    }
                } else {
                    worldManager.getWorld(world).setId(worldId);
                }
            }
            connection.commit();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (insert != null) {
                insert.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return worldManager;
    }
}
