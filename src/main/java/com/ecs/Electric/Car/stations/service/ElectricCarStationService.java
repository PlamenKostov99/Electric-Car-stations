package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.mapper.ElectricCarStationMapper;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectricCarStationService {
    private final ElectricCarStationRepository electricCarStationRepository;
    private final ElectricCarStationMapper electricCarStationMapper;

    public List<ElectricCarStationDto> findAll() {
        return electricCarStationRepository.findAll()
            .stream().map(electricCarStationMapper::electricCarStationEntityToDto)
            .collect(Collectors.toList());
    }

    public ElectricCarStationDto updateAvailability(Long id, int available) {
        ElectricCarStation electricCarStation = electricCarStationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Station not found with id " + id));
        electricCarStation.setAvailable(available);
        ElectricCarStation carStation = electricCarStationRepository.save(electricCarStation);
        return electricCarStationMapper.electricCarStationEntityToDto(carStation);
    }
}
