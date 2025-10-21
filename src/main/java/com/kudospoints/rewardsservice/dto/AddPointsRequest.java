package com.kudospoints.rewardsservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPointsRequest {
    private int points;
    private String type;
    private String transactionId;
    private String notes;
}
