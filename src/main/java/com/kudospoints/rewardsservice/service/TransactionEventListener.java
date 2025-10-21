package com.kudospoints.rewardsservice.service;

import com.kudospoints.rewardsservice.dto.AddPointsRequest;
import com.kudospoints.rewardsservice.dto.TransactionEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventListener {

    private final PointsServiceClient pointsServiceClient;

    public TransactionEventListener(PointsServiceClient pointsServiceClient) {
        this.pointsServiceClient = pointsServiceClient;
    }

    @RabbitListener(queues = "transactions.queue")
    public void handleTransactionEvent(TransactionEvent event) {
        System.out.println("Received event for member: " + event.getMemberId());

        // Step 1: The Business Logic (Rules Engine)
        int pointsToAward = calculatePoints(event);
        System.out.println("Calculated " + pointsToAward + " points to award.");

        // Step 2: Prepare the command to send to the points-service
        AddPointsRequest command = AddPointsRequest.builder()
                .points(pointsToAward)
                .type("EARN_PURCHASE")
                .notes("Points awarded for purchase. Payment method: " + event.getPaymentMethod())
                .build();

        // Step 3: Send the command via the API client
        pointsServiceClient.addPoints(event.getMemberId(), command);
    }

    private int calculatePoints(TransactionEvent event) {
        // Rule: 5x points for COMPANY_CARD, 1x for everything else
        if ("COMPANY_CARD".equalsIgnoreCase(event.getPaymentMethod())) {
            return event.getAmount().intValue() * 5;
        }
        return event.getAmount().intValue();
    }
}
