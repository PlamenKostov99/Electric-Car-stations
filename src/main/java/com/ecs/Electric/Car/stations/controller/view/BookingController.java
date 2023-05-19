package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.dto.BookingForm;
import com.ecs.Electric.Car.stations.dto.UserBookingDto;
import com.ecs.Electric.Car.stations.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;


    @PostMapping("/create")
    public String createBooking(@ModelAttribute("bookingForm") BookingForm bookingForm,
                                RedirectAttributes redirectAttributes) {
        boolean isAvailable = bookingService.checkAvailability(bookingForm);
        if (isAvailable) {
            bookingService.createBooking(bookingForm);
            redirectAttributes.addFlashAttribute("successMessage", "Booking successful!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "The station is not available for the specified time slot");
        }
        return "redirect:/index";
    }

    @GetMapping("/user/{userId}")
    public String getUserBookings(@PathVariable("userId") Long userId, Model model) {
        List<UserBookingDto> bookings = bookingService.getBookingsByUser(userId);
        model.addAttribute("bookings", bookings);
        model.addAttribute("userId", userId);
        model.addAttribute("view", "bookings/user-bookings");
        return "base-layout";
    }
}
