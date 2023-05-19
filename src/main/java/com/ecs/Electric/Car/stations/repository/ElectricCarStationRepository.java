package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectricCarStationRepository extends JpaRepository<ElectricCarStation, Long>, JpaSpecificationExecutor<ElectricCarStation> {
}
