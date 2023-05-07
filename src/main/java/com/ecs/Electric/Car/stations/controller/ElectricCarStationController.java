package com.ecs.Electric.Car.stations.controller;

import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.service.ElectricCarStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stations")
public class ElectricCarStationController {
    private final ElectricCarStationService electricCarStationService;

    @GetMapping
    public List<ElectricCarStation> getAllStations() {
        return electricCarStationService.findAll();
    }
}
