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
public class Planta {
    private int id; 
    private String nombre; 
    private String especie; 
    private String caracteristicas; 
    private String Periodo_siembra; 
    private String tamaño; 
    private String aprovechamiento; 
    private int Días_a_cosechar;
    private String Ruta_imagen;

    public Planta() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }
    
    

    public String getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(String aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    public String getPeriodo_siembra() {
        return Periodo_siembra;
    }

    public void setPeriodo_siembra(String Periodo_siembra) {
        this.Periodo_siembra = Periodo_siembra;
    }

    public int getDías_a_cosechar() {
        return Días_a_cosechar;
    }

    public void setDías_a_cosechar(int Días_a_cosechar) {
        this.Días_a_cosechar = Días_a_cosechar;
    }

    public String getRuta_imagen() {
        return Ruta_imagen;
    }

    public void setRuta_imagen(String Ruta_imagen) {
        this.Ruta_imagen = Ruta_imagen;
    }
     
}
