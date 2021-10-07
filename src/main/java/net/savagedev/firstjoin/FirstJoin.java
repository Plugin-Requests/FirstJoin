package net.savagedev.firstjoin;

import net.savagedev.firstjoin.commands.FirstJoinCommand;
import net.savagedev.firstjoin.listeners.ConnectionListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class FirstJoin extends JavaPlugin {
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.initListeners();
        this.initCommands();
    }

    private void initListeners() {
        final PluginManager pluginManager = this.getServer().getPluginManager();
        pluginManager.registerEvents(new ConnectionListener(this), this);
    }

    private void initCommands() {
        final PluginCommand command = this.getCommand("firstjoin");
        if (command == null) {
            this.getLogger().log(Level.SEVERE, "Unable to register command! Is it in the plugin.yml?");
        } else {
            command.setExecutor(new FirstJoinCommand(this));
        }
    }
}
