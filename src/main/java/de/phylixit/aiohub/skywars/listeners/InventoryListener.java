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
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent playerInteractEvent) {
		try{
			if(playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_AIR) || playerInteractEvent.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
				if(playerInteractEvent.getItem().getItemMeta().getDisplayName().equals("§6Team Auswählen")) {
					Inventory teamsInv = Bukkit.createInventory(null, 9*3, "§6Team wechseln");
					getTeams(teamsInv);
					playerInteractEvent.getPlayer().openInventory(teamsInv);
				}
				if(playerInteractEvent.getItem().getItemMeta().getDisplayName().equals("§6Kit Auswählen §7<Comming Soon>")) {
					Inventory kitsInv = playerInteractEvent.getPlayer().getServer().createInventory(null, 9, "§6Kit wählen");
					getKits(kitsInv);
					playerInteractEvent.getPlayer().openInventory(kitsInv);
				}
				if(playerInteractEvent.getItem().getItemMeta().getDisplayName().equals("Zurück zur Lobby")) {
					playerInteractEvent.getPlayer().kickPlayer(null);
					playerInteractEvent.setCancelled(true);
				}
			}
		}catch(Exception exception) { }
	}

	@EventHandler
	public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
		Player player = (Player) inventoryClickEvent.getWhoClicked();
		try{
			if(inventoryClickEvent.getInventory().getName().equals("§6Kit wählen")) {
				if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Starter")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§7Du hast das Kit §eStarter§7 gewählt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.STARTER);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Flamara")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§7Du hast das Kit §eFlamara§7 gewählt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.FLAMARA);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Zauberer")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§7Du hast das Kit §eZauberer§7 gewählt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.ZAUBERER);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Verfressen")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§7Du hast das Kit §eVerfressen§7 gewählt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.VERFRESSEN);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if (inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("§aKit Schwimmer")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "§7Du hast das Kit §eSchwimmer§7 gewählt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.SCHWIMMER);
					//TODO
					inventoryClickEvent.setCancelled(true);
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
							getTeams(teamsInv);
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
		}catch(NullPointerException nullPointerException) { }
	}
	
	private void getKits(Inventory inventory) {

		ItemStack starter = new ItemBuilder(Material.STONE_PICKAXE).setDisplayname("§aKit Starter").build();
		ItemStack flamara = new ItemBuilder(Material.GOLD_SWORD).setDisplayname("§aKit Flamara").build();
		ItemStack zauberer = new ItemBuilder(Material.ENCHANTMENT_TABLE).setDisplayname("§aKit Zauberer").build();
		ItemStack verfressen = new ItemBuilder(Material.GRILLED_PORK).setDisplayname("§aKit Verfressen").build();
		ItemStack schwimmer = new ItemBuilder(Material.WATER_BUCKET).setDisplayname("§aKit Schwimmer").build();
		ItemStack leer = new ItemBuilder(Material.STAINED_GLASS_PANE).setSubID(7).setDisplayname("§8x").build();

		for(int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, leer);

		inventory.setItem(0, starter);
		inventory.setItem(2, flamara);
		inventory.setItem(4, zauberer);
		inventory.setItem(6, verfressen);
		inventory.setItem(8, schwimmer);
	}

	private void getTeams(Inventory inventory) {
		for(int i = 0; i < inventory.getSize(); i++)
			inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE).setSubID(7).setDisplayname("§8x").build());
		for(Teams team : Teams.values()) {
			ItemStack teamItem = new ItemBuilder(Material.STAINED_CLAY).setSubID(5)
					.setDisplayname("§a" + team.getName()).setLore("§7" + team.getPlayers().size() + "§8/§71").build();
			inventory.setItem(team.getItemSlot(), teamItem);
		}
	}
}
