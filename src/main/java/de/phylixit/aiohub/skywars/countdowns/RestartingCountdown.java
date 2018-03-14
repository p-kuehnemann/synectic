package de.phylixit.aiohub.skywars.countdowns;

import de.phylixit.aiohub.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RestartingCountdown extends Countdown{
    private int seconds = 10;

    @Override
    public void run() {
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SkyWars.getInstance(), new Runnable() {
            @Override
            public void run() {
                if(seconds == 10 || seconds == 5 || seconds == 4 || seconds == 3 || seconds == 2) {
                    Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel wird neugestartet in §e" + seconds + "§7 Sekunden.");
                } else if(seconds == 1) {
                           Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel wird neugestartet in §eeiner§7 Sekunden.");
                } else if(seconds == 0) {
                    for(Player all : Bukkit.getOnlinePlayers())
                        all.kickPlayer(null);
                    Bukkit.spigot().restart();
                }
                seconds--;
            }
        }, 0, 20);
    }

    @Override
    public void cancel() { Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "§7Es ist ein Fehler beim neustarten aufgetreten, bitte kontaktiere einen §bDeveloper§7."); }
}
