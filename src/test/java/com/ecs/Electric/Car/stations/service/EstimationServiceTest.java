package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.EstimationDto;
import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import com.ecs.Electric.Car.stations.repository.CarRepository;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstimationServiceTest {

    @InjectMocks
    private EstimationService estimationService;

    @Mock
    private CarRepository carRepository;

    @Mock
    private ElectricCarStationRepository electricCarStationRepository;

    private User user;
    private Car car;
    private ElectricCarStation electricCarStation;

    @BeforeEach
    public void setup() {
        user = new User();
        car = new Car();
        car.setMake("Tesla");
        car.setModel("Model S");
        car.setBatteryCapacity(100.0);

        electricCarStation = new ElectricCarStation();
        electricCarStation.setChargerType(ChargerType.FAST);
        electricCarStation.setPrice(0.2);
    }

    @Test
    void testCalculateEstimation() {
        when(carRepository.findByMakeAndModelAndUser(car.getMake(), car.getModel(), user)).thenReturn(Optional.of(car));
        when(electricCarStationRepository.findAll()).thenReturn(Collections.singletonList(electricCarStation));

        List<EstimationDto> estimations = estimationService.calculateEstimation(car, user);

        assertNotNull(estimations);
        assertFalse(estimations.isEmpty());
        verify(carRepository).findByMakeAndModelAndUser(car.getMake(), car.getModel(), user);
        verify(electricCarStationRepository).findAll();
    }

    @Test
    void testCalculateEstimationWithInvalidCar() {
        when(carRepository.findByMakeAndModelAndUser(car.getMake(), car.getModel(), user)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> estimationService.calculateEstimation(car, user));

        verify(carRepository).findByMakeAndModelAndUser(car.getMake(), car.getModel(), user);
        verify(electricCarStationRepository, times(0)).findAll();
    }
}
