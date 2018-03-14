package de.phylixit.aiohub.skywars.listeners;

import de.dytanic.cloudnet.bridge.internal.util.ItemStackBuilder;
import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.InGameState;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.kits.Kits;
import de.phylixit.aiohub.skywars.utils.LocationManager;
import net.aiohub.utilities.stats.StatsAPI;
import net.aiohub.utilities.utils.*;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent playerJoinEvent) {
			playerJoinEvent.getPlayer().setHealth(20);
			playerJoinEvent.getPlayer().setFoodLevel(20);
			playerJoinEvent.getPlayer().setFireTicks(0);
			playerJoinEvent.getPlayer().setExp(0);
			playerJoinEvent.getPlayer().setLevel(0);
			playerJoinEvent.getPlayer().getInventory().clear();
			playerJoinEvent.getPlayer().getInventory().setArmorContents(null);
			playerJoinEvent.getPlayer().setGameMode(GameMode.SURVIVAL);
			playerJoinEvent.getPlayer().getWorld().setDifficulty(Difficulty.EASY);
			playerJoinEvent.getPlayer().getWorld().setStorm(false);
			playerJoinEvent.getPlayer().getWorld().setThundering(false);

			if(LocationManager.isLocationSet("spawn")) {
				playerJoinEvent.getPlayer().teleport(LocationManager.getLocationConfig("spawn"));
			} else {
				playerJoinEvent.getPlayer().sendMessage(SkyWars.getInstance().getPrefix() + "Der Spawn wurde noch nicht gesetzt. Bitte kontaktiere einen §cManager§7.");
				playerJoinEvent.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
			}

			if (SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
				if(!(StatsAPI.getInstance().isRegistered(playerJoinEvent.getPlayer().getUniqueId())))
				StatsAPI.getInstance().register(playerJoinEvent.getPlayer().getUniqueId());

				ScoreboardAPI.getInstance().sendScoreboard(playerJoinEvent.getPlayer(), "§6SkyWars", "§6AIOHub.net", "§1", "Map§8: ", "  §eVote-Phase", "§2", "Team§8: ","  §7Random");

				LobbyState lobbyState = (LobbyState) SkyWars.getInstance().getGameStateManager().getCurrentGameState();

				SkyWars.getInstance().players.add(playerJoinEvent.getPlayer());

				playerJoinEvent.getPlayer().setAllowFlight(false);
				playerJoinEvent.getPlayer().setFlying(false);

				playerJoinEvent.setJoinMessage(SkyWars.getInstance().getPrefix() + "§a" + playerJoinEvent.getPlayer().getDisplayName());

				ItemStack teams = ItemStackBuilder.builder(Material.NETHER_STAR).displayName("§6Team Auswählen").build();
				ItemStack kits = new ItemBuilder(Material.CHEST).setDisplayname("§6Kit Auswählen §7<Comming Soon>").build();
				ItemStack lobby = new ItemBuilder(Material.SLIME_BALL).setDisplayname("Zurück zur Lobby").build();

				playerJoinEvent.getPlayer().getInventory().setItem(0, teams);
				playerJoinEvent.getPlayer().getInventory().setItem(1, kits);
				playerJoinEvent.getPlayer().getInventory().setItem(8, lobby);

				SkyWars.getInstance().playerKits.put(playerJoinEvent.getPlayer(), Kits.STARTER);

				if (SkyWars.getInstance().players.size() >= 2) {
					if (!lobbyState.getLobbyCountdown().isRunning())
						lobbyState.getLobbyCountdown().run();
				}
			}
			if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof InGameState) {
				playerJoinEvent.getPlayer().setGameMode(GameMode.SURVIVAL);
				SkyWars.getInstance().spectators.add(playerJoinEvent.getPlayer());
				playerJoinEvent.getPlayer().setAllowFlight(true);
				playerJoinEvent.getPlayer().setFlying(true);
				for(Player all : Bukkit.getOnlinePlayers())
					all.hidePlayer(playerJoinEvent.getPlayer());
				playerJoinEvent.setJoinMessage(null);
				playerJoinEvent.getPlayer().sendMessage(SkyWars.getInstance().getPrefix() + "Du bist nun im Zuschauermodus.");
			}
	}
}
/*
	if(NickAPI.getInstance().isAutoNickEnabled(playerJoinEvent.getPlayer().getUniqueId()))
		NickAPI.getInstance().autoNickPlayer(playerJoinEvent.getPlayer());
 */
