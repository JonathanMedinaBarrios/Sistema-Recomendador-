/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.POJOS.Historialrecomendacion;
import com.proyecto.POJOS.Recomendacion;
import com.proyecto.dao.DaoRecomendacion;
import com.proyecto.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 *@author FAMILIA
 */
public class DaoRecomendacionCuidadoImpl implements DaoRecomendacion<Recomendacion> {

    
    @Override
    public Recomendacion crearRecomendacion(int id_usuario,int id) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query consulta = sesion.createSQLQuery(" SELECT COUNT(planta.nombre)\n"
                + "FROM usuario\n"
                + "INNER JOIN huerto   on usuario.id_usuario  = huerto.id_usuario\n"
                + "INNER JOIN cultivo  on huerto.id  = cultivo.id_huerto\n"
                + "INNER JOIN planta   on cultivo.id_Planta  = planta.id\n"
                + "where usuario.id_usuario =:id_usuario and planta.id=:id ");
        consulta.setParameter("id_usuario", id_usuario);
        consulta.setParameter("id",id);
        int numero = Integer.parseInt(""+consulta.uniqueResult());  
        if(numero == 0){
            consulta = sesion.createSQLQuery("SELECT planta.nombre From planta where planta.id =:id"); 
            consulta.setParameter("id",id);
            String nombre = "" +consulta.uniqueResult();
            Recomendacion recomendacion = new Recomendacion();
            recomendacion.setNombre("Recomendacion cuidado de planta   " + nombre);
            consulta = sesion.createSQLQuery("SELECT cuidadosplanta.descripcion From cuidadosplanta where cuidadosplanta.id_planta =:id"); 
            consulta.setParameter("id",id);
            String descripcion = "Has sembrado una nueva planta y organicApp te acompañará en el crecimiento\n"
                                +"de éste nuevo cultivo. Recomendaciones iniciales:\n"; 
            String recomendaciones = ""+consulta.uniqueResult(); 
            recomendacion.setDescripcion(descripcion + recomendaciones);
            Date fechaActual = new Date();
            recomendacion.setFecha(fechaActual);    
            recomendacion.setTipo("Cuidados");
            consulta = sesion.createSQLQuery("SELECT planta.rutaImagen From planta where planta.id =:id"); 
            consulta.setParameter("id",id);
            String rutaImagen = ""+consulta.uniqueResult();
            recomendacion.setRutaimagen(rutaImagen);
            return recomendacion;
            
        }else{
            return null; 
        }
    }

    @Override
    public void save(Historialrecomendacion recomendacion){
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.save(recomendacion.getRecomendacion()); 
        sesion.save(recomendacion);
        tx.commit();
        sesion.close();
    }
    
}
