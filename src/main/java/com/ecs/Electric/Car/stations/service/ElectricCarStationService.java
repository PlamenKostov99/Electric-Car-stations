package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectricCarStationService {
    private final ElectricCarStationRepository electricCarStationRepository;
    public List<ElectricCarStation> findAll() {
        List<ElectricCarStation> all = electricCarStationRepository.findAll();
        return all;
    }
}
