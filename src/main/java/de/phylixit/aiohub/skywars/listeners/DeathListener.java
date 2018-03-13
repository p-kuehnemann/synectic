package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.GameState;
import de.phylixit.aiohub.skywars.gamestates.InGameState;
import de.phylixit.aiohub.skywars.utils.StatsManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent playerDeathEvent) {
        playerDeathEvent.setDeathMessage(null);
        if (SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof InGameState) {
            if(SkyWars.getInstance().players.contains(playerDeathEvent.getEntity().getPlayer())) {
                if (playerDeathEvent.getEntity().getKiller() != null) {



                    if(SkyWars.getInstance().players.size() >= 1)
                        SkyWars.getInstance().getGameStateManager().setGameState(GameState.RESTARTING_STATE);
                    Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Der Spieler §e" + playerDeathEvent.getEntity().getPlayer().getDisplayName() +
                            " §7wurde von §e" + playerDeathEvent.getEntity().getKiller().getDisplayName() + " §7getötet!");
                    StatsManager.addDeath(playerDeathEvent.getEntity().getPlayer());

                    SkyWars.getInstance().players.remove(playerDeathEvent.getEntity().getPlayer());
                    SkyWars.getInstance().spectators.add(playerDeathEvent.getEntity().getPlayer());
                    Bukkit.broadcastMessage("Spectators: " + SkyWars.getInstance().spectators.size());
                    Bukkit.broadcastMessage("Players: " + SkyWars.getInstance().players.size());

                    playerDeathEvent.getEntity().getPlayer().setAllowFlight(true);
                    playerDeathEvent.getEntity().getPlayer().setFlying(true);
                    for (Player player : SkyWars.getInstance().players)
                        player.hidePlayer(playerDeathEvent.getEntity().getPlayer());


                } else {


                    if (SkyWars.getInstance().players.size() >= 1)
                        SkyWars.getInstance().getGameStateManager().setGameState(GameState.RESTARTING_STATE);
                    Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Der Spieler §e" + playerDeathEvent.getEntity().getPlayer().getDisplayName() + " §7ist gestorben.");
                    StatsManager.addDeath(playerDeathEvent.getEntity().getPlayer());

                    SkyWars.getInstance().players.remove(playerDeathEvent.getEntity().getPlayer());
                    SkyWars.getInstance().spectators.add(playerDeathEvent.getEntity().getPlayer());
                    Bukkit.broadcastMessage("Spectators: " + SkyWars.getInstance().spectators.size());
                    Bukkit.broadcastMessage("Players: " + SkyWars.getInstance().players.size());

                    playerDeathEvent.getEntity().getPlayer().setAllowFlight(true);
                    playerDeathEvent.getEntity().getPlayer().setFlying(true);
                    for (Player player : SkyWars.getInstance().players)
                        player.hidePlayer(playerDeathEvent.getEntity().getPlayer());


                }
            }
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