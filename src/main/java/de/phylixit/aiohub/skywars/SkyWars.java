package de.phylixit.aiohub.skywars;

import de.phylixit.aiohub.skywars.commands.locationsCommand;
import de.phylixit.aiohub.skywars.commands.statsCommand;
import de.phylixit.aiohub.skywars.gamestates.GameState;
import de.phylixit.aiohub.skywars.gamestates.GameStateManager;
import de.phylixit.aiohub.skywars.kits.Kits;
import de.phylixit.aiohub.skywars.listeners.*;
import net.aiohub.utilities.stats.StatsAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class SkyWars extends JavaPlugin {
	private static SkyWars instance;
	private GameStateManager gameStateManager;
	public HashMap<Player, Kits> playerKits = new HashMap<>();
	public ArrayList<Player> players = new ArrayList<>();
	public ArrayList<Player> spectators = new ArrayList<>();
	
	@Override
	public void onEnable() {
		instance = this;
		registerListeners();
		registerCommands();

		gameStateManager = new GameStateManager();
		gameStateManager.setGameState(GameState.LOBBY_STATE);

		StatsAPI.getInstance().setGameMode("skywars");
		StatsAPI.getInstance().setDefaultValues(Arrays.asList("kills", "deaths", "wins", "games"));
	}

	@Override
	public void onDisable() {}
	
	public static SkyWars getInstance() { return instance; }
	public String getPrefix() { return "§8┃ §6§lSkyWars §8┃ » §7"; }
	public GameStateManager getGameStateManager() { return gameStateManager; }
	private void registerListeners() {
		Bukkit.getPluginManager().registerEvents(new AsyncPlayerChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), this);
		Bukkit.getPluginManager().registerEvents(new CreatureSpawnListener(), this);
		Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageByEntityListener(), this);
		Bukkit.getPluginManager().registerEvents(new EntityDamageListener(), this);
		Bukkit.getPluginManager().registerEvents(new FoodLevelChangeListener(), this);
		Bukkit.getPluginManager().registerEvents(new InventoryListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerKickListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(), this);
		Bukkit.getPluginManager().registerEvents(new WeatherChangeListener(), this);
	}
	private void registerCommands() {
		getCommand("location").setExecutor(new locationsCommand());
		getCommand("stats").setExecutor(new statsCommand());
	}
}
