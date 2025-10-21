package com.kudospoints.rewardsservice.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventListener {

    @RabbitListener(queues = "transactions.queue")
    public void handleTransactionEvent(String message) {
        System.out.println("Received event for member: " + message);
    }
}
