package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.kits.Kits;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class JoinQuitKickListener implements Listener {

	@EventHandler
	public void onJoinPlayer(PlayerJoinEvent playerJoinEvent) {
		playerJoinEvent.getPlayer().setHealth(20);
		playerJoinEvent.getPlayer().setFoodLevel(20);
		playerJoinEvent.getPlayer().setFireTicks(0);
		playerJoinEvent.getPlayer().setExp(0);
		playerJoinEvent.getPlayer().setLevel(0);
		playerJoinEvent.getPlayer().getInventory().clear();
		playerJoinEvent.getPlayer().setGameMode(GameMode.SURVIVAL);
		
		if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
			LobbyState lobbyState = (LobbyState) SkyWars.getInstance().getGameStateManager().getCurrentGameState();
			SkyWars.getInstance().players.add(playerJoinEvent.getPlayer());
			playerJoinEvent.getPlayer().setAllowFlight(false);
			playerJoinEvent.getPlayer().setFlying(false);
			playerJoinEvent.setJoinMessage(SkyWars.getInstance().getPrefix() + "�a" + playerJoinEvent.getPlayer().getName());
			
			ItemStack kits = new ItemStack(Material.CHEST);
			ItemMeta kitsMeta = kits.getItemMeta();
			kitsMeta.setDisplayName("�6Kit Ausw�hlen �7<Comming Soon>");
			kits.setItemMeta(kitsMeta);
			playerJoinEvent.getPlayer().getInventory().setItem(0, kits);
			
			ItemStack backToLobby = new ItemStack(Material.SLIME_BALL);
			ItemMeta backToLobbyMeta = backToLobby.getItemMeta();
			backToLobbyMeta.setDisplayName("�cZur�ck zur Lobby �7<Rechtsklick>");
			backToLobby.setItemMeta(backToLobbyMeta);
			playerJoinEvent.getPlayer().getInventory().setItem(8, backToLobby);	
			SkyWars.getInstance().playerKits.put(playerJoinEvent.getPlayer(), Kits.STARTER);
			if(SkyWars.getInstance().players.size() >= 2) {
				if(!lobbyState.getLobbyCountdown().isRunning()) {
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
		if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
			SkyWars.getInstance().players.remove(playerQuitEvent.getPlayer());
			playerQuitEvent.setQuitMessage(SkyWars.getInstance().getPrefix() + "�c" + playerQuitEvent.getPlayer().getName());
			SkyWars.getInstance().playerKits.remove(playerQuitEvent.getPlayer());
		} else {
			SkyWars.getInstance().spectators.remove(playerQuitEvent.getPlayer());
			playerQuitEvent.setQuitMessage(null);
		}
	}
	
	@EventHandler
	public void onKickPlayer(PlayerKickEvent playerKickEvent) {
		if(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) {
			LobbyState lobbyState = (LobbyState) SkyWars.getInstance().getGameStateManager().getCurrentGameState();
			SkyWars.getInstance().players.remove(playerKickEvent.getPlayer());
			playerKickEvent.setLeaveMessage(SkyWars.getInstance().getPrefix() + "�c" + playerKickEvent.getPlayer().getName());
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
