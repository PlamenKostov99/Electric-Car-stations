package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.Booking;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByStationAndStartTimeAndEndTime(ElectricCarStation station,
                                                      LocalDateTime startTime,
                                                      LocalDateTime endTime);

    List<Booking> findByUser(User user);
}
