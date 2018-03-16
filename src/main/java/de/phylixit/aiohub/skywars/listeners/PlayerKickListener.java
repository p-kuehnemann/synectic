package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import de.phylixit.aiohub.skywars.utils.ScoreboardManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;

public class PlayerKickListener implements Listener {
    @EventHandler
    public void onKickPlayer(PlayerKickEvent playerKickEvent) {
        ScoreboardManager.reload();
        TeamManager.removePlayerFromTeam(playerKickEvent.getPlayer());

        if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
            LobbyState lobbyState = (LobbyState) SkyWars.getInstance().getGameStateManager().getCurrentGameState();
            SkyWars.getInstance().players.remove(playerKickEvent.getPlayer());
            playerKickEvent.setLeaveMessage(SkyWars.getInstance().getPrefix() + "§c" + playerKickEvent.getPlayer().getName());
            SkyWars.getInstance().playerKits.remove(playerKickEvent.getPlayer());
            if(SkyWars.getInstance().players.size() < 2)
                lobbyState.getLobbyCountdown().cancel();
        } else {
            SkyWars.getInstance().spectators.remove(playerKickEvent.getPlayer());
            playerKickEvent.setLeaveMessage(null);
        }
    }
}
