package de.phylixit.aiohub.skywars.utils;

import net.aiohub.utilities.stats.StatsAPI;
import net.aiohub.utilities.utils.ServerAPI;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class StatsManager {

    public static void addKill(Player player) {
        StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "Kills" , 1);
    }

    public static void addDeath(Player player) {
        StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "Deaths" , 1);
    }

    public static void addWin(Player player) {
        StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "Wins", 1);
    }

    public static void addLose(Player player) {
        StatsAPI.getInstance().addValueToKey(player.getUniqueId(), "Loses", 1);

    }

    public static int getKills(Player player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "Kills");
    }

    public static int getDeaths(Player player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "Deaths");
    }

    public static String getKD(Player player) {
        int kills = getKills(player);
        int deaths = getDeaths(player);
        double kdRaw = (double) kills / deaths;
        DecimalFormat kd = new DecimalFormat("#.##");
        return kd.format(kdRaw);
    }

    public static int getWins(Player player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "Wins");
    }

    public static int getLoses(Player player) {
        return StatsAPI.getInstance().getValue(player.getUniqueId(), "Loses");
    }

    public static int getGamePlayed(Player player) {
        int wins = getWins(player);
        int loses = getLoses(player);
        int gamePlayed = wins + loses;
        return gamePlayed;
    }
}
