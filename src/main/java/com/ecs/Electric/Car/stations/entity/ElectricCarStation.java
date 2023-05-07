package com.ecs.Electric.Car.stations.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "electric_stations")
public class ElectricCarStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "model")
    private String model;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "location_city")
    private String locationCity;

    @Column(name = "location_address")
    private String locationAddress;

    @Column(name = "price")
    private Double price;
}
