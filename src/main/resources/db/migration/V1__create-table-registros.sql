create table registros(
                          topico bigint NOT NULL auto_increment,
                          curso VARCHAR(100) NOT NULL,
                          titulo VARCHAR(100) NOT NULL,
                          estado VARCHAR(100) NOT NULL,
                          mensaje VARCHAR(100),
                          PRIMARY KEY (topico)
);
