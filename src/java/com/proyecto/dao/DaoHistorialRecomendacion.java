/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.dao;

import java.util.List;

/**
 *
 * @author FAMILIA
 * @param <Recomendacion>
 */
public interface DaoHistorialRecomendacion<Recomendacion>{
    
    List<Recomendacion> listarRecomendacion(int id_usuario);
}
