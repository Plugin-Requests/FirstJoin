package net.savagedev.firstjoin.commands;

import net.savagedev.firstjoin.FirstJoin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class FirstJoinCommand implements CommandExecutor {
    private final FirstJoin firstJoin;

    public FirstJoinCommand(FirstJoin firstJoin) {
        this.firstJoin = firstJoin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cInvalid arguments! Try: /firstjoin <amount|true|false>"));
            return true;
        }

        final String input = args[0];

        if (this.isNumber(input)) {
            final int amount = Integer.parseInt(input);
            if (amount < 1 || amount > 2304) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReward amount must be between 1 - 2,304." + amount));
                return true;
            }
            this.firstJoin.getConfig().set("reward-amount", amount);
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aReward amount updated! New amount: " + amount));
        } else {
            final boolean enabled = Boolean.parseBoolean(input);
            if (enabled) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aFirstJoin enabled!"));
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cFirstJoin disabled!"));
            }
            this.firstJoin.getConfig().set("enabled", enabled);
        }
        this.firstJoin.saveConfig();
        return true;
    }

    private boolean isNumber(String string) {
        if (string == null) {
            return false;
        }
        return string.chars().allMatch(Character::isDigit);
    }
}
