package io.github.planethouki;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleJoinMessage extends JavaPlugin implements Listener {

    private String defaultMessage;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("onEnable is called!");

        // config.ymlが存在しない場合に作成
        saveDefaultConfig();

        loadConfigValues();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.AQUA + "[Server] " + ChatColor.RESET + defaultMessage);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // コマンドが "sjm" の場合
        if (command.getName().equalsIgnoreCase("sjm")) {
            // サブコマンドが "reload" の場合
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                loadConfigValues();
                sender.sendMessage("Config reloaded!");
                return true;
            } else {
                sender.sendMessage("Usage: /sjm reload");
                return false;
            }
        }
        return false;
    }

    private void loadConfigValues() {
        reloadConfig();

        FileConfiguration config = getConfig();

        defaultMessage = config.getString("welcome-message", "Welcome to the server!");
    }
}
