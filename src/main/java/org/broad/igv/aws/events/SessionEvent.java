package org.broad.igv.aws.events;

public class SessionEvent extends Event {

    private SessionEvent(String payload) {
        this.payload = payload;
    }

    public static SessionEvent createSessionSaveEvent(Object sessionContent) {
        // TODO: parse session content for additional information
        return new SessionEvent("save");
    }

    public static SessionEvent createSessionLoadEvent(Object sessionContent) {
        return new SessionEvent("load");
    }

    @Override
    public String getDetailType() {
        return "SessionEvent";
    }
}
