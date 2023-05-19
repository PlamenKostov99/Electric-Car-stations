package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }
}
