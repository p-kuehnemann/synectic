package de.phylixit.aiohub.skywars.countdowns;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.GameState;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import de.phylixit.aiohub.skywars.utils.ScoreboardManager;
import net.aiohub.utilities.utils.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyCountdown extends Countdown {
	private boolean isRunning = false;
	private int seconds = 30;
	
	@Override
	public void run() {
		isRunning = true;
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), new Runnable() {
			@Override
			public void run() {
					if(seconds == 60 || seconds == 30 || seconds == 15 || seconds == 10 || seconds == 5 || seconds == 4 || seconds == 3 || seconds == 2) {
						Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel startet in §e" + seconds + "§7 Sekunden.");
					} else if(seconds == 1) {
						Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel startet in §eeiner§7 Sekunde.");
					} else if(seconds == 0) {
						SkyWars.getInstance().getGameStateManager().setGameState(GameState.INGAME_STATE);
					}
				seconds--;
				ScoreboardManager.reload();
				for(Player all : Bukkit.getOnlinePlayers())
					ScoreboardAPI.getInstance().sendScoreboard(all, "§6SkyWars", "§6AIOHub.net", "§1", "Map§8: ", "  §eVote-Phase", "§2", "Team§8: ", "  §a" + TeamManager.getTeam(all).getName());
			}
		}, 0, 20);
	}

	@Override
	public void cancel() {
		isRunning = false;
		Bukkit.getScheduler().cancelTask(taskID);
		if(seconds <= 15)
			seconds = 15;
	}
	public boolean isRunning() { return isRunning; }
}
