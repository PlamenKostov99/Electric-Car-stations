package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
