create table usuarios(
          id bigint NOT NULL auto_increment,
          login VARCHAR(100) NOT NULL,
          clave VARCHAR(300) NOT NULL,
          PRIMARY KEY (id)
);
INSERT INTO usuarios (login, clave)
VALUES
    ('Lucas', '$2a$10$EiRJ1qKG5AvbP/XOnxFKoO5Ypp3Vk5dVVOlw8w.nC5Xm3RC5WDwxS');

