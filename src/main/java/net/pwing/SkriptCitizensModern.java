package net.pwing;

import org.bukkit.plugin.java.JavaPlugin;

public class SkriptCitizensModern extends JavaPlugin {
    @Override
    public void onEnable() {
        // Check for required dependencies
        if (getServer().getPluginManager().getPlugin("Skript") == null) {
            getLogger().severe("Skript not found! This plugin requires Skript to work.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        if (getServer().getPluginManager().getPlugin("Citizens") == null) {
            getLogger().severe("Citizens not found! This plugin requires Citizens to work.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        getLogger().info("Skript-CitizenModern v" + getDescription().getVersion() + " enabled!");
        getLogger().info("Registering Skript elements...");
        
        // Register all Skript elements
        new SkriptAddon(this).register();
        
        getLogger().info("Registration complete!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Skript-CitizenModern disabled");
    }
}
