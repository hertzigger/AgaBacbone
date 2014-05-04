package com.craftaga.agabacbone;

import org.bukkit.entity.Player;
import org.junit.Test;

import java.util.UUID;

import static junit.framework.Assert.assertEquals;

/**
 * description
 *
 * @author Jonathan
 * @since 13/04/14
 */
public class PlayerNameResolverTest {
    private String playerName = "hertzigger";
    private UUID uuid = UUID.fromString("3c695f9a-40cf-4468-ad56-40b891555b48");
    private PlayerNameResolver playerNameResolver = new PlayerNameResolver();

    @Test
    public void testGetUniqueId() throws Exception
    {
        UUID actualValue = playerNameResolver.getUniqueId(playerName);
        UUID expectedValue = uuid;
        assertEquals(expectedValue, actualValue);
    }
}
