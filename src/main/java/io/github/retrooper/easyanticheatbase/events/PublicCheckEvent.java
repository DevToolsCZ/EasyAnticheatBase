package io.github.retrooper.easyanticheatbase.events;

import io.github.retrooper.packetevents.event.PacketEvent;

public final class PublicCheckEvent extends CheckEvent {
    public PublicCheckEvent(PacketEvent causeEvent) {
        super(causeEvent);
    }
}
