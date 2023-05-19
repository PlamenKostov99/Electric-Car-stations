package com.ecs.Electric.Car.stations.mapper;

import com.ecs.Electric.Car.stations.dto.UserBookingDto;
import com.ecs.Electric.Car.stations.entity.Booking;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserBookingMapper {

    UserBookingDto bookingEntityToDto(Booking booking);
}
