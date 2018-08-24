/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoPlanta;
import com.proyecto.db.JdbcConnect;
import com.proyecto.modelo.Planta;
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
public class DaoPlantaImpl implements DaoPlanta<Planta> {

    
    @Override
    public List<Planta> Listar() {
        List<Planta> lista = new ArrayList<>();
        try {
            Connection connect = JdbcConnect.getConnect();
            PreparedStatement pst = connect.
                    prepareStatement("SELECT * FROM Plantas");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Planta p = new Planta();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setRuta_imagen(rs.getString(3));
                p.setEspecie(rs.getString(4));
                p.setCaracteristicas(rs.getString(5));
                p.setTamaño(rs.getString(6));
                p.setPeriodo_siembra(rs.getString(7));
                p.setAprovechamiento(rs.getString(8));
                p.setDías_a_cosechar(rs.getInt(9));
                lista.add(p); 
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public List<Planta> buscarPlanta(String mes) {
        List<Planta> lista = new ArrayList<>();
       
        try {
            Connection connect = JdbcConnect.getConnect();
            PreparedStatement pst = connect.
                    prepareStatement("SELECT * FROM Plantas WHERE (Periodo_siembra LIKE '%"+mes+"%')");
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                 Planta p = new Planta();
                p.setNombre(rs.getString(2));
                p.setRuta_imagen(rs.getString(3));
                p.setEspecie(rs.getString(4));
                p.setCaracteristicas(rs.getString(5));
                p.setTamaño(rs.getString(6));
                p.setPeriodo_siembra(rs.getString(7));
                p.setAprovechamiento(rs.getString(8));
                p.setDías_a_cosechar(rs.getInt(9));
                lista.add(p);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoProductoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  lista; 
    }
}
