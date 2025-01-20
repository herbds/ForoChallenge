package com.foro.last.Dominio.TablasRepositorios;

import com.foro.last.Dominio.Temas.DatosActualizados;
import com.foro.last.Dominio.Temas.Estado;
import com.foro.last.Dominio.Temas.Foro;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Entity (name = "Registros")
@Table (name = "registros")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode (of = "topico")
public class RegistroTema {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long topico;

    private String mensaje;

    @NotBlank
    private String curso;

    @NotBlank
    private String titulo;

    @Enumerated (EnumType.STRING)
    private Estado estado;

    @NotBlank
    private String profesor;

    private Boolean activo = true;

    private LocalDateTime fecha;



    public RegistroTema(Foro foro, Estado estado) {
        this.activo = true;
        this.titulo = foro.titulo();
        this.curso = foro.nombreCurso();
        this.mensaje = foro.mensaje();
        this.estado = estado;
        this.profesor = foro.profesor();
        this.fecha = LocalDateTime.now();
    }


    public void actualizarDatos(DatosActualizados actualizados) {
        if (actualizados.estado() != null) {
            this.estado = actualizados.estado();
        }
        if (actualizados.mensaje() != null) {
            this.mensaje = actualizados.mensaje();
        }
        this.fecha = LocalDateTime.now();
    }

    public void desactivarTopico() {

        this.activo = false;
        this.estado = Estado.Eliminado;
        this.fecha = LocalDateTime.now();
    }

}

