package de.phylixit.aiohub.skywars.teams;

import org.bukkit.DyeColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public enum Teams
{
    T1("Team-1", "§aT1 §8┃ §7", new ArrayList<>(), "01t1", "spawn_t1", 9),
    T2("Team-2", "§aT2 §8┃ §7", new ArrayList<>(), "02t2", "spawn_t2", 10),
    T3("Team-3", "§aT3 §8┃ §7", new ArrayList<>(), "03t3", "spawn_t3", 11),
    T4("Team-4", "§aT4 §8┃ §7", new ArrayList<>(), "04t4", "spawn_t4", 12),
    T5("Team-5", "§aT5 §8┃ §7", new ArrayList<>(), "05t5", "spawn_t5", 13),
    T6("Team-6", "§aT6 §8┃ §7", new ArrayList<>(), "06t6", "spawn_t6", 14),
    T7("Team-7", "§aT7 §8┃ §7", new ArrayList<>(), "07t7", "spawn_t7", 15),
    T8("Team-8", "§aT8 §8┃ §7", new ArrayList<>(), "08t8", "spawn_t8", 16);

    private String name;
    private String prefix;
    private List<Player> players;
    private String scoreboardName;
    private String spawnLocationName;
    private int itemSlot;

    private Teams(String name, String prefix, List players, String scoreboardName, String spawnLocationName, int itemSlot){
        this.name = name;
        this.prefix = prefix;
        this.players = players;
        this.scoreboardName = scoreboardName;
        this.spawnLocationName = spawnLocationName;
        this.itemSlot = itemSlot;
    }

    public String getName() { return name; }
    public String getPrefix() { return prefix; }
    public String getScoreboardName() { return scoreboardName; }
    public String getSpawnLocationName() { return spawnLocationName; }
    public int getItemSlot() { return itemSlot; }
    public List<Player> getPlayers() { return players;}
    public boolean isFull() { return getPlayers().size() >= 1; }
}