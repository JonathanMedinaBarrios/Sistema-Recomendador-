/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.modelo;

/**
 *
 * @author FAMILIA
 */
public class Vivero {
    private int id_vivero; 
    private String nombreVivero, direccion, correo, contraseña,ciudad; 

    public int getId_vivero() {
        return id_vivero;
    }

    public void setId_vivero(int id_vivero) {
        this.id_vivero = id_vivero;
    }

    public String getNombreVivero() {
        return nombreVivero;
    }

    public void setNombreVivero(String nombreVivero) {
        this.nombreVivero = nombreVivero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    
    
    
}
