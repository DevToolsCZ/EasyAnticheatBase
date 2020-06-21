package io.github.retrooper.easyanticheatbase.check.manager;

import io.github.retrooper.easyanticheatbase.check.api.Check;
import io.github.retrooper.easyanticheatbase.check.api.PrivateCheck;
import io.github.retrooper.easyanticheatbase.check.api.PublicCheck;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public final class CheckManager {
    private final List<PublicCheck> publicChecks = new ArrayList<>();
    private final HashMap<UUID, List<PrivateCheck>> privateChecks = new HashMap<UUID, List<PrivateCheck>>();


    public void addCheck(final Check check) {
        if (check instanceof PublicCheck) {
            publicChecks.add((PublicCheck) check);
        } else if (check instanceof PrivateCheck) {
            PrivateCheck privateCheck = (PrivateCheck)check;
            if(privateChecks.containsKey(privateCheck.getPlayerData().getUniqueId())) {
                privateChecks.get(privateCheck.getPlayerData().getUniqueId()).add(privateCheck);
            }
            else {
                List<PrivateCheck> list = new ArrayList<PrivateCheck>();
                list.add(privateCheck);
                privateChecks.put(privateCheck.getPlayerData().getUniqueId(), list);
            }
        }
    }

    public void removeCheck(final Check check) {
        if (check instanceof PublicCheck) {
            publicChecks.remove(check);
        } else if (check instanceof PrivateCheck) {
            privateChecks.remove(check);
        }
    }

    public void clearChecks() {
        clearPublicChecks();
        clearPrivateChecks();
    }

    public void clearPublicChecks() {
        publicChecks.clear();
    }

    public void clearPrivateChecks() {
        privateChecks.clear();
    }

    public List<PublicCheck> getPublicChecks() {
        return publicChecks;
    }

    public HashMap<UUID, List<PrivateCheck>> getPrivateChecksMap() {
        return privateChecks;
    }

    public List<PrivateCheck> getPrivateChecks(final UUID uuid) {
        return privateChecks.get(uuid);
    }
}
