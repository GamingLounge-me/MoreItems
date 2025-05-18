package me.gaminglounge.moreitems.items;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import me.gaminglounge.itembuilder.ItemBuilder;
import me.gaminglounge.moreitems.Crafting;
import me.gaminglounge.moreitems.MoreItems;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Exmaple {
    MoreItems mi = MoreItems.INSTACE;
    MiniMessage mm = MiniMessage.miniMessage();

    public Exmaple() {
        crafting();
    }

    private ItemStack item() {
        return new ItemBuilder(Material.BEDROCK)
            .setName(mm.deserialize("<red>Test Item </red>"))
            .build();
    }

    private void crafting() {
        NamespacedKey key = new NamespacedKey(mi, "exmaple");
        ShapelessRecipe recipe = new ShapelessRecipe(key, item());

        recipe.addIngredient(1, Material.BEDROCK);

        MoreItems.CRAFTING.crafting(key, recipe);
    }

}
