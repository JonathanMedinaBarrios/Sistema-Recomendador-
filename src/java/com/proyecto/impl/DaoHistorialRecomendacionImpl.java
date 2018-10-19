/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.POJOS.Historialrecomendacion;
import com.proyecto.POJOS.Recomendacion;
import com.proyecto.dao.DaoHistorialRecomendacion;
import com.proyecto.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author FAMILIA
 */
public class DaoHistorialRecomendacionImpl implements DaoHistorialRecomendacion<Recomendacion> {

    @Override
    public List<Recomendacion> listarRecomendacion(int id_usuario){
        
        List<Historialrecomendacion> lista;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query consulta = sesion.createQuery("from Historialrecomendacion where id_usuario= :id_usuario");
        consulta.setParameter("id_usuario", id_usuario);
        lista = consulta.list();
        List<Recomendacion> lista2 = new ArrayList<>();
        for(Historialrecomendacion historial : lista){
          lista2.add(historial.getRecomendacion());
        }
        return lista2;
    }
    
    public List<Recomendacion> listarUltimasRecomendacion(int id_usuario){
        
        return null;
    }

}
