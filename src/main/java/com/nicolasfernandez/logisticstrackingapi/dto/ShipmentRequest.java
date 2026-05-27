package com.nicolasfernandez.logisticstrackingapi.dto;

import com.nicolasfernandez.logisticstrackingapi.entity.ShipmentStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShipmentRequest {

    @NotBlank(message = "Tracking number is required")
    @Size(
            min = 5,
            max = 20,
            message = "Tracking number must be between 5 and 20 characters"
    )
    private String trackingNumber;

    @NotBlank(message = "Origin country is required")
    private String originCountry;

    @NotBlank(message = "Destination country is required")
    private String destinationCountry;

    @NotNull(message = "Status is required")
    private ShipmentStatus status;

}
