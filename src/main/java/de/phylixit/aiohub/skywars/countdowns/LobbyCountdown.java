package de.phylixit.aiohub.skywars.countdowns;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.GameState;
import org.bukkit.Bukkit;

public class LobbyCountdown extends Countdown {

	private boolean isRunning = false;
	private int seconds = 60;
	
	@Override
	public void run() {
		isRunning = true;
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), new Runnable() {
			@Override
			public void run() {
				switch(seconds) {
				case 60: case 30: case 15: case 10: case 5: case 4: case 3: case 2:
					Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel startet in §e" + seconds + "§7 Sekunden.");
					break;
				case 1:
					Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel startet in §eeiner§7 Sekunde.");
					break;
				case 0:
					SkyWars.getInstance().getGameStateManager().setGameState(GameState.INGAME_STATE);
					break;
				default:
					break;
				}
				seconds--;
			}
		}, 0, 20);
	}

	@Override
	public void cancel() {
		isRunning = false;
		Bukkit.getScheduler().cancelTask(taskID);
		seconds = 15;
	}
	public boolean isRunning() { return isRunning; }
}
