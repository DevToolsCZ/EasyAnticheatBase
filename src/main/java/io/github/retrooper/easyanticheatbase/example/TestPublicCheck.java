package io.github.retrooper.easyanticheatbase.example;

import io.github.retrooper.easyanticheatbase.EasyAnticheatBase;
import io.github.retrooper.easyanticheatbase.check.api.PublicCheck;
import io.github.retrooper.easyanticheatbase.check.api.data.Category;
import io.github.retrooper.easyanticheatbase.events.PublicCheckEvent;
import io.github.retrooper.easyanticheatbase.playerdata.PlayerData;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.packet.PacketType;

public class TestPublicCheck extends PublicCheck {
    public TestPublicCheck() {
        super("TestCheck", 'A', Category.MOVEMENT);
    }

    @Override
    public PublicCheckEvent onCheck(PublicCheckEvent e) {
        if (e.getCauseEvent() instanceof PacketReceiveEvent) {
            PacketReceiveEvent receiveEvent = (PacketReceiveEvent) e.getCauseEvent();
            if (PacketType.Client.Util.isInstanceOfFlying(receiveEvent.getPacketId())) {
                if (receiveEvent.getPlayer() == null || receiveEvent.getPlayer().getUniqueId() == null) {
                    return e;
                }
                PlayerData data = EasyAnticheatBase.getPlayerDataManager().find(receiveEvent.getPlayer().getUniqueId());
                if (data == null) {
                    return e;
                }
            }
        }
        return super.onCheck(e);
    }
}
