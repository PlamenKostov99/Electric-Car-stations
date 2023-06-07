package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.repository.CarRepository;
import com.ecs.Electric.Car.stations.service.CarService;
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
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    private User user;
    private Car car;

    @BeforeEach
    public void setup() {
        user = new User();
        car = new Car();
        car.setMake("Tesla");
        car.setModel("Model S");
    }

    @Test
    void testSaveCar() {
        when(carRepository.findByMakeAndModelAndUser(anyString(), anyString(), any())).thenReturn(Optional.empty());
        when(carRepository.save(any())).thenReturn(car);

        Car savedCar = carService.saveCar(car, user);

        assertNotNull(savedCar);
        verify(carRepository).findByMakeAndModelAndUser(car.getMake(), car.getModel(), user);
        verify(carRepository).save(car);
    }

    @Test
    void testSaveCarWithExistingCar() {
        when(carRepository.findByMakeAndModelAndUser(anyString(), anyString(), any())).thenReturn(Optional.of(car));

        assertThrows(IllegalArgumentException.class, () -> carService.saveCar(car, user));

        verify(carRepository).findByMakeAndModelAndUser(car.getMake(), car.getModel(), user);
        verify(carRepository, times(0)).save(car);
    }

    @Test
    void testFindCarsForUser() {
        when(carRepository.findAllByUser(any())).thenReturn(Collections.singletonList(car));

        List<Car> cars = carService.findCarsForUser(user);

        assertNotNull(cars);
        assertEquals(1, cars.size());
        verify(carRepository).findAllByUser(user);
    }

    @Test
    void testFindCarByIdAndUser() {
        when(carRepository.findCarByIdAndUser(anyLong(), any())).thenReturn(Optional.of(car));

        Car foundCar = carService.findCarByIdAndUser(1L, user);

        assertNotNull(foundCar);
        verify(carRepository).findCarByIdAndUser(1L, user);
    }

    @Test
    void testFindCarByIdAndUserWithInvalidId() {
        when(carRepository.findCarByIdAndUser(anyLong(), any())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> carService.findCarByIdAndUser(1L, user));

        verify(carRepository).findCarByIdAndUser(1L, user);
    }
}
