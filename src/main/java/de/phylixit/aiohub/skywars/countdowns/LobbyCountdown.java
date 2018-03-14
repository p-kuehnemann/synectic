package de.phylixit.aiohub.skywars.countdowns;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.GameState;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

	private boolean isRunning = false;
	private int seconds = 15;
	
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
