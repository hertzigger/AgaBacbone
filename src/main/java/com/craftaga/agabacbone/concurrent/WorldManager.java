package com.craftaga.agabacbone.concurrent;

import com.craftaga.agabacbone.concurrent.schedule.IPlayerQueueConstructor;
import com.craftaga.agabacbone.concurrent.schedule.IPlayerScheduledTimerHandler;
import org.bukkit.World;

import java.util.HashMap;

/**
 * description
 *
 * @author Jonathan
 * @since 04/05/14
 */
public class WorldManager implements IWorldManager {
    private HashMap<String, AgaWorld> worldHashMap;

    public WorldManager()
    {
        this.worldHashMap = new HashMap<String, AgaWorld>();
    }
    @Override
    public void addWorld(World world, int id)
    {
        AgaWorld agaWorld = new AgaWorld(world, id);
        worldHashMap.put(world.getName(), agaWorld);
    }

    @Override
    public int getWorldId(World world)
    {
        return worldHashMap.get(world.getName()).getId();
    }

    @Override
    public AgaWorld getWorld(World world)
    {
        return worldHashMap.get(world.getName());
    }

}
