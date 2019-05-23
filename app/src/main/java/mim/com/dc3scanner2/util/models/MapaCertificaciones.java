/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author marcoisaac
 */
public class MapaCertificaciones implements Parcelable {

    private static final long serialVersionUID = 1L;



    private Integer duracion;

    private Date fechaEjecucion;

    private String areaTematica;
    private String capacitador;

    private String registro;
    private String tipo;

    private Certificaciones certificaciones;


    private Trabajador trabajadorIdtrabajador;

    public MapaCertificaciones() {
    }


    protected MapaCertificaciones(Parcel in) {
        if (in.readByte() == 0) {
            duracion = null;
        } else {
            duracion = in.readInt();
        }
        areaTematica = in.readString();
        capacitador = in.readString();
        registro = in.readString();
        trabajadorIdtrabajador = in.readParcelable(Trabajador.class.getClassLoader());
    }

    public static final Creator<MapaCertificaciones> CREATOR = new Creator<MapaCertificaciones>() {
        @Override
        public MapaCertificaciones createFromParcel(Parcel in) {
            return new MapaCertificaciones(in);
        }

        @Override
        public MapaCertificaciones[] newArray(int size) {
            return new MapaCertificaciones[size];
        }
    };

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Date getFechaEjecucion() {
        return fechaEjecucion;
    }

    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public String getCapacitador() {
        return capacitador;
    }

    public void setCapacitador(String capacitador) {
        this.capacitador = capacitador;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Certificaciones getCertificaciones() {
        return certificaciones;
    }

    public void setCertificaciones(Certificaciones certificaciones) {
        this.certificaciones = certificaciones;
    }


    public Trabajador getTrabajadorIdtrabajador() {
        return trabajadorIdtrabajador;
    }

    public void setTrabajadorIdtrabajador(Trabajador trabajadorIdtrabajador) {
        this.trabajadorIdtrabajador = trabajadorIdtrabajador;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (duracion == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(duracion);
        }
        parcel.writeString(areaTematica);
        parcel.writeString(capacitador);
        parcel.writeString(registro);
        parcel.writeParcelable(trabajadorIdtrabajador, i);
    }
}
