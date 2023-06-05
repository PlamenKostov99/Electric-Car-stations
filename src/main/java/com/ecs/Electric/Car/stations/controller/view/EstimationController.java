package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.dto.EstimationDto;
import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.service.CarService;
import com.ecs.Electric.Car.stations.service.EstimationService;
import com.ecs.Electric.Car.stations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EstimationController {
    private final EstimationService estimationService;
    private final CarService carService;
    private final UserService userService;

    @GetMapping("/estimate")
    public String estimateForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        List<Car> cars = carService.findCarsForUser(user);
        model.addAttribute("cars", cars);
        model.addAttribute("view", "/cars/estimate-form");
        return "base-layout";
    }

    @PostMapping("/estimate")
    public String estimate(@RequestParam Long carId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        Car car = carService.findCarByIdAndUser(carId, user);
        List<EstimationDto> estimation = estimationService.calculateEstimation(car, user);
        model.addAttribute("estimation", estimation);
        model.addAttribute("view", "/cars/estimate-results");
        return "base-layout";
    }
}
