package com.proyecto.POJOS;
// Generated 10-sep-2018 14:00:11 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name="usuario"
    ,schema="public"
)
public class Usuario  implements java.io.Serializable {

    
     private int idUsuario;
     private String nombre;
     private String apellidos;
     private String direccion;
     private String cuidad;
     private String correo;
     private String contrasena;
     private Set<Huerto> huertos = new HashSet<Huerto>(0);

    public Usuario() {
    }

	
    public Usuario( String nombre, String apellidos, String correo, String contrasena) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasena = contrasena;
    }
    public Usuario(String nombre, String apellidos, String direccion, String cuidad, String correo, String contrasena, Set<Huerto> huertos) {
       this.nombre = nombre;
       this.apellidos = apellidos;
       this.direccion = direccion;
       this.cuidad = cuidad;
       this.correo = correo;
       this.contrasena = contrasena;
       this.huertos = huertos;
    }
   
    @Id 
    @Column(name="id_usuario", unique=true, nullable=false)
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    @Column(name="nombre", nullable=false, length=15)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="apellidos", nullable=false, length=15)
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    
    @Column(name="direccion", length=25)
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    @Column(name="cuidad", length=15)
    public String getCuidad() {
        return this.cuidad;
    }
    
    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }

    
    @Column(name="correo", nullable=false, length=50)
    public String getCorreo() {
        return this.correo;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    
    @Column(name="contrasena", nullable=false, length=25)
    public String getContrasena() {
        return this.contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="usuario")
    public Set<Huerto> getHuertos() {
        return this.huertos;
    }
    
    public void setHuertos(Set<Huerto> huertos) {
        this.huertos = huertos;
    }




}


