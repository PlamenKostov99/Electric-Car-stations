CREATE TABLE electric_stations
(
    id               SERIAL PRIMARY KEY,
    name             VARCHAR(255)     NOT NULL,
    latitude         DOUBLE PRECISION NOT NULL,
    longitude        DOUBLE PRECISION NOT NULL,
    model            VARCHAR(255),
    capacity         INTEGER,
    location_city    VARCHAR(255),
    location_address VARCHAR(255),
    price            NUMERIC(10, 2)
);