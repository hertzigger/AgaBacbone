package com.craftaga.agabacbone.persistence.entities;

import com.craftaga.agabacbone.concurrent.IWorldManager;
import org.bukkit.*;
import org.bukkit.World;

import java.sql.SQLException;
import java.util.List;

/**
 * description
 *
 * @author Jonathan
 * @since 03/05/14
 */
public interface IWorldPersistence {
    void addWorlds(List<World> worlds, int serverId) throws SQLException;

    int fetchWorldId(String worldName, int serverId) throws SQLException;

    IWorldManager addOrFetch(List<World> worlds, int serverId) throws SQLException;
}
