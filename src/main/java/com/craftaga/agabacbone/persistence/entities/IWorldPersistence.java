package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.concurrent.IWorldManager;
import com.craftaga.agabacbone.persistence.IMysqlPersistence;
import org.bukkit.*;
import org.bukkit.World;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IWorldPersistence extends IMysqlPersistence {
    void addWorlds(List<World> worlds, int serverId) throws SQLException, IOException;

    int fetchWorldId(String worldName, int serverId) throws SQLException, IOException;

    IWorldManager addOrFetch(List<World> worlds, int serverId) throws SQLException, IOException;
}
