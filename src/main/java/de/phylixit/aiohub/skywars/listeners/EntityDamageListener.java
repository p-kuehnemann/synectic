package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.gamestates.RestartingState;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener implements Listener {
    @EventHandler
    public void onEntityDamage(EntityDamageEvent damageEvent) {
        if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState ||
                SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof RestartingState) {
            damageEvent.setCancelled(true);
        }else{
            if(damageEvent.getEntity() instanceof Player) {
                if(!TeamManager.isPlayerInValidTeam((Player) damageEvent.getEntity()))
                    damageEvent.setCancelled(true);
            }
        }
    }
}
