package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.UserDto;
import com.ecs.Electric.Car.stations.entity.Role;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.repository.RoleRepository;
import com.ecs.Electric.Car.stations.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.internal.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public void registerUser(UserDto userDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER");
        Set<Role> roleSet = Collections.asSet(userRole);
        user.setRoles(roleSet);
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
