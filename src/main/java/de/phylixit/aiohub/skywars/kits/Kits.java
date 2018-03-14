package de.phylixit.aiohub.skywars.kits;

import org.bukkit.Material;

public enum Kits
{
	STARTER("Starter", Material.STONE_PICKAXE, 0),
	FLAMARA("Flamara", Material.GOLD_SWORD, 2),
	ZAUBERER("Zauberer", Material.ENCHANTMENT_TABLE, 4),
	VERFRESSEN("Verfressen", Material.GRILLED_PORK, 6),
	SCHWIMMER("Schwimmer", Material.WATER_BUCKET, 8);

	private String name;
	private Material material;
	private int itemSlot;

	private Kits (String name, Material material, int itemSlot) {
		this.name = name;
		this.material = material;
		this.itemSlot = itemSlot;
	}

	public String getName() { return name; }
	public Material getMaterial() { return material; }
	public int getItemSlot() { return itemSlot; }
}
