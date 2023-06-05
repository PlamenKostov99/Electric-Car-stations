package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car saveCar(Car car, User user) throws IllegalArgumentException {
        Optional<Car> existingCar = carRepository.findByMakeAndModelAndUser(car.getMake(), car.getModel(), user);
        if (existingCar.isPresent()) {
            throw new IllegalArgumentException("Car with the same make and model already exists for this user");
        }
        car.setUser(user);
        user.getCars().add(car);
        return carRepository.save(car);
    }

    public List<Car> findCarsForUser(User user) {
        return carRepository.findAllByUser(user);
    }

    public Car findCarByIdAndUser(Long carId, User user) {
        return carRepository.findCarByIdAndUser(carId, user)
            .orElseThrow(() -> new IllegalArgumentException("Car not found for this user"));
    }
}
