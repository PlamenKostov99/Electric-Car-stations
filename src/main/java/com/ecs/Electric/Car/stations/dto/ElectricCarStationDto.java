package com.ecs.Electric.Car.stations.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ElectricCarStationDto {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String model;
    private Integer capacity;
    private String locationCity;
    private String locationAddress;
    private Double price;
    private String chargerType;
    private String availabilityStatus;
}
