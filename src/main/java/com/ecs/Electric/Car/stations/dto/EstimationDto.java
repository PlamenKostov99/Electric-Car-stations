package com.ecs.Electric.Car.stations.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class EstimationDto {
    private String chargerType;
    private Double cost;
    private Double time;
}
