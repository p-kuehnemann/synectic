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
                switch (seconds){
                    case 10: case 5: case 4: case 3: case 2:
                        Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel wird neugestartet in §e" + seconds + "§7 Sekunden.");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(SkyWars.getInstance().getPrefix() + "Das Spiel wird neugestartet in §eeiner§7 Sekunden.");
                        break;
                    case 0:
                        for(Player all : Bukkit.getOnlinePlayers())
                            all.kickPlayer(null);
                        Bukkit.spigot().restart();
                        break;
                    default:
                        break;
                }
                seconds--;
            }
        }, 0, 20);
    }

    @Override
    public void cancel() { }
}
