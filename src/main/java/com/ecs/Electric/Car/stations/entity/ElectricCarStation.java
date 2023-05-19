package com.ecs.Electric.Car.stations.entity;

import com.ecs.Electric.Car.stations.enums.AvailabilityStatus;
import com.ecs.Electric.Car.stations.enums.ChargerType;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "electric_stations")
public class ElectricCarStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "charger_type")
    private ChargerType chargerType;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability_status")
    private AvailabilityStatus availabilityStatus;
}
