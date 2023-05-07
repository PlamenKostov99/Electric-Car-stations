package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.UserDto;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.mapper.UserMapper;
import com.ecs.Electric.Car.stations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final UserMapper userMapper;

    public void registerUser(UserDto userDto) {
        userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
    }

    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username/password");
        }

        return user;
    }
}
