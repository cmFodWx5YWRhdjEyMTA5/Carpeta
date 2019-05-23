/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author marcoisaac
 */
public class PermisoTrabajo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idpermisoTrabajo;

    private String descripcion;

    private Area areaIdarea;

    private Subarea subareaIdsubarea;

    private TipoActividad tipoActividadIdtipoActividad;

    private Trabajador trabajadorIdtrabajador;

    private Usuario usuarioIdusuario;


    private Date inicio;

    private Date cierre;

    private String posicion;

    private String riesgo;

    private String comentario;

    private String comentario2;

    private Integer mapa;


    public PermisoTrabajo() {
    }

    public PermisoTrabajo(Integer idpermisoTrabajo) {
        this.idpermisoTrabajo = idpermisoTrabajo;
    }

    public Integer getIdpermisoTrabajo() {
        return idpermisoTrabajo;
    }

    public void setIdpermisoTrabajo(Integer idpermisoTrabajo) {
        this.idpermisoTrabajo = idpermisoTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public Area getAreaIdarea() {
        return areaIdarea;
    }

    public void setAreaIdarea(Area areaIdarea) {
        this.areaIdarea = areaIdarea;
    }

    public Subarea getSubareaIdsubarea() {
        return subareaIdsubarea;
    }

    public void setSubareaIdsubarea(Subarea subareaIdsubarea) {
        this.subareaIdsubarea = subareaIdsubarea;
    }

    public TipoActividad getTipoActividadIdtipoActividad() {
        return tipoActividadIdtipoActividad;
    }

    public void setTipoActividadIdtipoActividad(TipoActividad tipoActividadIdtipoActividad) {
        this.tipoActividadIdtipoActividad = tipoActividadIdtipoActividad;
    }

    public Trabajador getTrabajadorIdtrabajador() {
        return trabajadorIdtrabajador;
    }

    public void setTrabajadorIdtrabajador(Trabajador trabajadorIdtrabajador) {
        this.trabajadorIdtrabajador = trabajadorIdtrabajador;
    }

    public Usuario getUsuarioIdusuario() {
        return usuarioIdusuario;
    }

    public void setUsuarioIdusuario(Usuario usuarioIdusuario) {
        this.usuarioIdusuario = usuarioIdusuario;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getCierre() {
        return cierre;
    }

    public void setCierre(Date cierre) {
        this.cierre = cierre;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getRiesgo() {
        return riesgo;
    }

    public void setRiesgo(String riesgo) {
        this.riesgo = riesgo;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario2() {
        return comentario2;
    }

    public void setComentario2(String comentario2) {
        this.comentario2 = comentario2;
    }

    public Integer getMapa() {
        return mapa;
    }

    public void setMapa(Integer mapa) {
        this.mapa = mapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpermisoTrabajo != null ? idpermisoTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PermisoTrabajo)) {
            return false;
        }
        PermisoTrabajo other = (PermisoTrabajo) object;
        if ((this.idpermisoTrabajo == null && other.idpermisoTrabajo != null) || (this.idpermisoTrabajo != null && !this.idpermisoTrabajo.equals(other.idpermisoTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.PermisoTrabajo[ idpermisoTrabajo=" + idpermisoTrabajo + " ]";
    }

}
