package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import com.ecs.Electric.Car.stations.mapper.ElectricCarStationMapper;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import com.ecs.Electric.Car.stations.search.ElectricCarStationSpecification;
import com.ecs.Electric.Car.stations.service.ElectricCarStationService;
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
class ElectricCarStationServiceTest {

    @InjectMocks
    private ElectricCarStationService electricCarStationService;

    @Mock
    private ElectricCarStationRepository electricCarStationRepository;

    @Mock
    private ElectricCarStationMapper electricCarStationMapper;

    private ElectricCarStation electricCarStation;
    private ElectricCarStationDto electricCarStationDto;

    @BeforeEach
    public void setup() {

        electricCarStation = new ElectricCarStation();
        electricCarStation.setId(1L);
        electricCarStation.setName("Test Station");
        electricCarStation.setChargerType(ChargerType.FAST);

        electricCarStationDto = new ElectricCarStationDto();
        electricCarStationDto.setId(1L);
        electricCarStationDto.setName("Test Station");
        electricCarStationDto.setChargerType(ChargerType.FAST.name());
    }

    @Test
    void testFindAll() {
        when(electricCarStationRepository.findAll(any(ElectricCarStationSpecification.class))).thenReturn(Collections.singletonList(electricCarStation));
        when(electricCarStationMapper.electricCarStationEntityToDto(electricCarStation)).thenReturn(electricCarStationDto);

        List<ElectricCarStationDto> stations = electricCarStationService.findAll("Test Station", ChargerType.FAST.name());

        assertNotNull(stations);
        assertEquals(1, stations.size());
        verify(electricCarStationRepository, times(1)).findAll(any(ElectricCarStationSpecification.class));
        verify(electricCarStationMapper, times(1)).electricCarStationEntityToDto(electricCarStation);
    }


    @Test
    void testFindById() {
        when(electricCarStationRepository.findById(1L)).thenReturn(Optional.of(electricCarStation));
        when(electricCarStationMapper.electricCarStationEntityToDto(electricCarStation)).thenReturn(electricCarStationDto);

        ElectricCarStationDto foundStation = electricCarStationService.findById(1L);

        assertNotNull(foundStation);
        verify(electricCarStationRepository, times(1)).findById(1L);
        verify(electricCarStationMapper, times(1)).electricCarStationEntityToDto(electricCarStation);
    }

    @Test
    void testFindByIdWithInvalidId() {
        when(electricCarStationRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> electricCarStationService.findById(1L));

        verify(electricCarStationRepository, times(1)).findById(1L);
        verify(electricCarStationMapper, times(0)).electricCarStationEntityToDto(any(ElectricCarStation.class));
    }
}
