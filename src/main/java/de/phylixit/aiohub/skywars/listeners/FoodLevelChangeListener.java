package de.phylixit.aiohub.skywars.listeners;

import de.phylixit.aiohub.skywars.SkyWars;
import de.phylixit.aiohub.skywars.gamestates.InGameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChangeListener implements Listener {

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent foodLevelChangeEvent) {
        if (!(SkyWars.getInstance().getGameStateManager().getCurrentGameState() instanceof InGameState)) {
            foodLevelChangeEvent.setCancelled(true);
            if(foodLevelChangeEvent.getFoodLevel() <= 19)
                foodLevelChangeEvent.setFoodLevel(20);
        }
    }
}
