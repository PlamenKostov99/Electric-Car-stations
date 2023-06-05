package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.service.CarService;
import com.ecs.Electric.Car.stations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final UserService userService;

    @GetMapping("/car")
    public String showForm(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);
        List<Car> cars = carService.findCarsForUser(user);
        model.addAttribute("car", new Car());
        model.addAttribute("cars", cars);
        model.addAttribute("view", "/cars/add-car");
        return "base-layout";
    }

    @PostMapping("/car")
    public String saveCar(Car car,
                          BindingResult result,
                          RedirectAttributes redirectAttributes,
                          Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);

        if (result.hasErrors()) {
            model.addAttribute("view", "/cars/add-car");
            return "base-layout";
        }

        carService.saveCar(car, user);
        redirectAttributes.addFlashAttribute("success", "Car added successfully!");
        return "redirect:/index";
    }

}
