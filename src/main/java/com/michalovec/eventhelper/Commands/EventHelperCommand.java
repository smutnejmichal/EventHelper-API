package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.EventHelper;
import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EventHelperCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(" ");
            sender.sendMessage("§8§m------§r §4EventHelper Help §8§m------");
            sender.sendMessage("§c/eventhelper reload §7- Znovu načte konfiguraci");
            sender.sendMessage("§c/eventhelper version §7- Zobrazí verzi pluginu");
            sender.sendMessage("§c/eventhelper help §7- Zobrazí tuto nápovědu");
            sender.sendMessage("§c/eventsettings §7- Otevře GUI pro částečné nastavení eventu");
            sender.sendMessage("§c/tphere <hráč> §7- Teleportuje hráče na vaší lokaci");
            sender.sendMessage("§c/tpall §7- Teleportuje veškeré hráče na vaší lokaci");
            sender.sendMessage("§c/createwarp §7- Vytvoří warp");
            sender.sendMessage("§c/removewarp §7- Odstraní warp");
            sender.sendMessage("§c/warp <warp> §7- Teleporujte vás na daný warp");
            sender.sendMessage(" ");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            MessageManager.reload();
            sender.sendMessage(MessageManager.get("messages.configReloaded"));
            return true;
        }

        if (args[0].equalsIgnoreCase("version")) {
            String version = EventHelper.getInstance().getDescription().getVersion();
            sender.sendMessage(MessageManager.get("messages.versionMessage").replace("%version%", version));
            return true;
        }

        sender.sendMessage(MessageManager.get("messages.unknownSubcommand"));
        return true;
    }
}
