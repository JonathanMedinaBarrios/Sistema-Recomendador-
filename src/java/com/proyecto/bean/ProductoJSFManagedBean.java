/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyecto.bean;

import com.proyecto.dao.DaoProducto;
import com.proyecto.impl.DaoProductoImpl;
import com.proyecto.POJOS.Producto;
import com.proyecto.util.UtilJSF;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.component.fileupload.FileUpload;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author FAMILIA
 */
@Named(value = "productoJBean")
@ViewScoped
public class ProductoJSFManagedBean implements Serializable {

    private Producto producto = new Producto();
    private DaoProducto dao = new DaoProductoImpl();
    private List<Producto> lista = new ArrayList<>();
    private String tipo ="Herbicidas"; 
    FacesContext context = FacesContext.getCurrentInstance();
    ViveroJSFManagedBean vivero = context.getApplication().evaluateExpressionGet(context, "#{viveroManagedBean}", ViveroJSFManagedBean.class);
    private UploadedFile imagen; 
    /**
     * Creates a new instance of ProductoJSFManagedBean
     */
    public ProductoJSFManagedBean() {
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UploadedFile getImagen() {
        return imagen;
    }

    public void setImagen(UploadedFile imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    /*LISTAR PRODUCTOS*/
    @PostConstruct
    public void iniciar() {
        lista = dao.ListarProducto(vivero.getVivero().getIdVivero(),tipo);
    }

    public List<Producto> getLista() {
        return lista;
    }

    public void setLista(List<Producto> lista) {
        this.lista = lista;
    }

    /*FIN LISTAR */
    public void save() {
        try { 
            producto.getVivero().setIdVivero(vivero.getVivero().getIdVivero());
            dao.save(producto);
            lista = dao.ListarProducto(vivero.getVivero().getIdVivero(),tipo);
            producto = new Producto(); 
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "aviso", "Registro exitoso..."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "aviso", "Registro fallido..."));
        }

    }

    public void update() {
        dao.Update(producto);
    }

    public void delete() {
        dao.Delete(producto);
        lista = dao.ListarProducto(vivero.getVivero().getIdVivero(),tipo);
    }
    
    
    public void listar(){
        lista = dao.ListarProducto(vivero.getVivero().getIdVivero(),tipo);
    }
    
    public void  guardarImagen(UploadedFile file) {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String realPath = UtilJSF.getUrlDefinida(ec.getRealPath("/"));
        String pathDefinition = realPath + File.separator + "web" + File.separator + "resources" 
                                + File.separator + "img"+ File.separator + "Huertos"+ File.separator + file.getFileName();
        try {
            FileInputStream in = (FileInputStream) file.getInputstream();
            FileOutputStream out = new FileOutputStream(pathDefinition);

            byte[] buffer = new byte[(int) file.getSize()];
            int contador = 0;

            while ((contador = in.read(buffer)) != -1) {
                out.write(buffer, 0, contador);
            }
            in.close();
            out.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }
    
    
    
}
