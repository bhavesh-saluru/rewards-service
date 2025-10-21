package com.kudospoints.rewardsservice.service;

import com.kudospoints.rewardsservice.dto.TransactionEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventListener {

    @RabbitListener(queues = "transactions.queue")
    public void handleTransactionEvent(TransactionEvent event) {
        System.out.println("Received event for member: " + event.getMemberId());
        System.out.println("Amount: " + event.getAmount());
        System.out.println("Payment method type: " + event.getPaymentMethod());
        System.out.println("--------------------------");
    }
}
