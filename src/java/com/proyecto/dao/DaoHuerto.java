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
 * @param <Huerto>
 */
public interface DaoHuerto<Huerto> {
    void save(Huerto h);
    List<Huerto> listarHuerto(int id_usuario);
    public int NumeroCultivo(int id_huerto); 
}
