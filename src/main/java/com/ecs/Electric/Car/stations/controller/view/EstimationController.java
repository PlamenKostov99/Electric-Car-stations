package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.dto.EstimationDto;
import com.ecs.Electric.Car.stations.service.EstimationService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/estimate")
    public String estimateForm() {
        return "/cars/estimate-form";
    }

    @PostMapping("/estimate")
    public String estimate(@RequestParam String make,
                           @RequestParam String carModel,
                           @RequestParam double electricityCost,
                           Model model) {
        List<EstimationDto> estimation = estimationService.calculateEstimation(make, carModel, electricityCost);
        model.addAttribute("estimation", estimation);
        model.addAttribute("view", "/cars/estimate-results");
        return "base-layout";
    }
}
