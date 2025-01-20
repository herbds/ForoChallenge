package com.foro.last.Dominio.Temas;

import com.foro.last.Dominio.TablasRepositorios.RegistroTema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record DatosExtraidos(Long topico, String titulo, String curso, Estado estado, String mensaje, String profesor, String fechaRegistro) {
    public DatosExtraidos (RegistroTema extraccion){
        this(
            extraccion.getTopico(),
            extraccion.getTitulo(),
            extraccion.getCurso(),
            extraccion.getEstado(),
            extraccion.getMensaje(),
            extraccion.getProfesor(),
            extraccion.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        );
    }
}
