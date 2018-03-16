package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.gamestates.RestartingState;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent entityEvent) {
        if(entityEvent.getEntity() instanceof Player && entityEvent.getDamager() instanceof  Player) {
            if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState ||
                    SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof RestartingState) {
                entityEvent.setCancelled(true);
            } else {
                Player player = (Player) entityEvent.getEntity();
                Player targetPlayer = (Player) entityEvent.getDamager();
                if(!TeamManager.isPlayerInValidTeam(targetPlayer) || !TeamManager.isPlayerInValidTeam(player)) {
                    entityEvent.setCancelled(true);
                    return;
                }
                if(SkyWars.getInstance().spectators.contains(player)) { entityEvent.setCancelled(true); }
            }
        }
    }
}
