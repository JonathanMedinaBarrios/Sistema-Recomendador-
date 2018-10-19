/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.impl;

import com.proyecto.dao.DaoCultivo;
import com.proyecto.POJOS.Cultivo;
import com.proyecto.POJOS.Planta;
import com.proyecto.util.HibernateUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Transaction tx = sesion.beginTransaction();
        sesion.save(c);
        tx.commit();
        sesion.close();
    }

    @Override
    public List<Cultivo> listarCultivos(int id_huerto) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query consulta = sesion.createSQLQuery(" SELECT planta.nombre,planta.dias_a_cosechar,cultivo.fecha_siembra,planta.rutaimagen\n"
                + " FROM cultivo \n"
                + " INNER JOIN huerto   on cultivo.id_huerto  = huerto.id\n"
                + " INNER JOIN planta   on cultivo.id_Planta  = planta.id\n"
                + " where cultivo.id_huerto= :id_huerto");
        consulta.setParameter("id_huerto", id_huerto);
        List<Object[]> listaBusqueda = consulta.list();
        List<Cultivo> lista = new ArrayList<>();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        for (Object[] row : listaBusqueda) {
            Cultivo cultivo = new Cultivo();
            String strFecha = row[2].toString();
            Date fecha = null;
            try {
                fecha = formatoDelTexto.parse(strFecha);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            Planta planta = new Planta();
            planta.setNombre(row[0].toString());
            planta.setDiasACosechar(Integer.parseInt(row[1].toString()));
            planta.setRutaimagen(row[3].toString());
            cultivo.setPlanta(planta);
            cultivo.setFechaSiembra(fecha);
            cultivo.setProgreso(this.progreso(fecha, Integer.parseInt(row[1].toString())));
            lista.add(cultivo);
        }
        return lista;
    }

    @Override
    public void delete(Cultivo c) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Transaction tx = sesion.beginTransaction();
        sesion.delete(c);
        tx.commit();
        sesion.close();
    }

    @Override
    public void update(Cultivo c) {

    }

    public String progreso(Date fecha, int DiasCosechar) {
        double progreso = 0;
        Date fechaActual = new Date();
        long diferenciaEnMes = fechaActual.getTime() - fecha.getTime();
        long dias = diferenciaEnMes / (1000 * 60 * 60 * 24);
        progreso = (dias * 100) / DiasCosechar;
        return progreso + "";
    }

    @Override
    public List<Cultivo> CalendarioCultivos(int id_usuario) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();
        Query consulta = sesion.createSQLQuery(" SELECT planta.nombre,planta.dias_a_cosechar,cultivo.fecha_siembra\n"
                + "FROM usuario\n"
                + "INNER JOIN huerto   on usuario.id_usuario  = huerto.id_usuario\n"
                + "INNER JOIN cultivo  on huerto.id  = cultivo.id_huerto\n"
                + "INNER JOIN planta   on cultivo.id_Planta  = planta.id\n"
                + "where usuario.id_usuario =:id_usuario");
        consulta.setParameter("id_usuario", id_usuario);
        List<Object[]> listaBusqueda = consulta.list();
        List<Cultivo> lista = new ArrayList<>();
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        for (Object[] row : listaBusqueda) {
            Cultivo cultivo = new Cultivo();
            String strFecha = row[2].toString();
            Date fecha = null;
            try {
                fecha = formatoDelTexto.parse(strFecha);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            Planta planta = new Planta();
            planta.setNombre(row[0].toString());
            planta.setDiasACosechar(Integer.parseInt(row[1].toString()));
            cultivo.setPlanta(planta);
            cultivo.setFechaSiembra(fecha);
            cultivo.setFechaInicialCosecha(this.fechaCosecha(fecha, Integer.parseInt(row[1].toString())));
            lista.add(cultivo);
        }
        return lista;
    }

    public Date fechaCosecha(Date fecha, int dias) {
        if (dias == 0) {
            return fecha;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fecha);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

}
