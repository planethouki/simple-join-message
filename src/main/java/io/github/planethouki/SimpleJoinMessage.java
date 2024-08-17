package io.github.planethouki;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SimpleJoinMessage extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("onEnable is called!");

        // config.ymlが存在しない場合に作成
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // config.ymlからメッセージを取得
        String message = getConfig().getString("welcome-message", "Welcome to the server!");

        event.setJoinMessage(message);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // コマンドが "sjm" の場合
        if (command.getName().equalsIgnoreCase("sjm")) {
            // サブコマンドが "reload" の場合
            if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
                // config.yml を再読み込み
                reloadConfig();
                sender.sendMessage("Config reloaded!");
                return true;
            } else {
                sender.sendMessage("Usage: /sjm reload");
                return false;
            }
        }
        return false;
    }
}
