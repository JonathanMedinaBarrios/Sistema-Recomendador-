/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoCultivo;
import com.proyecto.db.JdbcConnect;
import com.proyecto.POJOS.Cultivo;
import com.proyecto.POJOS.Planta;
import com.proyecto.util.HibernateUtil;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author FAMILIA
 */
public class DaoCultivoImpl implements DaoCultivo<Cultivo> {

    @Override
    public void save(Cultivo c) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.save(c); 
        tx.commit();
        sesion.close();
    }

    @Override
    public List<Cultivo> listarCultivos(int id_huerto) {
        List<Cultivo> lista = new ArrayList<>();
         SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query consulta = sesion.createSQLQuery(" SELECT planta.nombre,planta.dias_a_cosechar,cultivo.fecha_siembra,planta.rutaimagen\n" +
                                               " FROM cultivo \n" +
                                               " INNER JOIN huerto   on cultivo.id_huerto  = huerto.id\n" +
                                               " INNER JOIN planta   on cultivo.id_Planta  = planta.id\n" +
                                               " where cultivo.id_huerto= :id_huerto");
        consulta.setParameter("id_huerto",id_huerto); 
        ResultSet rs;
        rs = (ResultSet) consulta.list();
        try {
            while (rs.next()) {
                Cultivo c = new Cultivo();
                Planta planta = new Planta();
                c.setFechaSiembra(rs.getDate(3));
                c.setPlanta(planta);
                c.getPlanta().setNombre(rs.getString(1));
                c.getPlanta().setDiasACosechar(rs.getInt(2));
                c.getPlanta().setRutaimagen(rs.getString(4));
                c.setProgreso(this.progreso(c.getFechaSiembra(),c.getPlanta().getDiasACosechar())); 
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DaoCultivoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    @Override
    public void delete(Cultivo c) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.delete(c); 
        tx.commit();
        sesion.close();
    }

    @Override
    public void update(Cultivo c) {
        
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


