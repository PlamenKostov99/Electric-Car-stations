package com.ecs.Electric.Car.stations.mapper;

import com.ecs.Electric.Car.stations.dto.UserDto;
import com.ecs.Electric.Car.stations.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
   /* @Mapping(target = "username", source = "username")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    User userDtoToUser(UserDto userDto);*/
}
