package com.foro.last.Infraestructura.Errores;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class TratamientoErroresForo {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError500(MethodArgumentNotValidException e){
        var error = e.getBindingResult().getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(error);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> tratarErrorDuplicacion(DataIntegrityViolationException e) {
        Map<String, String> errorResponse = new LinkedHashMap<>();
        String errorMessage = e.getMessage();

        String variableDuplicada = "";

        if (errorMessage.contains("Duplicate entry")) {
            String[] partes = errorMessage.split("Duplicate entry");
            if (partes.length > 1) {
                variableDuplicada = partes[1].split("for")[0].trim();
            }
        }

        errorResponse.put("error", "Error de duplicación : ");
        errorResponse.put("mensaje", "Valor duplicado : " + variableDuplicada);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> manejarErrorDeDeserializacion(HttpMessageNotReadableException e) {
        Map<String, String> errorResponse = new LinkedHashMap<>();
        String mensajeError;

        if (e.getRootCause() != null) {
            String causa = e.getRootCause().getMessage().toLowerCase();

            if (causa.contains("unexpected character")) {
                mensajeError = "Hubo un carácter inesperado en el formato. ";
            } else if (causa.contains("json parse exception")) {
                mensajeError = "El formato de los datos enviados no es válido. ";
            } else if (causa.contains("missing property")) {
                mensajeError = "Faltan algunos datos requeridos. ";
            } else {
                mensajeError = "Asegúrate de que el formato y los datos sean correctos.";
            }
        } else {
            mensajeError = "Hubo un error al procesar los datos enviados. " +
                    "Asegúrate de que el formato y los datos sean correctos.";
        }

        errorResponse.put("error", "Error de formato de datos");
        errorResponse.put("mensaje", mensajeError);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }


    private record DatosErrorValidacion(String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
