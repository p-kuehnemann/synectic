package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener{

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent playerQuitEvent) {
        TeamManager.removePlayerFromTeam(playerQuitEvent.getPlayer());

        if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) SkyWars.getInstance().getGameStateManager().getCurrentGameState();
            SkyWars.getInstance().players.remove(playerQuitEvent.getPlayer());
            playerQuitEvent.setQuitMessage(SkyWars.getInstance().getPrefix() + "§c" + playerQuitEvent.getPlayer().getDisplayName());
            SkyWars.getInstance().playerKits.remove(playerQuitEvent.getPlayer());
            if(SkyWars.getInstance().players.size() < 2)
                lobbyState.getLobbyCountdown().cancel();
        } else {
            SkyWars.getInstance().spectators.remove(playerQuitEvent.getPlayer());
            playerQuitEvent.setQuitMessage(null);
        }
    }
}
