/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.dao.DaoHuerto;
import com.proyecto.impl.DaoHuertoImpl;
import com.proyecto.modelo.Huerto;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author FAMILIA
 */
@Named(value = "huertoManagedBean")
@ApplicationScoped
public class HuertoJSFManagedBean {

    private Huerto huerto = new Huerto(); 
    private DaoHuerto dao = new DaoHuertoImpl();
    FacesContext context = FacesContext.getCurrentInstance();
    UsuarioJSFManagedBean usuario = context.getApplication().evaluateExpressionGet(context,"#{usuarioManagedBean}" ,UsuarioJSFManagedBean.class ); 
    private List<Huerto> lista  = new ArrayList<>();
    
    /**
     * Creates a new instance of HuertoJSFManagedBean
     */
    public HuertoJSFManagedBean() {
    }
    
    /*LISTAR HUERTOS*/
    @PostConstruct
    public void iniciar() {
        lista = dao.listarHuerto(usuario.getUsuario().getId());
    }

    public Huerto getHuerto() {
        return huerto;
    }

    public void setHuerto(Huerto huerto) {
        this.huerto = huerto;
    }

    public List<Huerto> getLista() {
        return lista;
    }

    public void setLista(List<Huerto> lista) {
        this.lista = lista;
    }
    
        
    public void save(){
        try {
            huerto.setId_Usuario(usuario.getUsuario().getId());
            dao.save(huerto);
            lista = dao.listarHuerto(usuario.getUsuario().getId());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "aviso", "Registro exitoso..."));
            huerto = new Huerto(); 
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "aviso", "Registro fallido..."));
        }
    } 
    
    public void listar(){
        lista = dao.listarHuerto(usuario.getUsuario().getId());
        huerto = new Huerto(); 
    }
        
}
