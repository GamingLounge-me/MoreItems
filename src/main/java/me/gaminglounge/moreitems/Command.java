package me.gaminglounge.moreitems;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.executors.PlayerCommandExecutor;
import me.gaminglounge.itembuilder.ItemBuilder;

public class Command {
    MoreItems mi = MoreItems.INSTACE;

    public Command() {
        new CommandAPICommand("items")
            .withPermission("MoreItems.command")
            .withAliases("MoreItems:items", "MoreItems:mi", "mi")
            .executes((sender, args) -> {
                // command list
                // sender.sendMessage("");
            })
            .withSubcommand(
                new CommandAPICommand("give")
                    .withPermission("MoreItems.command.give")
                    .executes((sender, args) -> {
                        // list of items / subcommands
                        // gui with all item to pick?
                    })
                    .withSubcommand(
                        new CommandAPICommand("head")
                            .withPermission("MoreItems.command.give.head")
                            .withOptionalArguments(new PlayerArgument("player_head"), new PlayerArgument("give_player"))
                            .executes((sender, args) -> {
                                Player head = (Player) args.get("player_head");
                                Player give = (Player) args.get("give_player");

                                if (!(sender instanceof PlayerCommandExecutor p)) {
                                    if (head == null || give == null) {
                                        sender.sendMessage("if not executed by a player you need to give /mi give head <player_head> <give_player>");
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
                                    sender.sendMessage("inv of player is full.");
                                }
                            })
                    )
            )
        .register();
    }

}
