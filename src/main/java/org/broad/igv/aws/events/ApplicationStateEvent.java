package org.broad.igv.aws.events;

public class ApplicationStateEvent extends Event {

    private ApplicationStateEvent(String payload) {
        this.payload = payload;
    }

    public static ApplicationStateEvent createLogonEvent() {
        return new ApplicationStateEvent("logon");
    }

    public static ApplicationStateEvent createLogoffEvent() {
        return new ApplicationStateEvent("logoff");
    }

    public static ApplicationStateEvent createExitEvent() {
        return new ApplicationStateEvent("exit");
    }

    @Override
    public String getDetailType() {
        return "ApplicationStateEvent";
    }
}
