/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoHuerto;
import com.proyecto.POJOS.Huerto;;
import com.proyecto.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author FAMILIA
 */
public class DaoHuertoImpl implements DaoHuerto<Huerto> {

    @Override
    public void save(Huerto h) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.save(h); 
        tx.commit();
        sesion.close();
    }

    
    @Override
    public List<Huerto> listarHuerto(int id_usuario) {
        List <Huerto> lista;
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("from Huerto where  id_usuario= :id_usuario"); 
        consulta.setParameter("id_usuario",id_usuario); 
        lista = consulta.list();
        List <Huerto> lista2 = new ArrayList<>();
        for(Huerto huerto : lista){
            huerto.setNumeroCultivo(NumeroCultivo(huerto.getId()));
            lista2.add(huerto);
        } 
        sesion.close();
        return lista2;
    }
    
    @Override
    public int NumeroCultivo(int id_huerto) {
        int numeroCultivo = 0; 
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("SELECT COUNT(*) from Cultivo where id_huerto = :id_huerto"); 
        consulta.setParameter("id_huerto",id_huerto); 
        return  numeroCultivo = Integer.parseInt(""+consulta.uniqueResult());        
    }

}
