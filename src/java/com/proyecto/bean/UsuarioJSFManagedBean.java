/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.dao.DaoUsuario;
import com.proyecto.impl.DaoUsuarioImpl;
import com.proyecto.POJOS.Usuario;
import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author FAMILIA
 */
@Named(value = "usuarioManagedBean")
@ApplicationScoped
public class UsuarioJSFManagedBean implements Serializable {
    
    private String username;
    private String password;
    private Usuario usuario = new Usuario();
    private DaoUsuario dao = new DaoUsuarioImpl();

    /**
     * Creates a new instance of UsuarioJSFManagedBean
     */
    @PostConstruct
    public void iniciar() {
        
    }

    public UsuarioJSFManagedBean() {

    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
    
    public String registrarUsuario() {

        if(!dao.ConsultaUsuario(usuario.getCorreo())){
            if(this.getPassword().equals(this.getUsuario().getContrasena())) {
            dao.Save(usuario);

            return "index";
        } else {
            FacesMessage msg = new  FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "No coiciden contraseñas!"); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
            usuario.setContrasena("");
            password = ""; 
            return "Registro";
        }
        }else{ 
            FacesMessage msg = new  FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "El correo se encuentra registrado...!"); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
               
                            
            return "Registro";
        }
        
    }
    
    public void  completarPerfil(){
       dao.CompletarPerfil(usuario); 
    }
    
    public String login() {

        usuario = (Usuario) dao.login(username, password);
        if (usuario != null){
            return "/ViewsUsuario/PerfilUsuario.xhtml";
        } else {
            FacesMessage msg = new  FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Login!", ""); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "index";
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }
}
