package com.ecs.Electric.Car.stations.service;

import com.ecs.Electric.Car.stations.dto.UserDto;
import com.ecs.Electric.Car.stations.entity.Role;
import com.ecs.Electric.Car.stations.entity.User;
import com.ecs.Electric.Car.stations.repository.RoleRepository;
import com.ecs.Electric.Car.stations.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    private User user;
    private Role role;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        role = new Role();
        role.setName("ROLE_USER");
        user.setRoles(Collections.singleton(role));
    }

    @Test
    void testLoadUserByUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        assertNotNull(userDetails);
        assertEquals(user.getUsername(), userDetails.getUsername());
        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    void testLoadUserByUsernameWithInvalidUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(user.getUsername()));

        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    void testRegisterUser() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");
        userDto.setEmail("test@test.com");

        when(roleRepository.findByName(anyString())).thenReturn(role);

        userService.registerUser(userDto);

        verify(roleRepository).findByName("ROLE_USER");
        verify(userRepository).save(any(User.class));
    }

    @Test
    void testFindUserByUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        User foundUser = userService.findUserByUsername(user.getUsername());

        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        verify(userRepository).findByUsername(user.getUsername());
    }

    @Test
    void testFindUserByUsernameWithInvalidUsername() {
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.findUserByUsername(user.getUsername()));

        verify(userRepository).findByUsername(user.getUsername());
    }
}
