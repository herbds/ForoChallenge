package com.foro.last.Controlador;

import com.foro.last.Dominio.Autenticaciones.DatosJwtToken;
import com.foro.last.Dominio.Interfaz.UsuariosForo;
import com.foro.last.Dominio.TablasRepositorios.IngresoUsuarios;
import com.foro.last.Dominio.Autenticaciones.DatosAutenticacion;
import com.foro.last.Infraestructura.Seguridad.TokenServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class ControladorAutentificacion {

    private final UsuariosForo usuarioRepositorio;
    private final AuthenticationManager gestionarAutentificacion;
    private final TokenServicio tokenServicio;

    @Autowired
    public ControladorAutentificacion(UsuariosForo usuarioRepositorio,
                                      AuthenticationManager gestionarAutentificacion,
                                      TokenServicio tokenServicio, PasswordEncoder passwordEncoder) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.gestionarAutentificacion = gestionarAutentificacion;
        this.tokenServicio = tokenServicio;
    }

    @PostMapping
    public ResponseEntity autentificacionUsuario(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.login(), datosAutenticacion.clave());
        var usuarioAutenticado = gestionarAutentificacion.authenticate(authToken);
        var jwtToken = tokenServicio.generarToken((IngresoUsuarios) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosJwtToken(jwtToken));
    }

}