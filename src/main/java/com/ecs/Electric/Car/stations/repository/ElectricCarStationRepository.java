package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricCarStationRepository extends JpaRepository<ElectricCarStation, Long> {
}
