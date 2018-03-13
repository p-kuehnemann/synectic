package de.phylixit.aiohub.skywars.teams;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.utils.LocationManager;
import net.aiohub.utilities.utils.ScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TeamManager {

    public static Map<Player, Teams> playerTeams = new HashMap<>();

    public static void setTeam(Player player, Teams team) {
        removePlayerFromTeam(player);
        playerTeams.put(player, team);
        team.getPlayers().add(player);
    }

    public static Teams getTeam(Player player) { return playerTeams.get(player); }
    public static void removePlayerFromTeam(Player player) {
        if(playerTeams.containsKey(player)) {
            getTeam(player).getPlayers().remove(player);
            playerTeams.remove(player);
        }
    }
    public static boolean isPlayerInATeam(Player player) { return getTeam(player) != null; }
    public static boolean isPlayerInValidTeam(Player player) {
        return getTeam(player).equals(Teams.T1) ||
                getTeam(player).equals(Teams.T2) ||
                getTeam(player).equals(Teams.T3) ||
                getTeam(player).equals(Teams.T4) ||
                getTeam(player).equals(Teams.T5) ||
                getTeam(player).equals(Teams.T6) ||
                getTeam(player).equals(Teams.T7) ||
                getTeam(player).equals(Teams.T8);
    }
    public static Map<Player, Teams> getPlayerTeams() { return playerTeams; }
    public static Teams[] getTeamArray() { return Teams.values(); }
    public static void teleportTeams() {
       for(Teams teams : Teams.values()) {
           for(Player player : teams.getPlayers()){
               player.getInventory().clear();
               player.getInventory().setArmorContents(null);
               ScoreboardAPI.getInstance().sendScoreboard(player, "§6SkyWars", "§6AIOHub.net", "§1", "Map§8: ", "  §cInvaild", "§2", "Team§8: ","  §a" + TeamManager.getTeam(player).getName());
               player.teleport(LocationManager.getLocationConfig(teams.getSpawnLocationName()));
           }
       }
    }
}
