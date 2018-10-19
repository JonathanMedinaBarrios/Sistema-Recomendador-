/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoPlanta;
import com.proyecto.POJOS.Planta;
import com.proyecto.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;



/**
 *
 * @author FAMILIA
 */
public class DaoPlantaImpl implements DaoPlanta<Planta> {

    
    @Override
    public List<Planta> Listar() {
       SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("FROM Planta"); 
        List <Planta> lista = consulta.list();
        return lista;
    }

    @Override
    public List<Planta> buscarPlanta(String mes) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("FROM Planta WHERE (periodoSiembra LIKE '%"+mes+"%')"); 
        List <Planta> lista = consulta.list();
        sesion.close();
        return  lista; 
    }
}
