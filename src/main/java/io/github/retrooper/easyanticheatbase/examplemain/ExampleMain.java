package io.github.retrooper.easyanticheatbase.examplemain;

import io.github.retrooper.easyanticheatbase.EasyAnticheatBase;
import org.bukkit.plugin.java.JavaPlugin;

public class ExampleMain extends JavaPlugin {
    @Override
    public void onEnable() {
        EasyAnticheatBase.start(this);
    }

    @Override
    public void onDisable() {
        EasyAnticheatBase.stop();
    }


}
