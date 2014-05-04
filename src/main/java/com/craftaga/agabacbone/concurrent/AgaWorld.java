package com.craftaga.agabacbone.concurrent;

import org.bukkit.World;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class AgaWorld implements IAgaWorld {
    private World world;
    private int id;

    public AgaWorld(World world, int Id)
    {
        this.world = world;
        this.id = id;
    }

    public World getWorld() {
        return world;
    }

    public int getId() {
        return id;
    }

    @Override
    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
}
