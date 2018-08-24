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
 * @param <Producto>
 */
public interface DaoProducto<Producto> {
    void save(Producto p);
    void Update(Producto p);
    void Delete(Producto p);
    List<Producto> ListarProducto(int id_vivero, String tipo);
}