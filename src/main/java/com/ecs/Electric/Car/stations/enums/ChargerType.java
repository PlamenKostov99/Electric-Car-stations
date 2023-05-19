package com.ecs.Electric.Car.stations.enums;

public enum ChargerType {
    FAST("FAST", 22.0),
    SUPER_FAST("SUPER_FAST", 150.0),
    REGULAR("REGULAR", 7.2);

    private String name;
    private double chargingSpeed;

    ChargerType(String name, double chargingSpeed) {
        this.name = name;
        this.chargingSpeed = chargingSpeed;
    }

    public String getName() {
        return name;
    }

    public double getChargingSpeed() {
        return chargingSpeed;
    }
}
