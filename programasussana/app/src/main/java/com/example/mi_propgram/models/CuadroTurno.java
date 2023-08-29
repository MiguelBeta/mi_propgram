package com.example.mi_propgram.models;

public class CuadroTurno {
    String semana;
    String horario_turno;
    String lunes;
    String martes;
    String miercoles;
    String jueves;
    String viernes;
    String sabado;
    String domingo;


    public CuadroTurno(){

    }

    public CuadroTurno(String semana, String horario_turno, String lunes, String martes, String miercoles, String jueves, String viernes, String sabado, String domingo) {
        this.semana = semana;
        this.horario_turno = horario_turno;
        this.lunes = lunes;
        this.martes = martes;
        this.miercoles = miercoles;
        this.jueves = jueves;
        this.viernes = viernes;
        this.sabado = sabado;
        this.domingo = domingo;
    }

    public String getSemana() {
        return semana;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }

    public String getHorario_turno() {
        return horario_turno;
    }

    public void setHorario_turno(String horario_turno) {
        this.horario_turno = horario_turno;
    }

    public String getLunes() {
        return lunes;
    }

    public void setLunes(String lunes) {
        this.lunes = lunes;
    }

    public String getMartes() {
        return martes;
    }

    public void setMartes(String martes) {
        this.martes = martes;
    }

    public String getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(String miercoles) {
        this.miercoles = miercoles;
    }

    public String getJueves() {
        return jueves;
    }

    public void setJueves(String jueves) {
        this.jueves = jueves;
    }

    public String getViernes() {
        return viernes;
    }

    public void setViernes(String viernes) {
        this.viernes = viernes;
    }

    public String getSabado() {
        return sabado;
    }

    public void setSabado(String sabado) {
        this.sabado = sabado;
    }

    public String getDomingo() {
        return domingo;
    }

    public void setDomingo(String domingo) {
        this.domingo = domingo;
    }

    @Override
    public String toString() {
        return "CuadroTurno{" +
                "semana='" + semana + '\'' +
                ", horario_turno='" + horario_turno + '\'' +
                ", lunes='" + lunes + '\'' +
                ", martes='" + martes + '\'' +
                ", miercoles='" + miercoles + '\'' +
                ", jueves='" + jueves + '\'' +
                ", viernes='" + viernes + '\'' +
                ", sabado='" + sabado + '\'' +
                ", domingo='" + domingo + '\'' +
                '}';
    }
}
