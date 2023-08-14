package com.example.mi_propgram;

public class DatosUsuario {

    String claseid, nombre, cedula,  telefono, direccion, contrasena, fechaNacimiento, roles, genero;


    public DatosUsuario() {
    }

    public DatosUsuario(String claseid, String nombre, String cedula, String telefono,
                        String direccion, String contrasena, String fechaNacimiento,
                        String roles, String genero) {

        this.claseid = claseid;
        this.nombre = nombre;
        this.cedula = cedula;
        this.telefono = telefono;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.roles = roles;
        this.genero = genero;
    }

    public String getClaseid() {
        return claseid;
    }

    public void setClaseid(String claseid) {
        this.claseid = claseid;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "DatosUsuario{" +
                "claseid='" + claseid + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cedula='" + cedula + '\'' +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", roles='" + roles + '\'' +
                ", genero='" + genero + '\'' +

                '}';
    }
}
