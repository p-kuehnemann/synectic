package de.phylixit.aiohub.skywars.commands;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.utils.LocationManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class locationsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player) {
            Player player = (Player) commandSender;
                if (strings.length == 0) {
                    player.sendMessage(" ");
                    player.sendMessage("X: " + player.getLocation().getX());
                    player.sendMessage("Y: " + player.getLocation().getY());
                    player.sendMessage("Z: " + player.getLocation().getZ());
                    player.sendMessage("Yaw: " + player.getLocation().getYaw());
                    player.sendMessage("Pitch: " + player.getLocation().getPitch());
                    player.sendMessage(" ");
                    return true;
                } else if (strings.length == 2) {
                    if (strings[0].equalsIgnoreCase("set")) {
                        String name = strings[1];
                        LocationManager.setLocation(name, player.getLocation());
                        player.sendMessage(SkyWars.getInstance().getPrefix() + "§7Die Location '§e" + name + "§7' wurde gesetzt.");
                        return true;
                    }
            }
        }
        return false;
    }
}
/*
    PERMISSIONS EINFÜGEN!!!!!
 */