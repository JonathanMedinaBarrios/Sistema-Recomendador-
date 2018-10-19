/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.POJOS.Historialrecomendacion;
import com.proyecto.POJOS.Recomendacion;
import com.proyecto.dao.DaoHistorialRecomendacion;
import com.proyecto.impl.DaoHistorialRecomendacionImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author FAMILIA
 */
@Named(value = "historialRecomendacionesBean")
@ViewScoped
public class HistorialRecomendacionesJSFManagedBean implements Serializable{


    private Recomendacion recomendacion = new Recomendacion();
    private DaoHistorialRecomendacion dao = new DaoHistorialRecomendacionImpl(); 
    private List<Recomendacion> lista  = new ArrayList<>();
    FacesContext context = FacesContext.getCurrentInstance();
    UsuarioJSFManagedBean usuario = context.getApplication().evaluateExpressionGet(context,"#{usuarioManagedBean}" ,UsuarioJSFManagedBean.class );
    
    @PostConstruct
    public void iniciar(){
        lista = dao.listarRecomendacion(usuario.getUsuario().getIdUsuario());
    }
    
    public HistorialRecomendacionesJSFManagedBean(){
    }

    public Recomendacion getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(Recomendacion recomendacion) {
        this.recomendacion = recomendacion;
    }

    public List<Recomendacion> getLista() {
        return lista;
    }

    public void setLista(List<Recomendacion> lista) {
        this.lista = lista;
    }

}
