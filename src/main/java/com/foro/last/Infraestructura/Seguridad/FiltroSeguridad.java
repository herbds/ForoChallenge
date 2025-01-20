package com.foro.last.Infraestructura.Seguridad;

import com.foro.last.Dominio.Interfaz.UsuariosForo;
import com.foro.last.Dominio.TablasRepositorios.IngresoUsuarios;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltroSeguridad extends OncePerRequestFilter {
    @Autowired
    private TokenServicio tokenServicio;
    @Autowired
    private UsuariosForo usuarioRepositorio;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        var authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "");
            var nombreUsuario = tokenServicio.getUsuarioLogin(token);
            if (nombreUsuario != null) {
                var usuario = usuarioRepositorio.findByLogin(nombreUsuario);
                var autentificacionUsuario = new UsernamePasswordAuthenticationToken
                        (usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autentificacionUsuario);
            }
        }
        filterChain.doFilter(request, response);
    }
}
