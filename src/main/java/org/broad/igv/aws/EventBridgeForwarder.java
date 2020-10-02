package org.broad.igv.aws;

import org.broad.igv.event.IGVEventObserver;
import org.broad.igv.util.AmazonUtils;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.eventbridge.EventBridgeClient;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequest;
import software.amazon.awssdk.services.eventbridge.model.PutEventsRequestEntry;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResponse;
import software.amazon.awssdk.services.eventbridge.model.PutEventsResultEntry;
import software.amazon.awssdk.services.sts.model.Credentials;

import java.util.ArrayList;
import java.util.List;

public class EventBridgeForwarder implements IGVEventObserver {
    private static EventBridgeClient eventBridgeClient;
    private static EventBridgeForwarder eventBridgeForwarder = new EventBridgeForwarder();

    public static EventBridgeForwarder getInstance() {
        return eventBridgeForwarder;
    };

    private EventBridgeForwarder() {
        Credentials credentials = AmazonUtils.GetCognitoAWSCredentials();
        AwsSessionCredentials creds = AwsSessionCredentials.create(credentials.accessKeyId(),
                credentials.secretAccessKey(),
                credentials.sessionToken());

        StaticCredentialsProvider credsProvider = StaticCredentialsProvider.create(creds);

        eventBridgeClient = EventBridgeClient.builder().credentialsProvider(credsProvider).build();
    };

    @Override
    public void receiveEvent(Object event) {
        PutEventsRequestEntry reqEntry = PutEventsRequestEntry.builder()
                .source("igv-desktop") //
                .detailType("igv-event") // Change it to the source
                .eventBusName("igv")
                .detail("{ \"event\": \""+event+"\" }")
                .resources()
                .build();

        List<PutEventsRequestEntry> requestEntries = new ArrayList<>();

        requestEntries.add(reqEntry);

        PutEventsRequest eventsRequest = PutEventsRequest.builder()
                .entries(requestEntries)
                .build();

        PutEventsResponse result = eventBridgeClient.putEvents(eventsRequest);

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