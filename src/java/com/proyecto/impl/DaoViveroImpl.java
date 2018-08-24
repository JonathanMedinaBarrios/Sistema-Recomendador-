/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoVivero;
import com.proyecto.db.JdbcConnect;
import com.proyecto.modelo.Vivero;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FAMILIA
 */
public class DaoViveroImpl implements DaoVivero<Vivero>, Serializable {

    
    @Override
    public Vivero login(String correo, String password) {
        Vivero V = null; 
        try{
             Connection  connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("SELECT * FROM viveros where correo=? and contraseña=? ;");
            pst.setString(1,correo);
            pst.setString(2,password );
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                V = new Vivero();
                V.setId_vivero(rs.getInt(1));
                V.setNombreVivero(rs.getString(2));
                V.setDireccion(rs.getString(3));
                V.setCorreo(rs.getString(4));
                V.setContraseña(rs.getString(5));
            }            
        }catch(ClassNotFoundException | SQLException ex){
             Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return V; 
    }

    @Override
    public void save(Vivero V) {
        Connection  connect = null; 
        try {
             connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("Insert into Viveros (nombreVivero,correo,contraseña) values(?,?,?);");
            pst.setString(1, V.getNombreVivero() );
            pst.setString(2, V.getCorreo());
            pst.setString(3, V.getContraseña());
            pst.executeUpdate();
            connect.commit();
        } catch (ClassNotFoundException | SQLException e) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public boolean ConsultaVivero(String correo) {
       boolean existe = false; 
        try{
             Connection  connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("SELECT * FROM viveros where correo=? and contraseña=? ;");
            pst.setString(1,correo);
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                existe = true;
            }            
        }catch(ClassNotFoundException | SQLException ex){
             Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe; 
    }

    @Override
    public void CompletarPerfil(Vivero v) {
        Connection  connect = null; 
        try {
             connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("Update  Viveros set direccion=?,cuidad=? where id_Usuario=?;");
            pst.setString(1, v.getDireccion() );
            pst.setString(2, v.getCiudad());
            pst.setInt(3, v.getId_vivero());
            pst.executeUpdate();
            connect.commit();
        } catch (ClassNotFoundException | SQLException e) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
}
