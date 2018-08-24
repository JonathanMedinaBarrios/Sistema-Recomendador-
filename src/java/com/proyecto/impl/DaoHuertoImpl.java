/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoHuerto;
import com.proyecto.db.JdbcConnect;
import com.proyecto.modelo.Huerto;
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
public class DaoHuertoImpl implements DaoHuerto<Huerto> {

    @Override
    public void save(Huerto h) {
        Connection connect = null;
        try {
            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.
                    prepareStatement("Insert into huertos (nombre,descripcion,area,id_usuario) values(?,?,?,?);");
            pst.setString(1,h.getNombre());
            pst.setString(2,h.getDescripcion());
            pst.setDouble(3,h.getArea());
            pst.setInt(4,h.getId_Usuario());
            pst.executeUpdate();
            connect.commit();
        } catch (ClassNotFoundException | SQLException ex){
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoHuertoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoHuertoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    
    @Override
    public List<Huerto> listarHuerto(int id_usuario) {
        List<Huerto> lista = new ArrayList<>();
        try {
            Connection connect = JdbcConnect.getConnect();
            PreparedStatement pst = connect.
                    prepareStatement("Select * from Huertos where id_usuario=?;");
            pst.setInt(1,id_usuario);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 Huerto h = new Huerto(); 
                 h.setIdhuerto(rs.getInt(1));
                 h.setNombre(rs.getString(2));
                 h.setDescripcion(rs.getString(4));
                 h.setArea(rs.getDouble(5));
                 h.setNumeroCultio(this.NumeroCultivo(h.getIdhuerto())); 
                 lista.add(h); 
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    
    @Override
    public int NumeroCultivo(int id_huerto) {
        Connection connect = null;
        int numeroCultivo = 0; 
        try {
           connect = JdbcConnect.getConnect();
            PreparedStatement pst = connect.
                    prepareStatement("SELECT COUNT(*) from Cultivos where id_huerto=?"); 
            pst.setInt(1,id_huerto);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               numeroCultivo = rs.getInt(1);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoCultivoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoCultivoImpl.class.getName()).log(Level.SEVERE, null, e);
        }     
        return  numeroCultivo;        
    }

}
