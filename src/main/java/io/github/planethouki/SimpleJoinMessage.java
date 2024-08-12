package io.github.planethouki;
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
}
