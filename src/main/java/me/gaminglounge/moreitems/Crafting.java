package me.gaminglounge.moreitems;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;

public class Crafting implements Listener {

    private List<NamespacedKey> rn;

    public void init() {
        rn = new ArrayList<>();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (NamespacedKey r : rn) {
            event.getPlayer().discoverRecipe(r);
        }
    }

    public void crafting(NamespacedKey key, Recipe recipe) {
        if (MoreItems.INSTACE.getConfig().getBoolean("Crafting." + key.getKey())) {
            if (Bukkit.getRecipe(key) == null) {
                Bukkit.getServer().addRecipe(recipe);
                Bukkit.updateRecipes();
            }
            rn.add(key);
        } else {
            if (Bukkit.getRecipe(key) != null) {
                Bukkit.getServer().removeRecipe(key);
                Bukkit.updateRecipes();
            }
        }
    }

}
