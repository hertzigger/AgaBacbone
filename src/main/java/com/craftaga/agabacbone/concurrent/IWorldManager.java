package com.craftaga.agabacbone.concurrent;

import org.bukkit.World;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public interface IWorldManager {
    void addWorld(World world, int id);

    int getWorldId(World world);

    AgaWorld getWorld(World world);
}
