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
            sender.sendMessage(""" 
                    §8§m        §r §4EventHelper Help §8§m        §r
                    §c/eventhelper reload §7- Znovu načte konfiguraci
                    §c/eventhelper version §7- Zobrazí verzi pluginu
                    §c/eventhelper help §7- Zobrazí tuto nápovědu
                    §c/eventsettings §7- Otevře GUI pro částečné nastavení eventu
                    §c/tphere <hráč> §7- Teleportuje hráče na vaší lokaci
                    §c/tpall §7- Teleportuje veškeré hráče na vaší lokaci
                    §c/createwarp §7- Vytvoří warp
                    §c/removewarp §7- Odstraní  warp
                    §c/warp <warp> §7- Teleporujte vás na daný warp
                    """);
            return false;
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
