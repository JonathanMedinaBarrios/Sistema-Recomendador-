/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoVivero;
import com.proyecto.POJOS.Vivero;
import com.proyecto.util.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author FAMILIA
 */
public class DaoViveroImpl implements DaoVivero<Vivero>, Serializable {

    
    @Override
    public Vivero login(String correo, String password) {
        Vivero V = null; 
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        String hql = "from Vivero where correo = '"+correo+"'  and  contrasena ='"+password+"'";
        try {
            V  = (Vivero) sesion.createQuery(hql).uniqueResult(); 
        } catch (Exception e) {
        }
        sesion.close();
        return V; 
    }

    @Override
    public void save(Vivero V) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.save(V); 
        tx.commit();
        sesion.close();
    }

    @Override
    public boolean ConsultaVivero(String correo) {
       boolean existe = false; 
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("from Vivero where correo = :email"); 
        consulta.setParameter("email",correo); 
        List<Vivero> viveros = consulta.list();
         for(Vivero vivero :viveros ){
            existe = true; 
        }
        sesion.close();
        return existe; 
    }

    @Override
    public void CompletarPerfil(Vivero v) {
       SessionFactory sf =  HibernateUtil.getSessionFactory();
       Session sesion = sf.openSession(); 
       Transaction tx = sesion.beginTransaction(); 
       sesion.update(v); 
       tx.commit();
       sesion.close();
    }
    
}
