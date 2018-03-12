package de.phylixit.aiohub.skywars.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawnListener {

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent creatureSpawnEvent) { creatureSpawnEvent.setCancelled(true); }
}
