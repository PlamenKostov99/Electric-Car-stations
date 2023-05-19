package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.dto.ElectricCarStationDto;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.service.ElectricCarStationService;
import com.ecs.Electric.Car.stations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ElectricCarStationService electricCarStationService;
    private final UserService userService;

    @GetMapping("/index")
    public String index(@RequestParam(name = "name", required = false) String name,
                        @RequestParam(name = "chargerType", required = false) String chargerType,
                        Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findUserByUsername(username);  // Hypothetical method, replace with your actual method
        Long userId = user.getId();
        List<ElectricCarStationDto> carStationServiceAll =
            electricCarStationService.findAll(name, chargerType);

        model.addAttribute("view", "home/index");
        model.addAttribute("stations", carStationServiceAll);
        model.addAttribute("userId", userId);

        return "base-layout";
    }

    @GetMapping("/station/{id}")
    public String getStation(@PathVariable Long id, Model model) {
        ElectricCarStationDto station = electricCarStationService.findById(id);
        model.addAttribute("station", station);
        model.addAttribute("view", "home/station");
        return "base-layout";
    }

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("view", "home/home");

        return "base-layout";
    }
}
