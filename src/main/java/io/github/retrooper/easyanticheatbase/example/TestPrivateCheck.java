package io.github.retrooper.easyanticheatbase.example;

import io.github.retrooper.easyanticheatbase.check.api.PrivateCheck;
import io.github.retrooper.easyanticheatbase.check.api.data.Category;
import io.github.retrooper.easyanticheatbase.events.PrivateCheckEvent;
import io.github.retrooper.easyanticheatbase.playerdata.PlayerData;
import io.github.retrooper.packetevents.event.impl.BukkitMoveEvent;

public class TestPrivateCheck extends PrivateCheck {
    public TestPrivateCheck(final PlayerData data) {
        super(data, "TestCheck", 'B', Category.MOVEMENT);
    }

    @Override
    public PrivateCheckEvent onCheck(final PrivateCheckEvent e) {
        if (e.getCauseEvent() instanceof BukkitMoveEvent) {
            BukkitMoveEvent moveEvent = (BukkitMoveEvent) e.getCauseEvent();

            double deltaXZ = moveEvent.getTo().getX() - moveEvent.getFrom().getX() + moveEvent.getTo().getZ() - moveEvent.getFrom().getZ();

           // moveEvent.getPlayer().sendMessage("deltaXZ: " + deltaXZ);

        }
        return super.onCheck(e);
    }

}
