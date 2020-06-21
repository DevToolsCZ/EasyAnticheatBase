package io.github.retrooper.easyanticheatbase;


import io.github.retrooper.easyanticheatbase.check.api.PrivateCheck;
import io.github.retrooper.easyanticheatbase.check.api.PublicCheck;
import io.github.retrooper.easyanticheatbase.check.manager.CheckManager;
import io.github.retrooper.easyanticheatbase.events.CheckEvent;
import io.github.retrooper.easyanticheatbase.events.PrivateCheckEvent;
import io.github.retrooper.easyanticheatbase.events.PublicCheckEvent;
import io.github.retrooper.easyanticheatbase.example.TestPrivateCheck;
import io.github.retrooper.easyanticheatbase.example.TestPublicCheck;
import io.github.retrooper.easyanticheatbase.playerdata.PlayerData;
import io.github.retrooper.easyanticheatbase.playerdata.PlayerDataManager;
import io.github.retrooper.packetevents.PacketEvents;
import io.github.retrooper.packetevents.annotations.PacketHandler;
import io.github.retrooper.packetevents.event.PacketEvent;
import io.github.retrooper.packetevents.event.PacketListener;
import io.github.retrooper.packetevents.event.impl.PacketReceiveEvent;
import io.github.retrooper.packetevents.event.impl.PacketSendEvent;
import io.github.retrooper.packetevents.event.impl.PlayerUninjectEvent;
import io.github.retrooper.packetevents.event.impl.PostPlayerInjectEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public final class EasyAnticheatBase implements PacketListener {
    private static EasyAnticheatBase instance;

    private static CheckManager checkManager;
    private static PlayerDataManager playerDataManager;

    public static void start(Plugin plugin) {
        PacketEvents.start(plugin);
        PacketEvents.getEventManager().registerListener(getInstance());

        getCheckManager().addCheck(new TestPublicCheck());
    }


    public static void stop() {
        PacketEvents.stop();
    }

    @PacketHandler
    public void onPostInject(PostPlayerInjectEvent event) {
        final PlayerData data = getPlayerDataManager().registerUser(event.getPlayer().getUniqueId());
        getCheckManager().addCheck(new TestPrivateCheck(data));
    }

    @PacketHandler
    public void onUninject(PlayerUninjectEvent event) {
        getPlayerDataManager().unregisterUser(event.getPlayer().getUniqueId());
        getCheckManager().getPrivateChecksMap().remove(event.getPlayer().getUniqueId());
    }

    @PacketHandler
    public void onPacket(final PacketEvent event) {
        //Cause has been fed into the constructor
        final CheckEvent checkEvent = new CheckEvent(event);
        for (final PublicCheck publicCheck : getCheckManager().getPublicChecks()) {
            PublicCheckEvent publicCheckEvent = new PublicCheckEvent(event);
            publicCheck.onPreCheck(publicCheckEvent);
        }

        final UUID uuid;
        if (event instanceof PacketReceiveEvent) {
            PacketReceiveEvent e = (PacketReceiveEvent) event;
            uuid = e.getPlayer().getUniqueId();
        } else if (event instanceof PacketSendEvent) {
            PacketSendEvent e = (PacketSendEvent) event;
            if (e.getPlayer() == null) {
                uuid = null;
            } else {
                uuid = e.getPlayer().getUniqueId();
            }
        } else if (event instanceof PostPlayerInjectEvent) {
            PostPlayerInjectEvent e = (PostPlayerInjectEvent) event;
            uuid = e.getPlayer().getUniqueId();
        } else {
            return;
        }
        if (uuid == null) return;
        if (!getCheckManager().getPrivateChecksMap().containsKey(uuid)) return;
        for (final PrivateCheck privateCheck : getCheckManager().getPrivateChecks(uuid)) {
            PrivateCheckEvent privateCheckEvent = new PrivateCheckEvent(event);
            privateCheck.onPreCheck(privateCheckEvent);
        }

    }

    public static CheckManager getCheckManager() {
        return checkManager == null ? checkManager = new CheckManager() : checkManager;
    }

    public static PlayerDataManager getPlayerDataManager() {
        return playerDataManager == null ? playerDataManager = new PlayerDataManager() : playerDataManager;
    }

    public static EasyAnticheatBase getInstance() {
        return instance == null ? instance = new EasyAnticheatBase() : instance;
    }
}
