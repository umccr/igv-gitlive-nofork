package org.broad.igv.aws;

import org.apache.log4j.Logger;
import org.broad.igv.event.IGVEventObserver;
import org.broad.igv.util.AmazonUtils;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventBridgeForwarder implements IGVEventObserver {
    private static final Logger log = Logger.getLogger(EventBridgeForwarder.class);

    private static EventBridgeClient eventBridgeClient;
    private static EventBridgeForwarder instance;
    private static final UUID uuid = UUID.randomUUID();

    public static EventBridgeForwarder getInstance() {
        if (instance == null) {
            instance = new EventBridgeForwarder();
        }
        return instance;
    };

    private EventBridgeForwarder() {
        updateCreds();
    };

    private void updateCreds() {
        eventBridgeClient = AmazonUtils.updateClientBuilder(EventBridgeClient.builder()).build();
    }

    @Override
    public void receiveEvent(Object event) {
        receiveEvent(event, "DefaultType");
    }

    public void receiveEvent(Object event, String detailType) {
        PutEventsRequestEntry reqEntry = PutEventsRequestEntry.builder()
                .source(uuid.toString())
                .detailType(detailType)
                .eventBusName("igv")
                .detail("{ \"event\": \""+event+"\" }")
                .resources()
                .build();

        List<PutEventsRequestEntry> requestEntries = new ArrayList<>();

        requestEntries.add(reqEntry);

        PutEventsRequest eventsRequest = PutEventsRequest.builder()
                .entries(requestEntries)
                .build();

        try {
            PutEventsResponse result = eventBridgeClient.putEvents(eventsRequest);
        } catch (SdkClientException creds) {
            log.debug("AWS EventBridge credentials are expired, trying to renew them", creds);
            updateCreds();
        }
//        TODO: inspect results to make sure event was received?

//        for(PutEventsResultEntry resultEntry: result.entries())
//        {
//            if (resultEntry.eventId() != null) {
//                System.out.println("Event Id: " + resultEntry.eventId());
//            } else {
//                System.out.println("PutEvents failed with Error Code: " + resultEntry.errorCode());
//            }
//        }
    }
}