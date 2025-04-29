package org.duocuc.taskmanager.modelo;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T> {
    void guardar(T objeto);
    Optional<T> buscarPorId(Long id);
    List<T> listar();
    boolean eliminar(Long id);

}
