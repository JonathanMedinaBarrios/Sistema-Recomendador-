/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoCultivo;
import com.proyecto.db.JdbcConnect;
import com.proyecto.modelo.Cultivo;
import com.proyecto.modelo.Planta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author FAMILIA
 */
public class DaoCultivoImpl implements DaoCultivo<Cultivo> {

    @Override
    public void save(Cultivo c) {
        Connection connect = null;
        try {
            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.
                    prepareStatement("Insert into Cultivos (fecha_siembra,id_Planta, id_huerto)"
                            + "values(?,?,?);");
            pst.setTimestamp(1, new Timestamp(c.getFecha_siembra().getTime()));
            pst.setInt(2, c.getId_planta());
            pst.setInt(3, c.getId_huerto());
            pst.executeUpdate();
            connect.commit();
        } catch (ClassNotFoundException | SQLException ex) {
            try {
                if (connect != null) {
                    connect.rollback();
                }
            } catch (SQLException ex1) {
                Logger.getLogger(DaoCultivoImpl.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DaoCultivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Cultivo> listarCultivos(int id_Huerto) {
        List<Cultivo> lista = new ArrayList<>();
        try {
            Connection connect = JdbcConnect.getConnect();
            PreparedStatement pst = connect.
                    prepareStatement("Select plantas.nombre,plantas.Días_a_cosechar,cultivos.fecha_siembra,plantas.RutaImagen\n"
                            + "from cultivos \n"
                            + "INNER JOIN huertos   on cultivos.id_huerto  = huertos.id\n"
                            + "INNER JOIN plantas   on cultivos.id_Planta  = plantas.id\n"
                            + "where cultivos.id_huerto=?;");
            pst.setInt(1, id_Huerto);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cultivo c = new Cultivo();
                Planta planta = new Planta();
                c.setFecha_siembra(rs.getDate(3));
                c.setPlanta(planta);
                c.getPlanta().setNombre(rs.getString(1));
                c.getPlanta().setDías_a_cosechar(rs.getInt(2));
                c.getPlanta().setRuta_imagen(rs.getString(4));
                c.setProgreso(this.progreso(c.getFecha_siembra(),c.getPlanta().getDías_a_cosechar())); 
                lista.add(c);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DaoCultivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void delete(int id_cultivo) {
        Connection connect = null;
        try {
            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.prepareStatement("DELETE FROM Cultivos where id=?");
            pst.setInt(1, id_cultivo);
            pst.executeUpdate();
            connect.commit();
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
    }

    @Override
    public void update(Cultivo c) {
        Connection connect = null;
        try {
            connect = JdbcConnect.getConnect();

            PreparedStatement pst = connect.
                    prepareStatement("Update Cultivo"
                            + "set fecha_siembra=? where id=?");
            pst.setTimestamp(1, new Timestamp(c.getFecha_siembra().getTime()));
            pst.setInt(2, c.getId());
            pst.executeUpdate();
            connect.commit();

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
    }
    
    public double progreso(Date fecha,int DiasCosechar){
        double progreso=0; 
        Date fechaActual = new Date();
        long diferenciaEnMes = fechaActual.getTime()-fecha.getTime(); 
        long dias = diferenciaEnMes/ (1000 * 60 * 60 * 24);
        progreso = (dias*100)/DiasCosechar;
        return progreso; 
    }

}


