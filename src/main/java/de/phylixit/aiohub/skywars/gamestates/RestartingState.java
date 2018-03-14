package de.phylixit.aiohub.skywars.gamestates;

import de.phylixit.aiohub.skywars.countdowns.RestartingCountdown;

public class RestartingState extends GameState {

	@Override
	public void start() {
		RestartingCountdown restartingCountdown = new RestartingCountdown();
		restartingCountdown.run();
	}

	@Override
	public void stop() {}

}
