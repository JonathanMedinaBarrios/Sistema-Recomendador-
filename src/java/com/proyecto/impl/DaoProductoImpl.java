/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoProducto;
import com.proyecto.POJOS.Producto;
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
public class DaoProductoImpl implements DaoProducto<Producto>{

    @Override
    public void save(Producto p) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.save(p);
        tx.commit();
        sesion.close();
    }

    @Override
    public void Update(Producto p) {
         
    }
    
    @Override
    public void Delete(Producto p) {
        
    }

    @Override
    public List<Producto> ListarProducto(int id_viviro, String tipo) {
        List<Producto> lista ;
         SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("from Producto where  id_vivero =:id_vivero  and tipo=:tipo"); 
        consulta.setParameter("id_vivero",id_viviro); 
        consulta.setParameter("tipo",tipo); 
        lista = consulta.list();
        sesion.close();
        return lista;
    }
  
}
