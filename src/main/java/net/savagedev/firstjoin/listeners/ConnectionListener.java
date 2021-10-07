package net.savagedev.firstjoin.listeners;

import net.savagedev.firstjoin.FirstJoin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class ConnectionListener implements Listener {
    private final FirstJoin firstJoin;

    public ConnectionListener(FirstJoin firstJoin) {
        this.firstJoin = firstJoin;
    }

    @EventHandler
    public void on(PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        if (player.hasPlayedBefore()) {
            return; // Player has already played.
        }

        if (!this.firstJoin.getConfig().getBoolean("enabled")) {
            return; // Rewards not enabled.
        }

        player.getInventory().addItem(new ItemStack(Material.DIAMOND, this.firstJoin.getConfig().getInt("reward-amount")));
    }
}
