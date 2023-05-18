package com.ecs.Electric.Car.stations.repository;

import com.ecs.Electric.Car.stations.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
