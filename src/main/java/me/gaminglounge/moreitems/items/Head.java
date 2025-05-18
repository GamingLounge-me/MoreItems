package me.gaminglounge.moreitems.items;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.executors.PlayerCommandExecutor;
import me.gaminglounge.itembuilder.ItemBuilder;
import me.gaminglounge.moreitems.Command;
import me.gaminglounge.moreitems.MoreItems;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Head {
    MoreItems mi = MoreItems.INSTACE;
    MiniMessage mm = MiniMessage.miniMessage();

    public CommandAPICommand command() {
        return new CommandAPICommand("head")
            .withPermission("MoreItems.command.give.head")
            .withOptionalArguments(new PlayerArgument("player_head"), new PlayerArgument("give_player"))
            .executes((sender, args) -> {
                Player head = (Player) args.get("player_head");
                Player give = (Player) args.get("give_player");

                if (!(sender instanceof PlayerCommandExecutor p)) {
                    if (head == null || give == null) {
                        Command.msg(sender, "command.give.head.usage");
                        return;
                    }
                } else {
                    if (head == null) {
                        head = (Player) p;
                    }
                    if (give == null) {
                        give = (Player) p;
                    }
                }
                
                Inventory inv = give.getInventory();
                if (inv.firstEmpty() > 0) {
                    inv.addItem(new ItemBuilder(head.getUniqueId()).build());
                } else {
                    Command.msg(sender, "inv.full");
                }
            }
        );
    }
}
