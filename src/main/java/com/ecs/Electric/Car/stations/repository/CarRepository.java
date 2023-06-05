package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Optional<Car> findByMakeAndModelAndUser(String make, String model, User user);

    List<Car> findAllByUser(User user);

    Optional<Car> findCarByIdAndUser(Long carId, User user);
}
