/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.dao.DaoCultivo;
import com.proyecto.impl.DaoCultivoImpl;
import com.proyecto.modelo.Cultivo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author FAMILIA
 */
@Named(value = "cultivoManagedBean")
@ApplicationScoped
public class CultivoJSFManagedBean {

    private Cultivo cultivo = new Cultivo();
    private DaoCultivo dao = new DaoCultivoImpl();
    private List<Cultivo> lista = new ArrayList<>();
    FacesContext context;
    HuertoJSFManagedBean huerto;

    /**
     * Creates a new instance of CultivoJSFManagedBean
     */
    //LISTAR CULTIVOS 
    @PostConstruct
    public void iniciar() {
        context = FacesContext.getCurrentInstance();
        huerto = context.getApplication().evaluateExpressionGet(context, "#{huertoManagedBean}", HuertoJSFManagedBean.class); 
    }

    public CultivoJSFManagedBean() {
    }

    public Cultivo getCultivo() {
        return cultivo;
    }

    public void setCultivo(Cultivo cultivo) {
        this.cultivo = cultivo;
    }
    
    public List<Cultivo> getLista(){
        return lista;
    }

    public void setLista(List<Cultivo> lista) {
        this.lista = lista;
    }

    public void save() {
        try {
            cultivo.setId_huerto(huerto.getHuerto().getIdhuerto());
            dao.save(cultivo);
            cultivo = new Cultivo(); 
            huerto.listar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "aviso", "Registro exitoso..."));
        } catch (Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "aviso", "Registro fallido..."));
        }
        
    }
        
    public String listar(){
         lista= dao.listarCultivos(huerto.getHuerto().getIdhuerto());
         return "MisCultivos"; 
    }
    
    
}
