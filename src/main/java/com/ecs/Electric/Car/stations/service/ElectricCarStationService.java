package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import com.ecs.Electric.Car.stations.mapper.ElectricCarStationMapper;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import com.ecs.Electric.Car.stations.search.ElectricCarStationSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ElectricCarStationService {
    private final ElectricCarStationRepository electricCarStationRepository;
    private final ElectricCarStationMapper electricCarStationMapper;

    public List<ElectricCarStationDto> findAll(String name, String chargerType) {
        ChargerType chargerTypeEnum = null;
        if (chargerType != null && !chargerType.isEmpty()) {
            chargerTypeEnum = ChargerType.valueOf(chargerType);
        }
        Specification<ElectricCarStation> spec = new ElectricCarStationSpecification(name, chargerTypeEnum);
        return electricCarStationRepository.findAll(spec)
            .stream().map(electricCarStationMapper::electricCarStationEntityToDto)
            .collect(Collectors.toList());
    }


    public ElectricCarStationDto findById(Long id) {
        ElectricCarStation electricCarStation = electricCarStationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Electric Car Station not found !"));
        return electricCarStationMapper.electricCarStationEntityToDto(electricCarStation);
    }
}
