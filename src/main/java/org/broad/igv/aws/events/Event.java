package org.broad.igv.aws.events;

import java.util.UUID;

public abstract class Event {

    private static final UUID uuid = UUID.randomUUID();

    protected String payload;

    public abstract String getDetailType();

    public String getPayload() {
        return "{ \"payload\": \""+this.payload+"\", \"sessionId\": \""+uuid+"\" }";
    }
}
