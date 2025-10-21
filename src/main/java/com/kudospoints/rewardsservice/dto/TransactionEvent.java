package com.kudospoints.rewardsservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionEvent {
    private UUID memberId;
    private BigDecimal amount;
    private String paymentMethod;
}
