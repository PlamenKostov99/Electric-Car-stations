CREATE TABLE car
(
    id                 SERIAL PRIMARY KEY,
    make               VARCHAR(255)     NOT NULL,
    model              VARCHAR(255)     NOT NULL,
    energy_required    DOUBLE PRECISION,
    avg_charging_speed DOUBLE PRECISION,
    battery_capacity   DOUBLE PRECISION NOT NULL,
    user_id            INT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
