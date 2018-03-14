package de.phylixit.aiohub.skywars.gamestates;

import de.phylixit.aiohub.skywars.countdowns.LobbyCountdown;

public class LobbyState extends GameState {
	private LobbyCountdown lobbyCountdown;
	
	@Override
	public void start() { lobbyCountdown = new LobbyCountdown(); }

	@Override
	public void stop() {}
	public LobbyCountdown getLobbyCountdown() { return lobbyCountdown;}
}
