package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.entity.Car;
import com.ecs.Electric.Car.stations.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/car")
    public String showForm(Car car, Model model) {
        model.addAttribute("view", "/cars/add-car");
        return "base-layout";
    }

    @PostMapping("/car")
    public String saveCar(Car car, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("view", "/cars/add-car");
            return "base-layout";
        }

        carService.saveCar(car);
        redirectAttributes.addFlashAttribute("success", "Car added successfully!");
        return "redirect:/index";
    }

}
