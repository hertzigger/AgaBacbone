package com.craftaga.agabacbone;

import java.util.UUID;

/**
 * description
 *
 * @author Jonathan
 * @since 13/04/14
 */
public interface IPlayerNameResolver {
    UUID getUniqueId(String playerName);
}
