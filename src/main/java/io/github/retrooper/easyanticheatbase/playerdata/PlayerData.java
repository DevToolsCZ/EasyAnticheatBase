package io.github.retrooper.easyanticheatbase.playerdata;

import java.util.UUID;

public final class PlayerData {
    private final UUID uuid;

    public PlayerData(final UUID uuid){
        this.uuid = uuid;
    }

    //EXAMPLE VARIABLES, APPEND OR REMOVE if you like

    public int packetCount = 0;
    public int cps = 0;

    public float yaw = 0F, pitch = -90F;

    public UUID getUniqueId() {
        return uuid;
    }
}
