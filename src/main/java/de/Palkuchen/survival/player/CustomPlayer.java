package de.Palkuchen.survival.player;

import java.util.ArrayList;
import java.util.List;

public class CustomPlayer {

    private List<String> tpaRequests = new ArrayList<>();

    String uuid;

    public CustomPlayer(String uuid) {
        this.uuid = uuid;
    }

    public void addTpa(String uuid) {
        tpaRequests.add(uuid);
    }

    public boolean hasTpa(String uuid) {
        return tpaRequests.contains(uuid);
    }
}
