package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByMakeAndModel(String make, String model);
}
