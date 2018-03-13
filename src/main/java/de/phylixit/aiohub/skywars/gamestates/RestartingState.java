package de.phylixit.aiohub.skywars.gamestates;

import de.phylixit.aiohub.skywars.countdowns.RestartingCountdown;

public class RestartingState extends GameState {

	private RestartingCountdown restartingCountdown;

	@Override
	public void start() {
		restartingCountdown = new RestartingCountdown();
		restartingCountdown.run();
	}

	@Override
	public void stop() {}

}
