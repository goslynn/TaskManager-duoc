package org.duocuc.taskmanager.modelo;

import java.util.List;
import java.util.Optional;

public interface Repositorio<T> {
    void guardar(T objeto) throws Exception;
    void actualizar(Long id, T objeto) throws Exception;
    T buscarPorId(Long id) throws Exception;
    List<T> listar();
    void eliminar(Long id) throws Exception;

}
