package com.nicolasfernandez.logisticstrackingapi.service;

import com.nicolasfernandez.logisticstrackingapi.dto.ShipmentRequest;
import com.nicolasfernandez.logisticstrackingapi.dto.ShipmentResponse;
import com.nicolasfernandez.logisticstrackingapi.entity.Shipment;
import com.nicolasfernandez.logisticstrackingapi.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    public ShipmentResponse createShipment(ShipmentRequest request) {

        Shipment shipment = new Shipment();

        shipment.setTrackingNumber(request.getTrackingNumber());
        shipment.setOriginCountry(request.getOriginCountry());
        shipment.setDestinationCountry(request.getDestinationCountry());
        shipment.setStatus(request.getStatus());

        Shipment savedShipment = shipmentRepository.save(shipment);

        return ShipmentResponse.builder()
                .id(savedShipment.getId())
                .trackingNumber(savedShipment.getTrackingNumber())
                .originCountry(savedShipment.getOriginCountry())
                .destinationCountry(savedShipment.getDestinationCountry())
                .status(savedShipment.getStatus())
                .createdAt(savedShipment.getCreatedAt())
                .updatedAt(savedShipment.getUpdatedAt())
                .build();
    }

    public List<ShipmentResponse> getAllShipments() {
        return shipmentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public Optional<ShipmentResponse> getShipmentById(Long id){
        return shipmentRepository.findById(id)
                .map(this::mapToResponse);
    }

    public void deleteShipmentById(Long id){
        shipmentRepository.deleteById(id);
    }

    public ShipmentResponse updateShipment(Long id, ShipmentRequest request) {

        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        shipment.setTrackingNumber(request.getTrackingNumber());
        shipment.setOriginCountry(request.getOriginCountry());
        shipment.setDestinationCountry(request.getDestinationCountry());
        shipment.setStatus(request.getStatus());

        Shipment updatedShipment = shipmentRepository.save(shipment);

        return mapToResponse(updatedShipment);
    }

    private ShipmentResponse mapToResponse(Shipment shipment) {

        return ShipmentResponse.builder()
                .id(shipment.getId())
                .trackingNumber(shipment.getTrackingNumber())
                .originCountry(shipment.getOriginCountry())
                .destinationCountry(shipment.getDestinationCountry())
                .status(shipment.getStatus())
                .createdAt(shipment.getCreatedAt())
                .updatedAt(shipment.getUpdatedAt())
                .build();
    }
}
