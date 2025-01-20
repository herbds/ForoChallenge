package com.foro.last.Dominio.Autenticaciones;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DatosJwtToken(@JsonProperty ("Llave de ingreso") String llaveDeIngreso) {
}
