/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;
    
import com.proyecto.dao.DaoPlanta;
import com.proyecto.impl.DaoPlantaImpl;
import com.proyecto.modelo.Planta;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

@Named(value = "plantaManegedBean")
@ApplicationScoped
public class PlantaManegedBean {
    
    private List<Planta> plantas = new ArrayList<>();;
    private DaoPlanta dao = new DaoPlantaImpl();
    private List<Planta> PlantasBusquedas = new ArrayList<>();
    private String mes = "Enero"; 
    
    @PostConstruct
    public void init() {
       plantas = dao.Listar();
       PlantasBusquedas =  dao.buscarPlanta(mes);
    }
     
    public List<Planta> getPlantas() {
        return plantas;
    }

    public List<Planta> getPlantasBusquedas() {
        return PlantasBusquedas;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
        
    public void buscar(){
      PlantasBusquedas =  dao.buscarPlanta(mes);
    }
   
    public void onTabChange(TabChangeEvent event) {
        FacesMessage msg = new FacesMessage("Tab Changed", "Active Tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
         
    public void onTabClose(TabCloseEvent event) {
        FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: " + event.getTab().getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
