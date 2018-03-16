package de.phylixit.aiohub.skywars.gamestates;

public class GameStateManager {

	private GameState[] gameStates = new GameState[3];
	private GameState currentGameState;
	public GameStateManager() {
		gameStates[GameState.LOBBY_STATE] = new LobbyState();
		gameStates[GameState.INGAME_STATE] = new InGameState();
		gameStates[GameState.RESTARTING_STATE] = new RestartingState();
	}
	public void setGameState(int newGameState) {
		if(currentGameState != null)
			currentGameState.stop();
		currentGameState = gameStates[newGameState];
		currentGameState.start();
	}
	public GameState getCurrentGameState() { return currentGameState; }
}
//public void stopCurrentGameState() { currentGameState.stop(); currentGameState = null; }