package com.foro.last.Infraestructura.Seguridad;


import com.foro.last.Dominio.Interfaz.UsuariosForo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutentificacionServicio implements UserDetailsService {
    @Autowired
    private UsuariosForo usuarioRepositorio;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepositorio.findByLogin(username);
    }
}
