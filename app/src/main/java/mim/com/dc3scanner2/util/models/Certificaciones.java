/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author marcoisaac
 */
public class Certificaciones implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idcertificaciones;

    private String nombre;


    public Certificaciones() {
    }

    public Certificaciones(Integer idcertificaciones) {
        this.idcertificaciones = idcertificaciones;
    }

    public Integer getIdcertificaciones() {
        return idcertificaciones;
    }

    public void setIdcertificaciones(Integer idcertificaciones) {
        this.idcertificaciones = idcertificaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcertificaciones != null ? idcertificaciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificaciones)) {
            return false;
        }
        Certificaciones other = (Certificaciones) object;
        if ((this.idcertificaciones == null && other.idcertificaciones != null) || (this.idcertificaciones != null && !this.idcertificaciones.equals(other.idcertificaciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Certificaciones[ idcertificaciones=" + idcertificaciones + " ]";
    }
    
}
