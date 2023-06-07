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
import com.ecs.Electric.Car.stations.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {
    @InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ElectricCarStationRepository electricCarStationRepository;

    @Mock
    private UserBookingMapper userBookingMapper;

    private BookingForm bookingForm;

    @BeforeEach
    public void setup() {
        bookingForm = new BookingForm();
        bookingForm.setUserId(1L);
        bookingForm.setStationId(1L);
        bookingForm.setStartTime("2023-06-06T10:00");
        bookingForm.setEndTime("2023-06-06T12:00");
    }

    @Test
    void testCreateBooking() {
        User user = new User();
        ElectricCarStation station = new ElectricCarStation();
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(electricCarStationRepository.getById(anyLong())).thenReturn(station);
        ArgumentCaptor<Booking> bookingArgumentCaptor = ArgumentCaptor.forClass(Booking.class);

        bookingService.createBooking(bookingForm);

        verify(userRepository).getById(1L);
        verify(electricCarStationRepository, times(1)).getById(1L);
        verify(bookingRepository).save(bookingArgumentCaptor.capture());
    }

    @Test
    void testCheckAvailability() {
        ElectricCarStation station = new ElectricCarStation();
        station.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        station.setCapacity(1);
        when(electricCarStationRepository.getById(anyLong())).thenReturn(station);
        when(bookingRepository.findByStationAndStartTimeAndEndTime(any(), any(), any()))
            .thenReturn(Collections.emptyList());

        boolean availability = bookingService.checkAvailability(bookingForm);

        assertTrue(availability);
        verify(electricCarStationRepository, times(1)).getById(1L);
        verify(bookingRepository, times(1))
            .findByStationAndStartTimeAndEndTime(any(), any(), any());
    }

    @Test
    void testGetBookingsByUser() {
        User user = new User();
        when(userRepository.getById(anyLong())).thenReturn(user);
        when(bookingRepository.findByUser(user)).thenReturn(Collections.emptyList());

        List<UserBookingDto> bookings = bookingService.getBookingsByUser(1L);

        assertNotNull(bookings);
        verify(userRepository).getById(1L);
        verify(bookingRepository).findByUser(user);
    }
}
