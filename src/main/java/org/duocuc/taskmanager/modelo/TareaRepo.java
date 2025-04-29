package org.duocuc.taskmanager.modelo;


import org.duocuc.taskmanager.excepciones.ErrorDatos;
import org.duocuc.taskmanager.excepciones.TareaInvalida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


public class TareaRepo implements Repositorio<Tarea>{
    private List<Tarea> tareas =  new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(TareaRepo.class);

    @Override
    public void guardar(Tarea t) throws ErrorDatos {
        try {
            validar(t);
        } catch (TareaInvalida e) {
            throw new ErrorDatos("Error al guardar la tarea", e);
        }
        tareas.add(t);
        log.info("Guardando tarea: {}", t.getNombre());
    }

    @Override
    public Tarea buscarPorId(Long id) throws ErrorDatos{
        Tarea t = tareas.stream()
                     .filter(tarea -> tarea.getId().equals(id))
                     .findFirst()
                .orElse(null);
        if (t == null){
            throw new ErrorDatos("No se encontró la tarea con id: " + id);
        }
        log.info("Tarea encontrada: {}", t.getNombre());
        return t;
    }

    @Override
    public List<Tarea> listar() {
        if (tareas == null || tareas.isEmpty()) {
            log.warn("No hay tareas registradas");
            return new ArrayList<>();
        }
        return tareas;
    }

    @Override
    public void eliminar(Long id) throws ErrorDatos {
        Tarea t = buscarPorId(id);
        tareas.remove(t);
        log.info("Tarea eliminada: { {}, {} }", t.getId(), t.getNombre());
    }

    @Override
    public void actualizar(Long id, Tarea tarea) throws ErrorDatos {
        Tarea t = buscarPorId(id);
        t.copyOf(tarea);
    }

    private void validar(Tarea t) throws TareaInvalida {

        if (t.getId() == null || t.getId() < 0) {
            throw new TareaInvalida("El id de la tarea no puede ser nulo o negativo");
        }
        if (tareas.contains(t)){
            throw new TareaInvalida("La tarea ya existe");
        }
        if (t.getNombre() == null || t.getNombre().isEmpty()){
            throw new TareaInvalida("El nombre de la tarea no puede ser nulo o vacío");
        }
        if (t.getDescripcion() == null || t.getDescripcion().isEmpty()){
            throw new TareaInvalida("La descripción de la tarea no puede ser nula o vacía");
        }
        if (t.getEstado() == null){
            throw new TareaInvalida("El estado de la tarea no puede ser nulo");
        }

    }



}
