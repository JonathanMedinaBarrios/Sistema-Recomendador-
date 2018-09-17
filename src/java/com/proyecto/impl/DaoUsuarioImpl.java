/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoUsuario;
import com.proyecto.POJOS.Usuario;
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
public class DaoUsuarioImpl implements DaoUsuario<Usuario>, Serializable{

    @Override
    public Usuario login(String user, String password) {
        Usuario U = null; 
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        String hql = "from Usuario where correo = '"+user+"'  and  contrasena ='"+password+"'";
        try {
            U  = (Usuario) sesion.createQuery(hql).uniqueResult(); 
        } catch (Exception e) {
        }
        sesion.close();
        return U; 
    }

    @Override
    public void Save(Usuario U) {
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Transaction tx = sesion.beginTransaction(); 
        sesion.save(U); 
        tx.commit();
        sesion.close();
    }

    @Override
    public void CompletarPerfil(Usuario U) {
       SessionFactory sf =  HibernateUtil.getSessionFactory();
       Session sesion = sf.openSession(); 
       Transaction tx = sesion.beginTransaction(); 
        sesion.update(U); 
        tx.commit();
        sesion.close();
    }

    @Override
    public boolean ConsultaUsuario(String correo) {
        boolean existe=false; 
        SessionFactory sf =  HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession(); 
        Query consulta = sesion.createQuery("from Usuario where correo = :email"); 
        consulta.setParameter("email",correo); 
        List<Usuario> usuarios = consulta.list();
         for(Usuario usuario :usuarios ){
            existe = true; 
        }
        sesion.close();
        return existe; 
    }
    
}
