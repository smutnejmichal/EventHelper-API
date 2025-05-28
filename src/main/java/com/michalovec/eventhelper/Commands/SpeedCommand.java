package com.michalovec.eventhelper.Commands;

import com.michalovec.eventhelper.Managers.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpeedCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    if (isInteger(args[0])) {
                        int speed_int = Integer.parseInt(args[0]);
                        if (player.isFlying()) {
                            player.setFlySpeed(convertSpeed(speed_int, true));
                        }
                        else {
                            player.setWalkSpeed(convertSpeed(speed_int, false));
                        }
                        player.sendMessage(MessageManager.getWithWarp("messages.speedChanged", args[0]));
                    }
                    else player.sendMessage(MessageManager.get("messages.speedNumberMustBeInt"));
                }
                else player.sendMessage(MessageManager.get("messages.speedWrongUsage"));
            }
            else player.sendMessage(MessageManager.get("messages.playerIsNotOP"));
        }
        else sender.sendMessage(MessageManager.get("messages.onlyPlayer"));

        return true;
    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private float convertSpeed(int level, boolean isFlying) {
        if (level < 1) level = 1;
        if (level > 10) level = 10;

        if (isFlying) {
            return 0.1f + (level - 1) * (0.9f / 9f);
        }

        return 0.2f + (level - 1) * ((1.0f - 0.2f) / 9f);
    }
}
