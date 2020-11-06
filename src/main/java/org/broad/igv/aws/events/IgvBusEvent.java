package org.broad.igv.aws.events;

import org.broad.igv.event.DataLoadedEvent;
import org.broad.igv.event.GenomeChangeEvent;

public class IgvBusEvent extends Event {

    public IgvBusEvent(Object igvEvent) {
//        TODO: parse igvEvents for useful information
        if (igvEvent instanceof GenomeChangeEvent) {
            this.payload = "igvBus.GenomeChangeEvent";
        } else if (igvEvent instanceof DataLoadedEvent) {
            this.payload = "igvBus.DataLoadedEvent";
        } else {
            this.payload = "igvBus.Event";
        }
    }

    @Override
    public String getDetailType() {
        return "IgvBusEvent";
    }
}
