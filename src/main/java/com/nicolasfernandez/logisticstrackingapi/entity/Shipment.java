package com.nicolasfernandez.logisticstrackingapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

    @Entity
    @Table(name = "shipments")
    @Getter
    @Setter
    public class Shipment
    {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @NotBlank(message = "Tracking number is required")
        @Size(min = 5, max = 20, message = "Tracking number must be between 5 and 20 characters")
        @Column(nullable = false, unique = true)
        private String trackingNumber;

        @NotBlank(message = "Origin country is required")
        @Column(nullable = false)
        private String originCountry;

        @NotBlank(message = "Destination country is required")
        @Column(nullable = false)
        private String destinationCountry;

        @NotNull(message = "Status is required")
        @Enumerated(EnumType.STRING)
        private ShipmentStatus status;

        private LocalDateTime createdAt;

        private LocalDateTime updatedAt;

        @PrePersist
        public void prePersist() {
            createdAt = LocalDateTime.now();
            updatedAt = LocalDateTime.now();

            if (status == null) {
                status = ShipmentStatus.CREATED;
            }
        }

        @PreUpdate
        public void preUpdate() {
            updatedAt = LocalDateTime.now();
        }

    }