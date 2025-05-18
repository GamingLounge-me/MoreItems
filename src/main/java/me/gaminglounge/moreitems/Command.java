package me.gaminglounge.moreitems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.PlayerCommandExecutor;
import me.gaminglounge.configapi.Language;
import me.gaminglounge.moreitems.items.Head;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class Command {
    MoreItems mi = MoreItems.INSTACE;
    MiniMessage mm = MiniMessage.miniMessage();
    FileConfiguration conf = mi.getConfig();

    public Command() {
        CommandAPICommand command = new CommandAPICommand("items")
            .withPermission("MoreItems.command")
            .withAliases("MoreItems:items", "MoreItems:mi", "mi");

        CommandAPICommand give = new CommandAPICommand("give")
            .withPermission("MoreItems.command.give");

        // set subcommands of give command
        List<CommandAPICommand> gsc = new ArrayList<>();
        if (conf.getBoolean("command.give.head")) gsc.add(new Head().command());
        
        give.setSubcommands(gsc);

        // set subcommands of command command
        List<CommandAPICommand> csc = new ArrayList<>();
        csc.add(give);

        command.setSubcommands(csc);

        // register command
        command.register();
    }

    public static void msg(CommandSender sender, String key) {
        MoreItems mi = MoreItems.INSTACE;
        MiniMessage mm = MiniMessage.miniMessage();
        if (sender instanceof Player pcx) {
            Player p = (Player) pcx;
            p.sendMessage(mm.deserialize(Language.getValue(mi, p, key)));
        } else {
            String msg = Language.getValue(mi, "en_US", key);
            // remove "<...>"(minimessage) tags
            sender.sendMessage(msg);
        }
    }

}
