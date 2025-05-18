package me.gaminglounge.moreitems;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import me.gaminglounge.configapi.LoadConfig;
import me.gaminglounge.moreitems.listener.DiscoverRecipe;

public class MoreItems extends JavaPlugin {

    public static MoreItems INSTACE;
    private DiscoverRecipe discoverRecipe;

    @Override
    public void onLoad() {
        INSTACE = this;
        INSTACE.saveDefaultConfig();

        Map<String, InputStream> lang = new HashMap<>();
        lang.put("en_US.json", this.getResource("lang/en_US.json"));
        lang.put("de_DE.json", this.getResource("lang/de_DE.json"));
        LoadConfig.registerLanguage(this, lang);

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
