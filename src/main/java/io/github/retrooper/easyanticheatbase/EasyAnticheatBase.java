package io.github.retrooper.easyanticheatbase;


import io.github.retrooper.easyanticheatbase.check.api.PrivateCheck;
import io.github.retrooper.easyanticheatbase.check.api.PublicCheck;
import io.github.retrooper.easyanticheatbase.check.manager.CheckManager;
import io.github.retrooper.easyanticheatbase.events.CheckEvent;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketSendEvent;
import io.github.retrooper.packetevents.event.impl.PlayerInjectEvent;
import io.github.retrooper.packetevents.event.impl.PostPlayerInjectEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public final class EasyAnticheatBase implements PacketListener {
    private static EasyAnticheatBase instance;

    private static CheckManager checkManager;
    public static void start(Plugin plugin) {
        PacketEvents.start(plugin);
        PacketEvents.getEventManager().registerListener(getInstance());
    }


    public static void stop() {
        PacketEvents.stop();
    }

    public static CheckManager getCheckManager() {
        return checkManager == null ? checkManager = new CheckManager() : checkManager;
    }

    public static EasyAnticheatBase getInstance() {
        return instance == null ? instance = new EasyAnticheatBase() : instance;
    }

    @PacketHandler
    public void onPacket(final PacketEvent event) {
        //Cause has been fed into the constructor
        final CheckEvent checkEvent = new CheckEvent(event);
        for(final PublicCheck publicCheck : getCheckManager().getPublicChecks()) {
            publicCheck.onPreCheck(checkEvent);
        }
        final UUID uuid;
        if(event instanceof PacketReceiveEvent)  {
            PacketReceiveEvent e = (PacketReceiveEvent) event;
            uuid = e.getPlayer().getUniqueId();
        }
        else if(event instanceof PacketSendEvent) {
            PacketSendEvent e = (PacketSendEvent) event;
            uuid = e.getPlayer().getUniqueId();
        }
        else if(event instanceof PlayerInjectEvent) {
            PlayerInjectEvent e = (PlayerInjectEvent) event;
            uuid = e.getPlayer().getUniqueId();
        }
        else if(event instanceof PostPlayerInjectEvent) {
            PostPlayerInjectEvent e = (PostPlayerInjectEvent) event;
            uuid = e.getPlayer().getUniqueId();
        }
        else {
            return;
        }

        for(final PrivateCheck privateCheck : getCheckManager().getPrivateChecks(uuid)) {
            privateCheck.onPreCheck(checkEvent);
        }

    }
}