package org.duocuc.taskmanager.util;

public class TareaInvalida extends Exception {
    public TareaInvalida(String message) {
        super(message);
    }
    public TareaInvalida(String message, Throwable cause) {
        super(message, cause);
    }
}
