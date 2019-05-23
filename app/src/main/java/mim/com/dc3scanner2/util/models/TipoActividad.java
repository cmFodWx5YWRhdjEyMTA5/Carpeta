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
public class TipoActividad implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idtipoActividad;
    private String nombre;
    private List<PermisoTrabajo> permisoTrabajoList;

    public TipoActividad() {
    }

    public TipoActividad(String nombre) {
        this.nombre = nombre;
    }

    public TipoActividad(Integer idtipoActividad) {
        this.idtipoActividad = idtipoActividad;
    }

    public Integer getIdtipoActividad() {
        return idtipoActividad;
    }

    public void setIdtipoActividad(Integer idtipoActividad) {
        this.idtipoActividad = idtipoActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public List<PermisoTrabajo> getPermisoTrabajoList() {
        return permisoTrabajoList;
    }

    public void setPermisoTrabajoList(List<PermisoTrabajo> permisoTrabajoList) {
        this.permisoTrabajoList = permisoTrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoActividad != null ? idtipoActividad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoActividad)) {
            return false;
        }
        TipoActividad other = (TipoActividad) object;
        if ((this.idtipoActividad == null && other.idtipoActividad != null) || (this.idtipoActividad != null && !this.idtipoActividad.equals(other.idtipoActividad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.TipoActividad[ idtipoActividad=" + idtipoActividad + " ]";
    }
    
}
