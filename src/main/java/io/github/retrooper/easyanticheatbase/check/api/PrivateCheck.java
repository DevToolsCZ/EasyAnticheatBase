package io.github.retrooper.easyanticheatbase.check.api;

import io.github.retrooper.easyanticheatbase.check.api.data.Category;
import io.github.retrooper.easyanticheatbase.events.CheckEvent;
import io.github.retrooper.easyanticheatbase.events.PrivateCheckEvent;
import io.github.retrooper.easyanticheatbase.playerdata.PlayerData;

public abstract class PrivateCheck extends Check {
    private final PlayerData playerData;

    public PrivateCheck(final PlayerData data, String name, char checkType, Category category) {
        super(name, checkType, category);
        this.playerData = data;
    }

    @Override
    public CheckEvent onPreCheck(final CheckEvent checkEvent) {
        if (checkEvent instanceof PrivateCheckEvent) {
            onCheck((PrivateCheckEvent) checkEvent);
        }
        return checkEvent;
    }


    public PrivateCheckEvent onCheck(final PrivateCheckEvent e) {
        return e;
    }

    public void violate() {
        getPlayerData().incrementVL(this);
    }

    public void comply() {
        getPlayerData().decrementVL(this);
    }

    public float getVL() {
        return getPlayerData().getVLCount(this);
    }

    public void clearVL() {
        getPlayerData().clearVL(this);
    }

    public void flag(final String message) {
        System.out.println(message);
    }

    public final PlayerData getPlayerData() {
        return playerData;
    }
}
