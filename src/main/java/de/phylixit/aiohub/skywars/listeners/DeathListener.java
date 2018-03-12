package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.InGameState;
import de.phylixit.aiohub.skywars.utils.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        playerDeathEvent.setDeathMessage(null);
        if (SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof InGameState) {
            if (playerDeathEvent.getEntity().getKiller() != null) {
                Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Der Spieler §e" + playerDeathEvent.getEntity().getPlayer().getDisplayName() +
                        " §7wurde von §e" + playerDeathEvent.getEntity().getKiller().getDisplayName() + " §7getötet!");
                StatsManager.addKill(playerDeathEvent.getEntity().getKiller());
                StatsManager.addDeath(playerDeathEvent.getEntity().getPlayer());
                playerDeathEvent.getEntity().getPlayer().spigot().respawn();
                SkyWars.getInstance().players.remove(playerDeathEvent.getEntity().getPlayer());
                SkyWars.getInstance().spectators.add(playerDeathEvent.getEntity().getPlayer());
            }
        } else {
            Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Der Spieler §e" + playerDeathEvent.getEntity().getPlayer().getDisplayName() + " §7ist gestorben.");
            StatsManager.addDeath(playerDeathEvent.getEntity().getPlayer());
        }
    }
}
/*
    if (SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof InGameState) {
            if (NickAPI.getInstance().isNicked(playerDeathEvent.getEntity().getUniqueId())) {
                NickSession nickSession = NickAPI.getInstance().getNickSession(playerDeathEvent.getEntity().getUniqueId());
                if (nickSession.isNickDeath()) {
                    return;
                }
            } else {

            }
        }
 */