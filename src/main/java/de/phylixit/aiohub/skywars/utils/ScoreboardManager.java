package de.phylixit.aiohub.skywars.utils;

import de.phylixit.aiohub.skywars.teams.TeamManager;
import de.phylixit.aiohub.skywars.teams.Teams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreboardManager {
    private static Scoreboard scoreboard;
    static {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        for(Teams team : Teams.values())
            scoreboard.registerNewTeam(team.getScoreboardName()).setPrefix(team.getPrefix());
    }

    public static void reload() {
        for(Player player : Bukkit.getOnlinePlayers()){
            scoreboard.getTeam(TeamManager.getTeam(player).getScoreboardName()).addEntry(player.getName());
            player.setScoreboard(scoreboard);
            player.setDisplayName(TeamManager.getTeam(player).getPrefix() + player.getName());
        }
    }
}
