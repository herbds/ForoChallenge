package com.foro.last.Dominio.Temas;

import jakarta.validation.constraints.NotBlank;

public record Foro(@NotBlank
                   String nombreCurso,
                   @NotBlank
                   String titulo,

                   String mensaje,

                   @NotBlank
                   String profesor) {
}
