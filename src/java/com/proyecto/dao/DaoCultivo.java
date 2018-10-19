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
 * @param <Cultivo>
 */
public interface DaoCultivo<Cultivo> {
    
    public void save(Cultivo c);
    public void delete(Cultivo cultivo);
    public void update(Cultivo c);
    List<Cultivo> listarCultivos(int id_Huerto);
    List<Cultivo> CalendarioCultivos(int id_usuario);
}
