package io.github.retrooper.easyanticheatbase.example;

import io.github.retrooper.easyanticheatbase.check.api.PrivateCheck;
import io.github.retrooper.easyanticheatbase.check.api.data.Category;
import io.github.retrooper.easyanticheatbase.events.PrivateCheckEvent;
import io.github.retrooper.easyanticheatbase.playerdata.PlayerData;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;





















import org.bukkit.Bukkit;

public class TestPrivateCheck extends PrivateCheck {
    private int ping = 0;
    public TestPrivateCheck(PlayerData data) {
        super(data, "TestCheck", 'B', Category.MOVEMENT);
    }

    @Override
    public PrivateCheckEvent onCheck(PrivateCheckEvent e) {
        if(e.getCauseEvent() instanceof PacketReceiveEvent) {
            Bukkit.getPlayer(getPlayerData().getUniqueId()).sendMessage("your ping is " + ping++);
            //System.out.println( getPlayerData().getUniqueId() + "'s ping is " + ping++);
        }
        return super.onCheck(e);
    }
}
