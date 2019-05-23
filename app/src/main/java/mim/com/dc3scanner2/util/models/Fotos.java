/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import java.io.Serializable;


/**
 *
 * @author marcoisaac
 */
public class Fotos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idfotos;
    private String titulo;
    private String descripcion;
    private String archivo;
    private PermisoTrabajo permisoTrabajoIdpermisoTrabajo;

    public Fotos() {
    }

    public Fotos(Integer idfotos) {
        this.idfotos = idfotos;
    }

    public Integer getIdfotos() {
        return idfotos;
    }

    public void setIdfotos(Integer idfotos) {
        this.idfotos = idfotos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public PermisoTrabajo getPermisoTrabajoIdpermisoTrabajo() {
        return permisoTrabajoIdpermisoTrabajo;
    }

    public void setPermisoTrabajoIdpermisoTrabajo(PermisoTrabajo permisoTrabajoIdpermisoTrabajo) {
        this.permisoTrabajoIdpermisoTrabajo = permisoTrabajoIdpermisoTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfotos != null ? idfotos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fotos)) {
            return false;
        }
        Fotos other = (Fotos) object;
        if ((this.idfotos == null && other.idfotos != null) || (this.idfotos != null && !this.idfotos.equals(other.idfotos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Fotos[ idfotos=" + idfotos + " ]";
    }
    
}
