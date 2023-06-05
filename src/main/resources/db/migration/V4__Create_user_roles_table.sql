CREATE TABLE user_roles
(
    users_id INTEGER,
    roles_id INTEGER,
    PRIMARY KEY (users_id, roles_id),
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (roles_id) REFERENCES roles (id)
);