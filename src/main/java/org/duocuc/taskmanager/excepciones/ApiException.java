package org.duocuc.taskmanager.excepciones;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(HttpStatus estado, String mensaje){
        super(mensaje);
        this.status = estado;
    }

    public ApiException(HttpStatus estado, String mensaje, Throwable causa){
        super(mensaje, causa);
        this.status = estado;
    }

    public HttpStatus getStatus() {
        return status;
    }




}
