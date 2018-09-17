/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.dao.DaoVivero;
import com.proyecto.impl.DaoViveroImpl;
import com.proyecto.POJOS.Vivero;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author -
 */
@Named(value = "viveroManagedBean")
@SessionScoped
public class ViveroJSFManagedBean implements Serializable {

    private String username;
    private String password;
    private Vivero vivero = new Vivero();
    private DaoVivero dao = new DaoViveroImpl ();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Vivero getVivero() {
        return vivero;
    }

    public void setVivero(Vivero vivero) {
        this.vivero = vivero;
    }
    
    

    /**
     * Creates a new instance of ViveroJSFManagedBean
     */
    public ViveroJSFManagedBean() {
    }
    
    
    public String saveVivero(){
        
        if(!dao.ConsultaVivero(vivero.getCorreo())){
           if(this.getPassword().equals(this.getVivero().getContrasena())) {
            dao.save(vivero);
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Contraseñas no coinciden...!"));
            return "Registro";
        }
        }else{
           FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            "Contraseñas no coinciden...!"));
            return "Registro"; 
        }
    }
    
    public void actualizarPerfil(){
     dao.CompletarPerfil(vivero);
    }
    
    public String login() {

        vivero = (Vivero) dao.login(username, password);
        if (vivero != null){
            return "principalvivero";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Invalid Login!",
                            ""));
            return "index.xhtml";
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }

}
