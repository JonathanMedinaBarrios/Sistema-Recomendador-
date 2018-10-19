/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.dao.DaoCultivo;
import com.proyecto.impl.DaoCultivoImpl;
import com.proyecto.POJOS.Cultivo;
import com.proyecto.POJOS.Historialrecomendacion;
import com.proyecto.POJOS.Planta;
import com.proyecto.POJOS.Recomendacion;
import com.proyecto.dao.DaoRecomendacion;
import com.proyecto.impl.DaoRecomendacionCuidadoImpl;
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

    private Cultivo cultivo;
    private Planta planta ; 
    private DaoRecomendacion recomendancion = new DaoRecomendacionCuidadoImpl();
    
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
        planta = new Planta(); 
        cultivo = new Cultivo();
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

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }
    
   
    public String  save() {
        try { 
            cultivo.setHuerto(huerto.getHuerto());
            cultivo.setPlanta(planta);
            Historialrecomendacion historial = new Historialrecomendacion();
            historial.setRecomendacion((Recomendacion) recomendancion.crearRecomendacion(huerto.getHuerto().getUsuario().getIdUsuario(), planta.getId()));
            if(historial.getRecomendacion()==null){
            }else{
                historial.setUsuario(huerto.getHuerto().getUsuario());
                recomendancion.save(historial);
            }
            dao.save(cultivo);
            cultivo = new Cultivo(); 
            huerto.listar();
            FacesMessage msg = new  FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Exitoso!"); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "MisHuertos";
        } catch (Exception e){
            FacesMessage msg = new  FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "Registro Fallido"); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "MisHuertos";
        }
        
    }
        
    public String listar(){
         lista= dao.listarCultivos(huerto.getHuerto().getId());
         return "MisCultivos"; 
    }
    
    
}
