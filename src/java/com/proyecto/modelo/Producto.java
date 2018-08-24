/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.modelo;

import java.io.Serializable;
import javax.persistence.Lob;

/**
 *
 * @author FAMILIA
 */
public class Producto implements  Serializable {
    
    private int id, id_vivero; 
    private String Nombre,Descripcion,Tipo, Marca, Presentacion;
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_vivero() {
        return id_vivero;
    }

    public void setId_vivero(int id_vivero) {
        this.id_vivero = id_vivero;
    }
    
    
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String Marca) {
        this.Marca = Marca;
    }

    public String getPresentacion() {
        return Presentacion;
    }

    public void setPresentacion(String Presentacion) {
        this.Presentacion = Presentacion;
    }

}
