package de.phylixit.aiohub.skywars.gamestates;

import de.phylixit.aiohub.skywars.teams.TeamManager;

public class InGameState extends GameState {

	@Override
	public void start() {
		TeamManager.teleportTeams();
	}

	@Override
	public void stop() { }
}
