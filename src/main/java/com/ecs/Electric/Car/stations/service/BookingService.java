package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.BookingForm;
import com.ecs.Electric.Car.stations.dto.UserBookingDto;
import com.ecs.Electric.Car.stations.entity.Booking;
import com.ecs.Electric.Car.stations.entity.ElectricCarStation;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.enums.AvailabilityStatus;
import com.ecs.Electric.Car.stations.mapper.UserBookingMapper;
import com.ecs.Electric.Car.stations.repository.BookingRepository;
import com.ecs.Electric.Car.stations.repository.ElectricCarStationRepository;
import com.ecs.Electric.Car.stations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ElectricCarStationRepository electricCarStationRepository;
    private final UserBookingMapper userBookingMapper;

    public void createBooking(BookingForm bookingForm) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(bookingForm.getStartTime(), formatter);
        LocalDateTime endTime = LocalDateTime.parse(bookingForm.getEndTime(), formatter);
        Booking booking = new Booking();
        User user = userRepository.getById(bookingForm.getUserId());
        ElectricCarStation station = electricCarStationRepository.getById(bookingForm.getStationId());
        booking.setUser(user);
        booking.setStation(station);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        bookingRepository.save(booking);
    }

    public boolean checkAvailability(BookingForm bookingForm) {
        ElectricCarStation station = electricCarStationRepository.getById(bookingForm.getStationId());
        if (!station.getAvailabilityStatus().equals(AvailabilityStatus.AVAILABLE)) {
            return false;
        }
        int maxCapacity = station.getCapacity();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime startTime = LocalDateTime.parse(bookingForm.getStartTime(), formatter);
        LocalDateTime endTime = LocalDateTime.parse(bookingForm.getEndTime(), formatter);
        List<Booking> bookings = bookingRepository.findByStationAndStartTimeAndEndTime(
            station,
            startTime,
            endTime);

        if (bookings.size() > maxCapacity) {
            return false;
        }
        return bookings.isEmpty();
    }

    public List<UserBookingDto> getBookingsByUser(Long userId) {
        User user = userRepository.getById(userId);
        return bookingRepository.findByUser(user)
            .stream()
            .map(userBookingMapper::bookingEntityToDto)
            .collect(Collectors.toList());
    }
}
