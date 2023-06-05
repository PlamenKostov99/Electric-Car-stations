package com.ecs.Electric.Car.stations.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "make", nullable = false)
    private String make;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "energy_required")
    private Double energyRequired;
    @Column(name = "avg_charging_speed")
    private Double averageChargingSpeed;
    @Column(name = "battery_capacity", nullable = false)
    private Double batteryCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
