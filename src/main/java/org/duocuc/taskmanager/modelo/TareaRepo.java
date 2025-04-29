package org.duocuc.taskmanager.modelo;

import org.duocuc.taskmanager.util.TareaInvalida;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TareaRepo implements Repositorio<Tarea>{
    private List<Tarea> tareas =  new ArrayList<>();

    @Override
    public void guardar(Tarea t) {
        try {
            validar(t);
        } catch (TareaInvalida e) {
            System.out.println("Error al guardar la tarea: " + e.getMessage());
            return;
        }
        tareas.add(t);
        System.out.println("Guardando tarea: " + t.getNombre());
    }

    @Override
    public Optional<Tarea> buscarPorId(Long id) {
        return tareas.stream()
                     .filter(tarea -> tarea.getId().equals(id))
                     .findFirst();
    }

    @Override
    public List<Tarea> listar() {
        return tareas;
    }

    @Override
    public boolean eliminar(Long id) {
        Optional<Tarea> tarea = buscarPorId(id);
        if (tarea.isPresent()) {
            tareas.remove(tarea.get());
            System.out.println("Tarea eliminada: " + tarea.get().getNombre());
            return true;
        } else {
            System.out.println("Tarea no encontrada");
            return false;
        }
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
