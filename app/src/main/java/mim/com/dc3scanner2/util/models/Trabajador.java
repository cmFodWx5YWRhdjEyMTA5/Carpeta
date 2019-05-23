/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mim.com.dc3scanner2.util.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;


/**
 * @author marcoisaac
 */

public class Trabajador implements Serializable, Parcelable {

    private static final long serialVersionUID = 1L;

    private Integer idtrabajador;

    private String nombreCompleto;
    private String nss;
    private Integer edad;
    private String tel;
    private String codigo;
    private String imagen;
    private Empresa empresaIdempresa;
    private Area areaIdarea;
    private Puesto puestoIdpuesto;
    private List<Certificaciones> certificacionesList;
    private List<PermisoTrabajo> permisoTrabajoList;
    private List<MapaCertificaciones> mapaCertificacionesList;
    private String estatus;

    public Trabajador() {
    }

    public Trabajador(String nombreCompleto, String nss, String codigo, Integer edad, String tel) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.nss = nss;
        this.edad = edad;
        this.tel = tel;
    }

    public Trabajador(Integer idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    protected Trabajador(Parcel in) {
        if (in.readByte() == 0) {
            idtrabajador = null;
        } else {
            idtrabajador = in.readInt();
        }
        nombreCompleto = in.readString();
        nss = in.readString();
        if (in.readByte() == 0) {
            edad = null;
        } else {
            edad = in.readInt();
        }
        tel = in.readString();
        codigo = in.readString();
    }

    public static final Creator<Trabajador> CREATOR = new Creator<Trabajador>() {
        @Override
        public Trabajador createFromParcel(Parcel in) {
            return new Trabajador(in);
        }

        @Override
        public Trabajador[] newArray(int size) {
            return new Trabajador[size];
        }
    };

    public Integer getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(Integer idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNss() {
        return nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Empresa getEmpresaIdempresa() {
        return empresaIdempresa;
    }

    public void setEmpresaIdempresa(Empresa empresaIdempresa) {
        this.empresaIdempresa = empresaIdempresa;
    }

    public Puesto getPuestoIdpuesto() {
        return puestoIdpuesto;
    }

    public void setPuestoIdpuesto(Puesto puestoIdpuesto) {
        this.puestoIdpuesto = puestoIdpuesto;
    }


    public List<Certificaciones> getCertificacionesList() {
        return certificacionesList;
    }

    public void setCertificacionesList(List<Certificaciones> certificacionesList) {
        this.certificacionesList = certificacionesList;
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
        hash += (idtrabajador != null ? idtrabajador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.idtrabajador == null && other.idtrabajador != null) || (this.idtrabajador != null && !this.idtrabajador.equals(other.idtrabajador))) {
            return false;
        }
        return true;
    }

    public Area getAreaIdarea() {
        return areaIdarea;
    }

    public void setAreaIdarea(Area areaIdarea) {
        this.areaIdarea = areaIdarea;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Trabajador[ idtrabajador=" + idtrabajador + " ]";
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<MapaCertificaciones> getMapaCertificacionesList() {
        return mapaCertificacionesList;
    }

    public void setMapaCertificacionesList(List<MapaCertificaciones> mapaCertificacionesList) {
        this.mapaCertificacionesList = mapaCertificacionesList;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idtrabajador == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idtrabajador);
        }
        parcel.writeString(nombreCompleto);
        parcel.writeString(nss);
        if (edad == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(edad);
        }
        parcel.writeString(imagen);
        parcel.writeString(tel);
        parcel.writeString(codigo);
    }
}
