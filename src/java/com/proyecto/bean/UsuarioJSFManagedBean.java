/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.POJOS.Recomendacion;
import com.proyecto.dao.DaoUsuario;
import com.proyecto.impl.DaoUsuarioImpl;
import com.proyecto.POJOS.Usuario;
import com.proyecto.dao.DaoHistorialRecomendacion;
import com.proyecto.impl.DaoHistorialRecomendacionImpl;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author JONATHAN MEDINA BARRIOS
 */
@Named(value = "usuarioManagedBean")
@ApplicationScoped
public class UsuarioJSFManagedBean implements Serializable {
    
    private String username;
    private String password;
    private Usuario usuario = new Usuario();
    private DaoUsuario dao = new DaoUsuarioImpl();
    private DaoHistorialRecomendacion dao2 = new DaoHistorialRecomendacionImpl(); 
    private List<Recomendacion> listaRecomendacion  = new ArrayList<>();

    /**
     * Creates a new instance of UsuarioJSFManagedBean
     */
    @PostConstruct
    public void iniciar() {
        listaRecomendacion = dao2.listarRecomendacion(usuario.getIdUsuario());
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
            return "/faces/ViewsUsuario/PerfilUsuario.xhtml";
        } else {
            FacesMessage msg = new  FacesMessage(FacesMessage.SEVERITY_FATAL, "Invalid Login!", ""); 
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "index";
        }
    }

    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        password = ""; 
        return "/faces/InicioU.xhtml";
    }

    public List<Recomendacion> getListaRecomendacion() {
        return listaRecomendacion;
    }

    public void setListaRecomendacion(List<Recomendacion> listaRecomendacion) {
        this.listaRecomendacion = listaRecomendacion;
    }
    
}
