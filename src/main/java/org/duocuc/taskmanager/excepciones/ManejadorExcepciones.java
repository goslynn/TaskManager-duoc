package org.duocuc.taskmanager.excepciones;
import org.duocuc.taskmanager.servicio.ServicioTareas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;


@ControllerAdvice
public class ManejadorExcepciones {

    private static final Logger log = LoggerFactory.getLogger(ServicioTareas.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> manejarApiException(ApiException ex) {
        traza(ex);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", ex.getMessage());

        List<String> causas = extraerCausas(ex);
        if (!causas.isEmpty()) {
            body.put("cause", causas);
        }

        body.put("status", ex.getStatus().value());
        body.put("error", ex.getStatus().getReasonPhrase());
        body.put("timestamp", LocalDateTime.now());

        return new ResponseEntity<>(body, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> manejarGenerico(Exception ex) {
        traza(ex);
        Map<String, Object> body = new HashMap<>();
        body.put("mensaje", "Error interno inesperado :/");
        body.put("detalle", ex.getMessage());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private List<String> extraerCausas(Throwable ex) {
        List<String> lista = new ArrayList<>();
        Throwable causa = ex.getCause();

        while (causa != null) {
            String mensaje = causa.getMessage();
            if (mensaje != null && !mensaje.isEmpty() && !mensaje.equals(ex.getMessage())) {
                lista.add(mensaje);
            }
            causa = causa.getCause();
        }

        // Usamos stream para quitar duplicados si existieran
        return lista.stream()
                    .distinct()
                    .toList();
    }

    private void traza(Throwable ex) {
        log.error("OOPS! sos imbecil: ", ex);
    }

}
