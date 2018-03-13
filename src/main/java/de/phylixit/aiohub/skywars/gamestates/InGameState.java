package de.phylixit.aiohub.skywars.gamestates;

import de.phylixit.aiohub.skywars.teams.TeamManager;
import org.bukkit.Bukkit;

public class InGameState extends GameState {

	@Override
	public void start() {
		Bukkit.broadcastMessage("§c§lDer InGame State hat begonne!");
		TeamManager.teleportTeams();
	}

	@Override
	public void stop() {
		Bukkit.broadcastMessage("§c§lDer InGame State hat gestoppt!");
	}
}
