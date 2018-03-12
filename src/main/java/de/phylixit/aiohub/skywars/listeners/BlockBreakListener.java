package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.LobbyState;
import de.phylixit.aiohub.skywars.gamestates.RestartingState;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener{

    @EventHandler
    public void onBlockBreak(BlockBreakEvent blockBreakEvent) {
        if((SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof LobbyState) ||
                (SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof RestartingState)) {
            if(blockBreakEvent.getPlayer().getGameMode() != GameMode.CREATIVE)
                blockBreakEvent.setCancelled(true);
        }
    }
}
