package com.foro.last.Dominio.Temas;

import jakarta.validation.constraints.NotNull;

public record DatosActualizados(@NotNull Long topico, Estado estado, String mensaje) {
}
