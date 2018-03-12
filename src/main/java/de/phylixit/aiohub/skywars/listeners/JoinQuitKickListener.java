package de.phylixit.aiohub.skywars.listeners;

import de.dytanic.cloudnet.bridge.internal.util.ItemStackBuilder;
import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.kits.Kits;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import net.aiohub.utilities.utils.*;
import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class JoinQuitKickListener implements Listener {

	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent playerJoinEvent) {

		//if(NickAPI.getInstance().isAutoNickEnabled(playerJoinEvent.getPlayer().getUniqueId())) {
		//	NickAPI.getInstance().autoNickPlayer(playerJoinEvent.getPlayer());
		//}
			playerJoinEvent.getPlayer().setHealth(20);
			playerJoinEvent.getPlayer().setFoodLevel(20);
			playerJoinEvent.getPlayer().setFireTicks(0);
			playerJoinEvent.getPlayer().setExp(0);
			playerJoinEvent.getPlayer().setLevel(0);
			playerJoinEvent.getPlayer().getInventory().clear();
			playerJoinEvent.getPlayer().setGameMode(GameMode.SURVIVAL);
			playerJoinEvent.getPlayer().getWorld().setDifficulty(Difficulty.EASY);
			playerJoinEvent.getPlayer().getWorld().setStorm(false);
			playerJoinEvent.getPlayer().getWorld().setThundering(false);
			Location spawnLocation = new Location(Bukkit.getWorld("world"), 41.5, 157, 8.5);
			spawnLocation.setYaw(90);
			spawnLocation.setPitch(0);
			playerJoinEvent.getPlayer().teleport(spawnLocation);

			if (SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {

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
					if (!lobbyState.getLobbyCountdown().isRunning()) {
						lobbyState.getLobbyCountdown().run();
					}
				}
			} else {
				SkyWars.getInstance().spectators.add(playerJoinEvent.getPlayer());

				playerJoinEvent.getPlayer().setAllowFlight(true);
				playerJoinEvent.getPlayer().setFlying(true);

				playerJoinEvent.setJoinMessage(null);

				playerJoinEvent.getPlayer().sendMessage(SkyWars.getInstance().getPrefix() + "Du bist nun im Zuschauermodus.");
			}
		}
	
	@EventHandler
	public void onQuitPlayer(PlayerQuitEvent playerQuitEvent) {
		TeamManager.removePlayerFromTeam(playerQuitEvent.getPlayer());

		if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
			SkyWars.getInstance().players.remove(playerQuitEvent.getPlayer());
			playerQuitEvent.setQuitMessage(SkyWars.getInstance().getPrefix() + "§c" + playerQuitEvent.getPlayer().getDisplayName());
			SkyWars.getInstance().playerKits.remove(playerQuitEvent.getPlayer());
		} else {
			SkyWars.getInstance().spectators.remove(playerQuitEvent.getPlayer());
			playerQuitEvent.setQuitMessage(null);
		}
	}
	
	@EventHandler
	public void onKickPlayer(PlayerKickEvent playerKickEvent) {
		TeamManager.removePlayerFromTeam(playerKickEvent.getPlayer());

		if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
			LobbyState lobbyState = (LobbyState) SkyWars.getInstance().getGameStateManager().getCurrentGameState();
			SkyWars.getInstance().players.remove(playerKickEvent.getPlayer());
			playerKickEvent.setLeaveMessage(SkyWars.getInstance().getPrefix() + "§c" + playerKickEvent.getPlayer().getName());
			SkyWars.getInstance().playerKits.remove(playerKickEvent.getPlayer());
			if(SkyWars.getInstance().players.size() < 2) {
					lobbyState.getLobbyCountdown().cancel();
			}
		} else {
			SkyWars.getInstance().spectators.remove(playerKickEvent.getPlayer());
			playerKickEvent.setLeaveMessage(null);	
		}
	}
}
