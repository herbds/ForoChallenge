package com.foro.last.Dominio.Interfaz;

import com.foro.last.Dominio.TablasRepositorios.IngresoUsuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuariosForo extends JpaRepository<IngresoUsuarios, Long> {
    UserDetails findByLogin(String username);
}
