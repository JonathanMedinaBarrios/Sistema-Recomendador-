/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.POJOS.Cultivo;
import com.proyecto.dao.DaoCultivo;
import com.proyecto.impl.DaoCultivoImpl;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

/**
 *
 * @author FAMILIA
 */
@Named(value = "calendarioCosechaBean")
@ViewScoped
public class CalendarioCosechaJSFManagedBean implements Serializable{

    private ScheduleModel periodoCosecha ;
    private DaoCultivo dao = new DaoCultivoImpl();
    FacesContext context = FacesContext.getCurrentInstance();
    UsuarioJSFManagedBean usuario = context.getApplication().evaluateExpressionGet(context, "#{usuarioManagedBean}", UsuarioJSFManagedBean.class);

    @PostConstruct
    public void init() {
       periodoCosecha = new DefaultScheduleModel();
       List <Cultivo> lista = dao.CalendarioCultivos(usuario.getUsuario().getIdUsuario());
        
        if(lista == null){
            periodoCosecha.addEvent(new DefaultScheduleEvent()); 
        }else{
        }
        for(Cultivo cultivo : lista) {
            String titulo = "Cosechar ";
            Date inicio = cultivo.getFechaInicialCosecha();
            Date fin = cultivo.getFechaInicialCosecha();
            periodoCosecha.addEvent(new DefaultScheduleEvent(titulo, inicio, fin));
        } 
    }

    public ScheduleModel getPeriodoCosecha() {
        return periodoCosecha;
    }

}
