/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author marcoisaac
 */
public class Sanciones implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idsanciones;

    private String motivo;


    private String incumplimiento;

    private String sancion;


    private Date fechaSancion;

    private Trabajador trabajadorIdtrabajador;


    public Sanciones() {
    }

    public Sanciones(Integer idsanciones) {
        this.idsanciones = idsanciones;
    }

    public Integer getIdsanciones() {
        return idsanciones;
    }

    public void setIdsanciones(Integer idsanciones) {
        this.idsanciones = idsanciones;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Date getFechaSancion() {
        return fechaSancion;
    }

    public void setFechaSancion(Date fechaSancion) {
        this.fechaSancion = fechaSancion;
    }

    public String getIncumplimiento() {
        return incumplimiento;
    }

    public void setIncumplimiento(String incumplimiento) {
        this.incumplimiento = incumplimiento;
    }

    public String getSancion() {
        return sancion;
    }

    public void setSancion(String sancion) {
        this.sancion = sancion;
    }

    public Trabajador getTrabajadorIdtrabajador() {
        return trabajadorIdtrabajador;
    }

    public void setTrabajadorIdtrabajador(Trabajador trabajadorIdtrabajador) {
        this.trabajadorIdtrabajador = trabajadorIdtrabajador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsanciones != null ? idsanciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sanciones)) {
            return false;
        }
        Sanciones other = (Sanciones) object;
        if ((this.idsanciones == null && other.idsanciones != null) || (this.idsanciones != null && !this.idsanciones.equals(other.idsanciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Sanciones[ idsanciones=" + idsanciones + " ]";
    }
    
}
