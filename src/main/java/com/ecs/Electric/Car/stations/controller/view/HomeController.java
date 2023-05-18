package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.service.ElectricCarStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ElectricCarStationService electricCarStationService;

    @GetMapping("/index")
    public String index(Model model){

        List<ElectricCarStationDto> carStationServiceAll = electricCarStationService.findAll();

        model.addAttribute("view", "home/index");
        model.addAttribute("stations", carStationServiceAll);

        return "base-layout";
    }
}
