/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoProducto;
import com.proyecto.db.JdbcConnect;
import com.proyecto.POJOS.Producto;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FAMILIA
 */
public class DaoProductoImpl implements DaoProducto<Producto>,Serializable {

    @Override
    public void save(Producto p) {
        Connection connect = null;
        try {
            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.
                    prepareStatement("Insert into Productos (nombre,descripcion,marca,tipo,id_vivero) values(?,?,?,?,?);");
            pst.setString(1, p.getNombre());
            pst.setString(2, p.getDescripcion());
            pst.setString(3, p.getMarca());
            pst.setString(4, p.getTipo());
            pst.setInt(5, p.getVivero().getIdVivero());
            pst.executeUpdate();
            connect.commit();
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void Update(Producto p) {
         Connection connect = null;
          try {

            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.
                    prepareStatement("Update  Productos "
                            + "set nombre=?,descripcion=?,marca=?,tipo=? where id=?;");
            pst.setInt(5, p.getId());
            pst.setString(1, p.getNombre());
            pst.setString(2, p.getDescripcion());
            pst.setString(3, p.getMarca());
            pst.setString(4, p.getTipo());
            pst.executeUpdate();
            connect.commit();
        } catch ( SQLException ex) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
    
    @Override
    public void Delete(Producto p) {
        Connection connect = null;
          try {

            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.prepareStatement("DELETE FROM Productos  where id=?;");
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            connect.commit();
        } catch ( SQLException ex) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }

    @Override
    public List<Producto> ListarProducto(int id_viviro, String tipo) {
        List<Producto> lista = new ArrayList<>();
        try {
            Connection connect = JdbcConnect.getConnect();
            PreparedStatement pst = connect.
                    prepareStatement("Select * from productos where id_vivero=? and tipo=?");
            pst.setInt(1, id_viviro);
            pst.setString(2, tipo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setDescripcion(rs.getString(3));
                p.setMarca(rs.getString(4));
                p.setTipo(rs.getString(5));
                lista.add(p); 
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
  
}
