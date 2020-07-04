package io.github.retrooper.easyanticheatbase.playerdata;

import io.github.retrooper.easyanticheatbase.check.api.Check;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class PlayerData {
    private final UUID uuid;

    public PlayerData(final UUID uuid) {
        this.uuid = uuid;
    }

    //EXAMPLE VARIABLES, APPEND OR REMOVE if you like

    private final Map<Class<?>, Float> violations = new HashMap<Class<?>, Float>();

    public void setVLCount(final Check check, final float value) {
        violations.put(check.getClass(), value);
    }

    public float getVLCount(final Check check) {
        return violations.getOrDefault(check.getClass(), 0.0f);
    }

    public void addVL(final Check check, final float val) {
        setVLCount(check, getVLCount(check) + val);
    }

    public void subtractVL(final Check check, final float val) {
        addVL(check, -val);
    }

    public void incrementVL(final Check check) {
        addVL(check, 1.0F);
    }

    public void decrementVL(final Check check) {
        subtractVL(check, 1.0F);
    }

    public void clearVL(final Check check) {
        setVLCount(check, 0.0F);
    }

    public Map<Class<?>, Float> getViolationsMap() {
        return violations;
    }

    public UUID getUniqueId() {
        return uuid;
    }
}
