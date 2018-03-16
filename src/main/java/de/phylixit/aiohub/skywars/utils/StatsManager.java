package de.phylixit.aiohub.skywars.utils;

import net.aiohub.utilities.stats.StatsAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class StatsManager {
    public static void addKill(Player player) { StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "kills" , 1); }
    public static void addDeath(Player player) { StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "deaths" , 1); }
    public static void addWin(Player player) {
        StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "wins", 1);
    }
    public static void addGamePlayed(Player player) { StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "games", 1); }

    public static int getKills(Player player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "kills");
    }
    public static int getDeaths(Player player) { return StatsAPI.getInstance().getValue(player.getUniqueId(), "deaths"); }
    public static double getKD(Player player) {
        int kills = getKills(player);
        int deaths = getDeaths(player);
        double kdRaw = (double) kills / (double) deaths;
        DecimalFormat kd = new DecimalFormat("#.##");
        if(deaths == 0)
            kdRaw = kills;
        return Double.parseDouble(kd.format(kdRaw));
    }
    public static int getWins(Player player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "wins");
    }
    public static int getLoses(Player player) { return getDeaths(player); }
    public static int getGamePlayed(Player player) { return StatsAPI.getInstance().getValue(player.getUniqueId(), "games"); }

    public static int getKills(OfflinePlayer player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "kills");
    }
    public static int getDeaths(OfflinePlayer player) { return StatsAPI.getInstance().getValue(player.getUniqueId(), "deaths"); }
    public static double getKD(OfflinePlayer player) {
        int kills = getKills(player);
        int deaths = getDeaths(player);
        double kdRaw = (double) kills / (double) deaths;
        DecimalFormat kd = new DecimalFormat("#.##");
        if(deaths == 0)
            kdRaw = kills;
        return Double.parseDouble(kd.format(kdRaw));
    }
    public static int getWins(OfflinePlayer player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "wins");
    }
    public static int getLoses(OfflinePlayer player) { return getDeaths(player); }
    public static int getGamePlayed(OfflinePlayer player) { return StatsAPI.getInstance().getValue(player.getUniqueId(), "games"); }
}
