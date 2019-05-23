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
 *
 * @author marcoisaac
 */
public class Usuario implements Parcelable {

    private static final long serialVersionUID = 1L;
    private Integer idusuario;
    private String nombre;
    private String usuario;
    private String contrasena;
    private String afiliacion;
    private TipoUsuario tipoUsuarioIdtipoUsuario;
    private List<PermisoTrabajo> permisoTrabajoList;

    public Usuario() {
    }

    public Usuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Usuario(Integer idusuario, String nombre) {
        this.idusuario = idusuario;
        this.nombre = nombre;
    }

    protected Usuario(Parcel in) {
        if (in.readByte() == 0) {
            idusuario = null;
        } else {
            idusuario = in.readInt();
        }
        nombre = in.readString();
        usuario = in.readString();
        contrasena = in.readString();
        afiliacion = in.readString();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    public Integer getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getAfiliacion() {
        return afiliacion;
    }

    public void setAfiliacion(String afiliacion) {
        this.afiliacion = afiliacion;
    }

    public TipoUsuario getTipoUsuarioIdtipoUsuario() {
        return tipoUsuarioIdtipoUsuario;
    }

    public void setTipoUsuarioIdtipoUsuario(TipoUsuario tipoUsuarioIdtipoUsuario) {
        this.tipoUsuarioIdtipoUsuario = tipoUsuarioIdtipoUsuario;
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
        hash += (idusuario != null ? idusuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.idusuario == null && other.idusuario != null) || (this.idusuario != null && !this.idusuario.equals(other.idusuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mim.entities.Usuario[ idusuario=" + idusuario + " ]";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (idusuario == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(idusuario);
        }
        parcel.writeString(nombre);
        parcel.writeString(usuario);
        parcel.writeString(contrasena);
        parcel.writeString(afiliacion);
    }
}
