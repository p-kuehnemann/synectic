package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.kits.Kits;
import de.phylixit.aiohub.skywars.teams.TeamManager;
import de.phylixit.aiohub.skywars.teams.Teams;
import de.phylixit.aiohub.skywars.utils.ScoreboardManager;
import net.aiohub.utilities.utils.ItemBuilder;
import net.aiohub.utilities.utils.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent playerInteractEvent) {
		try{
			if(playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_AIR) || playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(playerInteractEvent.getItem().getItemMeta().getDisplayName().equals("§6Team Auswählen")) {
					Inventory teamsInv = Bukkit.createInventory(null, 9*3, "§6Team wechseln");
					getTeamsInventory(teamsInv);
					playerInteractEvent.getPlayer().openInventory(teamsInv);
				}
				if(playerInteractEvent.getItem().getItemMeta().getDisplayName().equals("§6Kit Auswählen §7<Comming Soon>")) {
					Inventory kitsInv = playerInteractEvent.getPlayer().getServer().createInventory(null, 9, "§6Kit wählen");
					getKitsInventory(kitsInv);
					playerInteractEvent.getPlayer().openInventory(kitsInv);
				}
				if(playerInteractEvent.getItem().getItemMeta().getDisplayName().equals("Zurück zur Lobby")) {
					playerInteractEvent.getPlayer().kickPlayer(null);
					playerInteractEvent.setCancelled(true);
				}
			}
		}catch(Exception exception) { System.out.println(); }
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
		Player player = (Player) inventoryClickEvent.getWhoClicked();
		try{
			if(inventoryClickEvent.getInventory().getName().equals("§6Kit wählen")) {
				if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Starter")) {
					getKit(inventoryClickEvent, Kits.STARTER);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Flamara")) {
					getKit(inventoryClickEvent, Kits.FLAMARA);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Zauberer")) {
					getKit(inventoryClickEvent, Kits.ZAUBERER);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Verfressen")) {
					getKit(inventoryClickEvent, Kits.VERFRESSEN);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Schwimmer")) {
					getKit(inventoryClickEvent, Kits.SCHWIMMER);
				} else {
					inventoryClickEvent.setCancelled(true);
				}
			}

			if(inventoryClickEvent.getInventory().getName().equals("§6Team wechseln")) {
				Inventory teamsInv = Bukkit.createInventory(null, 9 * 3, "§6Team wechseln");
				for (Teams team : Teams.values()) {
					if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§a" + team.getName())) {
						if (!team.isFull()) {
							TeamManager.setTeam((Player) inventoryClickEvent.getWhoClicked(), team);
							getTeamsInventory(teamsInv);
							inventoryClickEvent.getWhoClicked().openInventory(teamsInv);
							inventoryClickEvent.setCancelled(true);

						} else {
							inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§cDieses Team ist bereits voll!");
							inventoryClickEvent.setCancelled(true);
						}
					} else {
						inventoryClickEvent.setCancelled(true);
					}
				}
					ScoreboardManager.reload();
					ScoreboardAPI.getInstance().sendScoreboard(player, "§6SkyWars", "§6AIOHub.net", "§1", "Map§8: ", "  §eVote-Phase", "§2", "Team§8: ", "  §a" + TeamManager.getTeam(player).getName());
			}
		}catch(NullPointerException nullPointerException) { System.out.println(); }
	}
	
	private void getKitsInventory(Inventory inventory) {
		for(int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setSubID(7).setDisplayname("§8x").build());
		for(Kits kits : Kits.values()) {
			ItemStack kitItem = new ItemBuilder(kits.getMaterial()).setDisplayname("§aKit " + kits.getName()).build();
			inventory.setItem(kits.getItemSlot(), kitItem);
		}
	}

	private void getTeamsInventory(Inventory inventory) {
		for(int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setSubID(7).setDisplayname("§8x").build());
		for(Teams team : Teams.values()) {
			ItemStack teamItem = new ItemBuilder(Material.STAINED_CLAY).setSubID(5)
					.setDisplayname("§a" + team.getName()).setLore("§7" + team.getPlayers().size() + "§8/§71").build();
			inventory.setItem(team.getItemSlot(), teamItem);
		}
	}

	private void getKit(InventoryClickEvent inventoryClickEvent, Kits kits) {
		inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§7Du hast das Kit §e" + kits.getName() +"§7 gewählt!");
		inventoryClickEvent.getWhoClicked().closeInventory();
		SkyWars.getInstance().playerKits.remove(inventoryClickEvent.getWhoClicked());
		SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), kits);
		//TODO: Buying, Description
		inventoryClickEvent.setCancelled(true);
	}
}
