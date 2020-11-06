package org.broad.igv.aws.events;

public class IgvBusEvent extends Event {

    public IgvBusEvent(Object igvEvent) {
//        TODO: parse igvEvents for useful information
        this.payload = "igvBusEvent";
    }

    @Override
    public String getDetailType() {
        return "IgvBusEvent";
    }
}
