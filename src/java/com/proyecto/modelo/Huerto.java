
package com.proyecto.modelo;


public class Huerto {
    int idhuerto, id_Usuario ,numeroCultio;  
    private String nombre, rutaImagen, descripcion; 
    private double  area; 

    public int getIdhuerto() {
        return idhuerto;
    }

    public void setIdhuerto(int idhuerto) {
        this.idhuerto = idhuerto;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getNumeroCultio() {
        return numeroCultio;
    }

    public void setNumeroCultio(int numeroCultio) {
        this.numeroCultio = numeroCultio;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
    
}
