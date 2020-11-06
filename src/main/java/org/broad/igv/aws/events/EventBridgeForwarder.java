package org.broad.igv.aws.events;

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
        if (AmazonUtils.isLoggedin()) {
            eventBridgeClient = AmazonUtils.updateClientBuilder(EventBridgeClient.builder()).build();
        } else {
            throw new IllegalStateException("Trying to create AWS client without credentials! Have to be logged in.");
        }
    }

    @Override
    public void receiveEvent(Object event) {
        // TODO: make sure we are receiving IGV EventBus events
        sendEvent(new IgvBusEvent(event));
    }

    public void sendEvent(Event event) {
        if (!AmazonUtils.isLoggedin()) {
            return;
        }

        PutEventsRequestEntry reqEntry = PutEventsRequestEntry.builder()
                .source("igv.desktop")
                .detailType(event.getDetailType())
                .eventBusName("igv")
                .detail(event.getPayload())
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

    }
}