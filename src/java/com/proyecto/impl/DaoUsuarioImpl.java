/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoUsuario;
import com.proyecto.db.JdbcConnect;
import com.proyecto.modelo.Usuario;
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
public class DaoUsuarioImpl implements DaoUsuario<Usuario>, Serializable{

    @Override
    public Usuario login(String user, String password) {
        Usuario U = null; 
        try{
             Connection  connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("SELECT * FROM usuarios where correo=? and contraseña=? ;");
            pst.setString(1,user );
            pst.setString(2,password );
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                U = new Usuario();
                U.setId(rs.getInt(1));
                U.setNombre(rs.getString(2));
                U.setApellido(rs.getString(3));
                U.setDireccion(rs.getString(4));
                U.setCuidad(rs.getString(5));
                U.setCorreo(rs.getString(6));
            }            
        }catch(ClassNotFoundException | SQLException ex){
             Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return U; 
    }

    @Override
    public void Save(Usuario U) {
        Connection  connect = null; 
        try {
             connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("Insert into Usuarios (nombre,apellidos,correo,contraseña) values(?,?,?,?);");
            pst.setString(1, U.getNombre() );
            pst.setString(2, U.getApellido());
            pst.setString(3, U.getCorreo());
            pst.setString(4, U.getContraseña());
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
    public void CompletarPerfil(Usuario u) {
        Connection  connect = null; 
        try {
             connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("Update  Usuarios set direccion=?,cuidad=? where id_Usuario=?;");
            pst.setString(1, u.getDireccion() );
            pst.setString(2, u.getCuidad());
            pst.setInt(3, u.getId());
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
    public boolean ConsultaUsuario(String correo) {
        boolean existe=false; 
        try{
             Connection  connect = JdbcConnect.getConnect();
             PreparedStatement pst = connect.
                    prepareStatement("SELECT * FROM usuarios where correo=?;");
            pst.setString(1,correo );
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                existe=true;
            }            
        }catch(ClassNotFoundException | SQLException ex){
             Logger.getLogger(DaoUsuarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe; 
    }
    
}
