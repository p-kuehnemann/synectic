package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.kits.Kits;
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
			if(playerInteractEvent.getAction() == Action.RIGHT_CLICK_AIR || playerInteractEvent.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(playerInteractEvent.getPlayer().getItemInHand().getItemMeta().getDisplayName() == "�cZur�ck zur Lobby �7<Rechtsklick>") {
					SkyWars.getInstance().getServer().dispatchCommand(playerInteractEvent.getPlayer(), "hub");
					playerInteractEvent.setCancelled(true);
				}
				if(playerInteractEvent.getPlayer().getItemInHand().getItemMeta().getDisplayName() == "�6Kit Ausw�hlen �7<Comming Soon>") {
					Inventory inv = playerInteractEvent.getPlayer().getServer().createInventory(null, 9, "�6Kit w�hlen");
					getKits(inv);
					playerInteractEvent.getPlayer().openInventory(inv);
				}
			}
		}catch(Exception exception) { }
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent inventoryClickEvent) {
		try{
			if(inventoryClickEvent.getInventory().getName().equals("�6Kit w�hlen")) {
				if(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("�aKit Starter")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "�7Du hast das Kit �eStarter�7 gew�hlt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.STARTER);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("�aKit Flamara")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "�7Du hast das Kit �eFlamara�7 gew�hlt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.FLAMARA);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("�aKit Zauberer")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "�7Du hast das Kit �eZauberer�7 gew�hlt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.ZAUBERER);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("�aKit Verfressen")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "�7Du hast das Kit �eVerfressen�7 gew�hlt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.VERFRESSEN);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else if(inventoryClickEvent.getCurrentItem().getItemMeta().getDisplayName().equals("�aKit Schwimmer")) {
					inventoryClickEvent.getWhoClicked().sendMessage(SkyWars.getInstance().getPrefix() + "�7Du hast das Kit �eSchwimmer�7 gew�hlt!");
					inventoryClickEvent.getWhoClicked().closeInventory();
					SkyWars.getInstance().playerKits.remove((Player) inventoryClickEvent.getWhoClicked());
					SkyWars.getInstance().playerKits.put((Player) inventoryClickEvent.getWhoClicked(), Kits.SCHWIMMER);
					//TODO
					inventoryClickEvent.setCancelled(true);
				} else {
					inventoryClickEvent.setCancelled(true);
				}
			}
		}catch(NullPointerException nullPointerException) { }
	}
	
	private void getKits(Inventory inventory) {
		ItemStack starter = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta starterMeta = starter.getItemMeta();
		starterMeta.setDisplayName("�aKit Starter");
		starter.setItemMeta(starterMeta);
		
		ItemStack flamara = new ItemStack(Material.GOLD_SWORD);
		ItemMeta flamaraMeta = flamara.getItemMeta();
		flamaraMeta.setDisplayName("�aKit Flamara");
		flamara.setItemMeta(flamaraMeta);
		
		ItemStack zauberer = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta zaubererMeta = zauberer.getItemMeta();
		zaubererMeta.setDisplayName("�aKit Zauberer");
		zauberer.setItemMeta(zaubererMeta);
		
		ItemStack verfressen = new ItemStack(Material.GRILLED_PORK);
		ItemMeta verfressenMeta = verfressen.getItemMeta();
		verfressenMeta.setDisplayName("�aKit Verfressen");
		verfressen.setItemMeta(verfressenMeta);
		
		ItemStack schwimmer = new ItemStack(Material.WATER_BUCKET);
		ItemMeta schwimmerMeta = schwimmer.getItemMeta();
		schwimmerMeta.setDisplayName("�aKit Schwimmer");
		schwimmer.setItemMeta(schwimmerMeta);
		
		ItemStack leer = new ItemStack(Material.STAINED_GLASS_PANE);
		ItemMeta leerMeta = leer.getItemMeta();
		leerMeta.setDisplayName(" ");
		leer.setItemMeta(leerMeta);
		
		inventory.setItem(0, starter);
		inventory.setItem(1, leer);
		inventory.setItem(2, flamara);
		inventory.setItem(3, leer);
		inventory.setItem(4, zauberer);
		inventory.setItem(5, leer);
		inventory.setItem(6, verfressen);
		inventory.setItem(7, leer);
		inventory.setItem(8, schwimmer);
	}
	
}
