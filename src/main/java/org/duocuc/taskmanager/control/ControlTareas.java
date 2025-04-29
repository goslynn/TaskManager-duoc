package org.duocuc.taskmanager.control;

import org.duocuc.taskmanager.modelo.Repositorio;
import org.duocuc.taskmanager.modelo.Tarea;
import org.duocuc.taskmanager.modelo.TareaRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControlTareas {

    private final Repositorio<Tarea> repositorio = new TareaRepo();

    @GetMapping("/tarea")
    public ResponseEntity<List<Tarea>> getTareas(){
        List<Tarea> lista = repositorio.listar();
        if (lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tarea/{id}")
    public ResponseEntity<Tarea> getTarea(@PathVariable Long id){
        Tarea t = repositorio.buscarPorId(id).orElse(null);
        if (t != null){
            return ResponseEntity.ok(t);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/tarea")
    public void guardarTarea(@RequestBody Tarea tarea){
        if (tarea == null){
            System.out.println("no resuelve json, llega null");
            return;
        }
        repositorio.guardar(tarea);
    }

    @PutMapping("/tarea/{id}")
    public ResponseEntity<Void> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea){
        Tarea memoria = repositorio.buscarPorId(id).orElse(null);
        if (memoria == null){
            return ResponseEntity.notFound().build();
        }
        memoria.setId(tarea.getId());
        memoria.setDescripcion(tarea.getDescripcion());
        memoria.setNombre(tarea.getNombre());
        memoria.setEstado(tarea.getEstado());
        System.out.println("tarea param: " + tarea);
        System.out.println("tarea memoria: " + repositorio.buscarPorId(memoria.getId()));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){
        boolean b = repositorio.eliminar(id);
        if (b){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
