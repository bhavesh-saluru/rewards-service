package com.kudospoints.rewardsservice.controller;

import com.kudospoints.rewardsservice.dto.TransactionEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
public class PublishEventController {

    private final String EXCHANGE_NAME = "kudospoints.exchange"; // Central mail sorting room
    private final String ROUTING_KEY = "transactions.completed"; // Pin code to PO Box (event)
    private final RabbitTemplate rabbitTemplate;

    public PublishEventController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/publish")
    public ResponseEntity<Void> publishEvent(@RequestBody TransactionEvent event) {
        System.out.println("Received event for: " + event.getMemberId());

        // Publish the event to RabbitMQ (our existing logic)
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, event);

        return ResponseEntity.ok().build();
    }
}
