package com.ecs.Electric.Car.stations.controller;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.service.ElectricCarStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stations")
public class ElectricCarStationController {
    private final ElectricCarStationService electricCarStationService;

  /*  @GetMapping
    public List<ElectricCarStationDto> getAllStations(@RequestParam(name = "name") String name,
                                                      @RequestParam(name = "chargerType") String chargerType,
                                                      @RequestParam(name = "minAvailableSpots") Integer minAvailableSpots) {
        return electricCarStationService.findAll(name, chargerType, minAvailableSpots);
    }

    @PutMapping("/api/stations/{id}/availability")
    public ElectricCarStationDto updateAvailability(@RequestParam Long id, @RequestParam int available) {
        return electricCarStationService.updateAvailability(id, available);
    }*/
}
