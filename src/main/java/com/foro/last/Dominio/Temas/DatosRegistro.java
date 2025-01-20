package com.foro.last.Dominio.Temas;

import java.time.LocalDateTime;

public record DatosRegistro(Long topico,
                            String titulo,
                            String curso,
                            Estado estado,
                            String mensaje,
                            String profesor,
                            String fechaRegistro) {
}
