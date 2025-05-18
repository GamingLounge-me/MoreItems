package me.gaminglounge.moreitems.listener;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DiscoverRecipe implements Listener {

    private List<NamespacedKey> rn;

    public void init() {
        rn = new ArrayList<>();
    }

    public void register(NamespacedKey key) {
        rn.add(key);
        Bukkit.getServer().getOnlinePlayers().forEach(p -> {
            p.discoverRecipe(key);
        });
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        for (NamespacedKey r : rn) {
            event.getPlayer().discoverRecipe(r);
        }
    }

}
