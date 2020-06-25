package io.github.retrooper.easyanticheatbase.examplemain;

import io.github.retrooper.easyanticheatbase.EasyAnticheatBase;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMain extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        EasyAnticheatBase.start(this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        EasyAnticheatBase.stop();
    }
}
