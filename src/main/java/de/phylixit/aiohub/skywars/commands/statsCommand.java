package de.phylixit.aiohub.skywars.commands;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.utils.StatsManager;
import net.aiohub.utilities.stats.StatsAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class statsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if(commandSender instanceof  Player) {
            if(strings.length == 0) {
                player.sendMessage(" ");
                player.sendMessage("§7Deine §6Statistiken");
                player.sendMessage("§7Kills§8: §e" + StatsManager.getKills(player));
                player.sendMessage("§7Tode§8: §e" + StatsManager.getDeaths(player));
                player.sendMessage("§7K/D§8: §e" + StatsManager.getKD(player));
                player.sendMessage("§7Gespielte Spiele§8: §e" + StatsManager.getGamePlayed(player));
                player.sendMessage("§7Gewonnene Spiele§8: §e" + StatsManager.getWins(player));
                player.sendMessage("§7Verlorene Spiele§8: §e" + StatsManager.getLoses(player));
                player.sendMessage(" ");
                return true;
            } else if(strings.length == 1) {
                if(Bukkit.getPlayer(strings[0]) != null) {
                    Player targetPlayer = Bukkit.getPlayer(strings[0]);

                    player.sendMessage(" ");
                    player.sendMessage("§7" + targetPlayer.getName() + "'s §6Statistiken");
                    player.sendMessage("§7Kills§8: §e" + StatsManager.getKills(targetPlayer));
                    player.sendMessage("§7Tode§8: §e" + StatsManager.getDeaths(targetPlayer));
                    player.sendMessage("§7K/D§8: §e" + StatsManager.getKD(targetPlayer));
                    player.sendMessage("§7Gespielte Spiele§8: §e" + StatsManager.getGamePlayed(targetPlayer));
                    player.sendMessage("§7Gewonnene Spiele§8: §e" + StatsManager.getWins(targetPlayer));
                    player.sendMessage("§7Verlorene Spiele§8: §e" + StatsManager.getLoses(targetPlayer));
                    player.sendMessage(" ");
                    return true;
                } else {
                    OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(strings[0]);
                    if(!StatsAPI.getInstance().isRegistered(targetPlayer.getUniqueId())){
                        player.sendMessage(SkyWars.getInstance().getPrefix() + "§cDieser Spieler hat noch nie SkyWars gespielt.");
                        return true;
                    }

                    player.sendMessage(" ");
                    player.sendMessage("§7" + targetPlayer.getName() + "'s §6Statistiken");
                    player.sendMessage("§7Kills§8: §e" + StatsManager.getKills(targetPlayer));
                    player.sendMessage("§7Tode§8: §e" + StatsManager.getDeaths(targetPlayer));
                    player.sendMessage("§7K/D§8: §e" + StatsManager.getKD(targetPlayer));
                    player.sendMessage("§7Gespielte Spiele§8: §e" + StatsManager.getGamePlayed(targetPlayer));
                    player.sendMessage("§7Gewonnene Spiele§8: §e" + StatsManager.getWins(targetPlayer));
                    player.sendMessage("§7Verlorene Spiele§8: §e" + StatsManager.getLoses(targetPlayer));
                    player.sendMessage(" ");
                }
            }
        }
        return false;
    }
}
