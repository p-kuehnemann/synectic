package de.phylixit.aiohub.skywars.listeners;

import net.minecraft.server.v1_8_R3.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener {
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent weatherChangeEvent) {
        weatherChangeEvent.setCancelled(true);
    }
}
