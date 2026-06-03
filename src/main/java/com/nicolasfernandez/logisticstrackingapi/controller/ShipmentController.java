package com.nicolasfernandez.logisticstrackingapi.controller;

import com.nicolasfernandez.logisticstrackingapi.dto.ShipmentRequest;
import com.nicolasfernandez.logisticstrackingapi.dto.ShipmentResponse;
import com.nicolasfernandez.logisticstrackingapi.service.ShipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResponse> createShipment(
            @Valid @RequestBody ShipmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(shipmentService.createShipment(request));
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResponse>> getAllShipments(){
        return ResponseEntity.ok(shipmentService.getAllShipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentResponse> getShipmentById(
            @PathVariable Long id) {

        return shipmentService.getShipmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentResponse> updateShipment(@PathVariable Long id, @Valid @RequestBody ShipmentRequest request) {
        return ResponseEntity.ok(
                shipmentService.updateShipment(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id){
        shipmentService.deleteShipmentById(id);
        return ResponseEntity.noContent().build();
    }
}