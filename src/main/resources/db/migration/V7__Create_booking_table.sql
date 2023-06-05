CREATE TABLE booking
(
    id         SERIAL PRIMARY KEY,
    user_id    BIGINT    NOT NULL,
    station_id BIGINT    NOT NULL,
    start_time TIMESTAMP NOT NULL,
    end_time   TIMESTAMP NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (station_id) REFERENCES electric_stations (id)
);