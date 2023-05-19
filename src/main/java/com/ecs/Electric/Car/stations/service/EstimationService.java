package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.EstimationDto;
import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import com.ecs.Electric.Car.stations.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstimationService {

    private final CarRepository carRepository;

    public List<EstimationDto> calculateEstimation(String make, String model, double electricityCost) {
        Car car = carRepository.findByMakeAndModel(make, model)
            .orElseThrow(() -> new IllegalArgumentException("Car not found for this make and model"));

        List<EstimationDto> estimations = new ArrayList<>();
        for (ChargerType charger : ChargerType.values()) {
            double time = car.getBatteryCapacity() / charger.getChargingSpeed();
            double cost = car.getBatteryCapacity() * electricityCost;
            estimations.add(new EstimationDto(charger.name(), cost, time));
        }

        return estimations;
    }
}
