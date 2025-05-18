package me.gaminglounge.moreitems;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import me.gaminglounge.moreitems.listener.DiscoverRecipe;

public class MoreItems extends JavaPlugin {

    public static MoreItems INSTACE;
    private DiscoverRecipe discoverRecipe;

    @Override
    public void onLoad() {
        INSTACE = this;
        INSTACE.saveDefaultConfig();

        if (!CommandAPI.isLoaded())
            CommandAPI.onLoad(new CommandAPIBukkitConfig(this));
    }   

    @Override
    public void onEnable() {
        INSTACE.discoverRecipe = new DiscoverRecipe();
        INSTACE.discoverRecipe.init();
        this.listener();

        CommandAPI.onEnable();
    }

    @Override
    public void onDisable() {
        CommandAPI.onDisable();
    }

    public void listener() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents( INSTACE.discoverRecipe, this);
    }


}
