package com.example.mi_propgram.models;

public class UsuarioModel {
    String idUser;
    String nombre;
    String cedula;
    String rol;
    String telefono;
    String coreo;
    String contrasena;

    public UsuarioModel() {
    }

    public UsuarioModel(String idUser, String nombre, String cedula, String rol, String telefono, String coreo, String contrasena) {
        this.idUser = idUser;
        this.nombre = nombre;
        this.cedula = cedula;
        this.rol = rol;
        this.telefono = telefono;
        this.coreo = coreo;
        this.contrasena = contrasena;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCoreo() {
        return coreo;
    }

    public void setCoreo(String coreo) {
        this.coreo = coreo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return "UsuarioModel{" +
                "idUser='" + idUser + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", rol='" + rol + '\'' +
                ", telefono='" + telefono + '\'' +
                ", contrasena='" + contrasena + '\'' +
                '}';
    }
}
