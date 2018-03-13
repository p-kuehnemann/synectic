package de.phylixit.aiohub.skywars.gamestates;

import de.phylixit.aiohub.skywars.countdowns.RestartingCountdown;
import org.bukkit.Bukkit;

public class RestartingState extends GameState {

	private RestartingCountdown restartingCountdown;

	@Override
	public void start() {
		restartingCountdown = new RestartingCountdown();
		Bukkit.broadcastMessage("§c§lDer Restarting State hat begonne!");
		restartingCountdown.run();
	}

	@Override
	public void stop() {
		Bukkit.broadcastMessage("§c§lDer Restarting State hat gestoppt!");
	}

}
