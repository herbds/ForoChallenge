package com.foro.last.Infraestructura.Seguridad;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.foro.last.Dominio.Interfaz.UsuariosForo;
import com.foro.last.Dominio.TablasRepositorios.IngresoUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class TokenServicio {

    private String apiSecret;

    @Autowired
    private UsuariosForo usuarioRepositorio;

    public String generarToken(IngresoUsuarios usuario) {
        try {

            Algorithm algorithm = Algorithm.HMAC256("123456");
            System.out.println(algorithm);
            return JWT.create()
                    .withIssuer("last foro token")
                    .withSubject(usuario.getLogin())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarExpiracion())
                    .sign(algorithm);
        } catch (JWTVerificationException exception){
            throw new RuntimeException();
        }
    }

    private Instant generarExpiracion(){
        return LocalDateTime.now().plusMinutes(40).toInstant(java.time.ZoneOffset.of("-05:00"));
    }


    public String getUsuarioLogin(String token) {
        if (token == null) {
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("123456");
            verifier = JWT.require(algorithm)
                    .withIssuer("last foro token")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {
            System.out.println(exception.toString());
        }
        if (verifier.getSubject() == null) {
            throw new RuntimeException("Verifier invalido");
        }
        return verifier.getSubject();
    }


}
