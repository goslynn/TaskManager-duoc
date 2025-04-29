package org.duocuc.taskmanager.servicio;

import org.duocuc.taskmanager.excepciones.ApiException;
import org.duocuc.taskmanager.modelo.Repositorio;
import org.duocuc.taskmanager.modelo.Tarea;
import org.duocuc.taskmanager.modelo.TareaRepo;
import org.springframework.http.HttpStatus;

import java.util.List;

public class ServicioTareas {

    private final Repositorio<Tarea> repositorio = new TareaRepo();
//    private static final Logger log = LoggerFactory.getLogger(ServicioTareas.class);

    public List<Tarea> listarTareas() {
        return repositorio.listar();
    }

    public Tarea buscarPorId(Long id) throws ApiException {
        try{
            return repositorio.buscarPorId(id);
        }catch (Exception e){
            throw new ApiException(HttpStatus.NOT_FOUND, e.getMessage() , e);
        }
    }

    public void guardarTarea(Tarea tarea) throws ApiException {
        try {
            repositorio.guardar(tarea);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar tarea", e);
        }
    }

    public void actualizarTarea(Long id, Tarea tareaNueva) throws ApiException{
        try {
//            if (tareaNueva.getId() != null) {
//                throw new IllegalArgumentException("El id de la tarea no debe ser modificado");
//            }
            repositorio.actualizar(id, tareaNueva);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar tarea", e);
        }
    }

    public void eliminarTarea(Long id) throws ApiException {
        try {
            repositorio.eliminar(id);
        } catch (Exception e) {
            throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al eliminar tarea", e);
        }

    }
}
