/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.modelo;

import java.util.Date;


/**
 *
 * @author FAMILIA
 */
public class Cultivo {
    int id , id_huerto, id_planta;
    double progreso; 
    private Date Fecha_siembra; 
    private Planta planta; 

    public int getId_planta() {
        return id_planta;
    }

    public void setId_planta(int id_planta) {
        this.id_planta = id_planta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_huerto() {
        return id_huerto;
    }

    public void setId_huerto(int id_huerto) {
        this.id_huerto = id_huerto;
    }

   
   public Date getFecha_siembra() {
        return Fecha_siembra;
    }

    public void setFecha_siembra(Date Fecha_siembra) {
        this.Fecha_siembra = Fecha_siembra;
    }

    public double getProgreso() {
        return progreso;
    }

    public void setProgreso(double progreso) {
        this.progreso = progreso;
    }

   
 
    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
   
}
