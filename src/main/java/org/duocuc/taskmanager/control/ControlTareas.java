package org.duocuc.taskmanager.control;

import org.duocuc.taskmanager.modelo.Repositorio;
import org.duocuc.taskmanager.modelo.Tarea;
import org.duocuc.taskmanager.modelo.TareaRepo;
import org.duocuc.taskmanager.servicio.ServicioTareas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControlTareas {

    private final ServicioTareas servicio = new ServicioTareas();

    @GetMapping("/tarea")
    public ResponseEntity<List<Tarea>> getTareas(){
        List<Tarea> lista = servicio.listarTareas();
        if (lista.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/tarea/{id}")
    public ResponseEntity<Tarea> getTarea(@PathVariable Long id){
        Tarea t = servicio.buscarPorId(id);
        return ResponseEntity.ok(t);
    }

    @PostMapping("/tarea")
    public void guardarTarea(@RequestBody Tarea tarea){
        if (tarea == null){
            System.out.println("no resuelve json, llega null");
            return;
        }
        servicio.guardarTarea(tarea);
    }

    @PutMapping("/tarea/{id}")
    public ResponseEntity<Void> actualizarTarea(@PathVariable Long id, @RequestBody Tarea tarea){
        servicio.actualizarTarea(id, tarea);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tarea/{id}")
    public ResponseEntity<Void> eliminarTarea(@PathVariable Long id){
        servicio.eliminarTarea(id);
        return ResponseEntity.ok().build();
    }


}
