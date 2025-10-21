package com.kudospoints.rewardsservice.service;

import com.kudospoints.rewardsservice.dto.AddPointsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class PointsServiceClient {

    private final RestTemplate restTemplate;

    @Value("${points.service.base-url}")
    private String pointsServiceBaseUrl;

    public PointsServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void addPoints(UUID memberId, AddPointsRequest request) {
        String url = pointsServiceBaseUrl + "/api/v1/members/" + memberId + "/transactions";
        try {
            restTemplate.postForEntity(url, request, Void.class);
            System.out.println("Successfully sent command to Points Service for member: " + memberId);
        } catch (Exception e) {
            System.err.println("Error calling Points Service: " + e.getMessage());
        }
    }
}
