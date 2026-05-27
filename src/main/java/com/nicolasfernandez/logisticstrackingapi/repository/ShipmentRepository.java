package com.nicolasfernandez.logisticstrackingapi.repository;

import com.nicolasfernandez.logisticstrackingapi.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}