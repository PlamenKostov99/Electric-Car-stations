/*
package com.ecs.Electric.Car.stations.controller;

import com.ecs.Electric.Car.stations.dto.AuthenticationRequestDto;
import com.ecs.Electric.Car.stations.dto.UserDto;
import com.ecs.Electric.Car.stations.service.AuthenticationService;
import com.ecs.Electric.Car.stations.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authentication")
public class UserController {


    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequestDto authenticationRequestDto) {
        String token = authenticateUser.authenticateUser(authenticationRequestDto);
        if (!Objects.isNull(token)) {
            return ResponseEntity.ok().body(token);
        }
        return ResponseEntity.status(400).body("Error occured");
    }

}
*/
