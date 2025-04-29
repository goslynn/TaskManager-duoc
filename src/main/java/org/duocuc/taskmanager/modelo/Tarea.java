package org.duocuc.taskmanager.modelo;

public class Tarea {
    private Long id;
    private static Long contador = 0L;
    private String nombre;
    private String descripcion;
    private Estado estado;

    public Tarea(){
        this.id = contador++;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Long getContador() {
        return contador;
    }

    public static void setContador(Long contador) {
        Tarea.contador = contador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "\nid=" + id +
                "\nnombre='" + nombre +
                "\ndescripcion='" + descripcion +
                "\nestado=" + estado +
                "\n}";
    }
}
