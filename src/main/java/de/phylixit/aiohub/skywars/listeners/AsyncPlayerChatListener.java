package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent asyncPlayerChatEvent) {
        asyncPlayerChatEvent.setCancelled(true);
        if(SkyWars.getInstance().players.contains(asyncPlayerChatEvent.getPlayer())) {
            Bukkit.broadcastMessage(asyncPlayerChatEvent.getPlayer().getDisplayName() + " §8» §7" + asyncPlayerChatEvent.getMessage());
        }
    }
}
