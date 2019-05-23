/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import java.io.Serializable;
import java.util.List;


/**
 * @author marcoisaac
 */
public class Subarea implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idsubarea;

    private String nombre;

    public Subarea() {
    }

    public Subarea(Integer idsubarea) {
        this.idsubarea = idsubarea;
    }

    public Integer getIdsubarea() {
        return idsubarea;
    }

    public void setIdsubarea(Integer idsubarea) {
        this.idsubarea = idsubarea;
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
        hash += (idsubarea != null ? idsubarea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subarea)) {
            return false;
        }
        Subarea other = (Subarea) object;
        if ((this.idsubarea == null && other.idsubarea != null) || (this.idsubarea != null && !this.idsubarea.equals(other.idsubarea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombre;
    }

}
