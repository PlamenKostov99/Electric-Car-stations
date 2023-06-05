package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.EstimationDto;
import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import com.ecs.Electric.Car.stations.repository.CarRepository;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstimationService {

    private final CarRepository carRepository;
    private final ElectricCarStationRepository electricCarStationRepository;

    public List<EstimationDto> calculateEstimation(Car car, User user) {
        Car carE = carRepository.findByMakeAndModelAndUser(car.getMake(), car.getModel(), user)
            .orElseThrow(() -> new IllegalArgumentException("Car not found for this make and model"));
        Map<ChargerType, Double> chargerTypeGroupedByCost = getChargerTypeGroupedByCost();

        List<EstimationDto> estimations = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("#.##");
        for (ChargerType charger : ChargerType.values()) {
            double timeInHours = carE.getBatteryCapacity() / charger.getChargingSpeed();
            double cost = carE.getBatteryCapacity() * chargerTypeGroupedByCost.get(charger);
            String roundedCost = df.format(cost) + " BGN";

            long hours = (long) timeInHours;
            int minutes = (int) ((timeInHours - hours) * 60);
            String time = hours + "h " + minutes + "m";

            estimations.add(new EstimationDto(charger.name(), roundedCost, time));
        }

        return estimations;
    }

    private Map<ChargerType, Double> getChargerTypeGroupedByCost() {
        List<ElectricCarStation> electricCarStations = electricCarStationRepository.findAll();

        Map<ChargerType, List<ElectricCarStation>> groupedStations = electricCarStations.stream()
            .collect(Collectors.groupingBy(ElectricCarStation::getChargerType));

        Map<ChargerType, Double> averageCostByChargerType = new HashMap<>();
        for (ChargerType chargerType : ChargerType.values()) {
            List<ElectricCarStation> stationsOfThisType = groupedStations.get(chargerType);
            if (stationsOfThisType != null && !stationsOfThisType.isEmpty()) {
                double averageCost = stationsOfThisType.stream()
                    .mapToDouble(ElectricCarStation::getPrice)
                    .average()
                    .getAsDouble();
                averageCostByChargerType.put(chargerType, averageCost);
            } else {
                averageCostByChargerType.put(chargerType, 0.0);
            }
        }
        return averageCostByChargerType;
    }
}
