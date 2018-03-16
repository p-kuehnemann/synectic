package de.phylixit.aiohub.skywars.kits;

import net.aiohub.utilities.utils.ItemBuilder;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitManager {

    public static void getKit(String name, Player player) {
        if(name.equals(Kits.STARTER.getName())) {
            ItemStack sword = new ItemBuilder(Material.STONE_SWORD).build();
            ItemStack pickaxe = new ItemBuilder(Material.IRON_PICKAXE).build();
            ItemStack axt = new ItemBuilder(Material.STONE_AXE).build();
            ItemStack blocks = new ItemBuilder(45).setAmount(64).build();

            player.getInventory().addItem(sword);
            player.getInventory().addItem(pickaxe);
            player.getInventory().addItem(axt);
            player.getInventory().addItem(blocks);
        } else if(name.equals(Kits.FLAMARA.getName())) {
            ItemStack sword = new ItemBuilder(Material.GOLD_SWORD).addEnchantment(Enchantment.FIRE_ASPECT, 2).addEnchantment(Enchantment.DAMAGE_ALL, 1).build();
            ItemStack block1 = new ItemBuilder(Material.WOOL).setSubID(14).setAmount(64).build();
            ItemStack block2 = new ItemBuilder(Material.WOOL).setSubID(1).setAmount(64).build();

            player.getInventory().addItem(sword);
            player.getInventory().addItem(block1);
            player.getInventory().addItem(block2);
        } else if(name.equals(Kits.ZAUBERER.getName())) {
            ItemStack enchantmentTable = new ItemBuilder(Material.ENCHANTMENT_TABLE).build();
            ItemStack books = new ItemBuilder(Material.BOOK).setAmount(3).build();
            ItemStack exp = new ItemBuilder(Material.EXP_BOTTLE).setAmount(32).build();
            ItemStack lapis = new ItemBuilder(Material.LAPIS_BLOCK).setAmount(16).build();

            player.getInventory().addItem(enchantmentTable);
            player.getInventory().addItem(books);
            player.getInventory().addItem(exp);
            player.getInventory().addItem(lapis);
        } else if(name.equals(Kits.VERFRESSEN.getName())) {
            ItemStack pork = new ItemBuilder(Material.GRILLED_PORK).setAmount(32).build();
            ItemStack beef = new ItemBuilder(Material.COOKED_BEEF).setAmount(32).build();
            ItemStack chicken = new ItemBuilder(Material.COOKED_CHICKEN).setAmount(32).build();
            ItemStack apple = new ItemBuilder(Material.APPLE).setAmount(64).build();

            player.getInventory().addItem(pork);
            player.getInventory().addItem(beef);
            player.getInventory().addItem(chicken);
            player.getInventory().addItem(apple);
        } else if(name.equals(Kits.SCHWIMMER.getName())) {
            ItemStack water = new ItemBuilder(Material.WATER_BUCKET).build();
            ItemStack boats = new ItemBuilder(Material.BOAT).setAmount(3).build();
            ItemStack leggins = new ItemBuilder(Material.LEATHER_LEGGINGS).setDyeColor(DyeColor.BLACK).build();

            player.getInventory().setLeggings(leggins);
            player.getInventory().addItem(water);
            player.getInventory().addItem(boats);
            player.getInventory().addItem(leggins);
        }

    }
}
