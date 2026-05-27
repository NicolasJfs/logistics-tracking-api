package com.nicolasfernandez.logisticstrackingapi.dto;

import com.nicolasfernandez.logisticstrackingapi.entity.ShipmentStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ShipmentResponse {

    private Long id;
    private String trackingNumber;
    private String originCountry;
    private String destinationCountry;
    private ShipmentStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
