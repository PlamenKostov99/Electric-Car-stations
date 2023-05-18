package com.ecs.Electric.Car.stations.mapper;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElectricCarStationMapper {

    ElectricCarStationDto electricCarStationEntityToDto(ElectricCarStation electricCarStation);
}
