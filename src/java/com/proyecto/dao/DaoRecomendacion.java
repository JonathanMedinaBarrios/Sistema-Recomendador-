/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.dao;

import com.proyecto.POJOS.Historialrecomendacion;
/**
 *
 * @author FAMILIA
 * @param <Recomendacion>
 */
public interface DaoRecomendacion<Recomendacion> {
    
    public Recomendacion crearRecomendacion(int id_usuario , int id); 
    public void save(Historialrecomendacion recomendacion);
    
}
