package com.ecs.Electric.Car.stations.dto;

import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class UserBookingDto {
    private ElectricCarStation station;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
