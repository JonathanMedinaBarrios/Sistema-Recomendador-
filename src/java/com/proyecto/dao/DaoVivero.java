/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.dao;

/**
 *
 * @author FAMILIA
 * @param <Vivero>
 */
public interface DaoVivero<Vivero> {
    
    Vivero login(String correo, String password);
    public void save(Vivero v);
    boolean ConsultaVivero(String correo); 
    void CompletarPerfil(Vivero u);
}
