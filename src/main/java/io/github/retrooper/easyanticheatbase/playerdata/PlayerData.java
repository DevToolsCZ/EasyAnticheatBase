package io.github.retrooper.easyanticheatbase.playerdata;

import java.util.UUID;

public final class PlayerData {
    private final UUID uuid;

    public PlayerData(final UUID uuid){
        this.uuid = uuid;
    }

    //EXAMPLE VARIABLES, APPEND OR REMOVE if you like

    public int ping;
    public int cps;

    public float yaw, pitch;

    public UUID getUniqueId() {
        return uuid;
    }
}
