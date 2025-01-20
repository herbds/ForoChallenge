package com.foro.last.Dominio.Temas;

import com.foro.last.Dominio.TablasRepositorios.RegistroTema;

import java.net.URI;
import java.time.format.DateTimeFormatter;

public record ExtraccionConUrl(Long topico, String titulo, String curso, Estado estado, String mensaje, String profesor, String fechaRegistro, URI url) {
    public ExtraccionConUrl (DatosRegistro extraccion, URI url){
        this(
                extraccion.topico(),
                extraccion.titulo(),
                extraccion.curso(),
                extraccion.estado(),
                extraccion.mensaje(),
                extraccion.profesor(),
                extraccion.fechaRegistro(),
                url
        );
    }
}
