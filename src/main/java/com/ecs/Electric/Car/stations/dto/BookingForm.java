package com.ecs.Electric.Car.stations.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class BookingForm {

    private Long userId;
    private Long stationId;
    private String startTime;
    private String endTime;
}
