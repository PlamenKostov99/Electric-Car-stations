package com.ecs.Electric.Car.stations.controller.view;

import com.ecs.Electric.Car.stations.dto.UserDto;
import com.ecs.Electric.Car.stations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("view", "user/login");

        return "base-layout";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(UserDto userDto) {

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            return "redirect:/register";
        }

        userService.registerUser(userDto);

        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }
}
