package com.example.mi_propgram.models;

import java.util.List;

public class GuardSchedule {
    private String guardName;
    private int dayNumber; // Agrega este campo
    private String day; // Mantén este campo para el nombre del día
    private String shift;

    public GuardSchedule(){

    }
    public GuardSchedule(String guardName, int dayNumber, String day, String shift) {
        this.guardName = guardName;
        this.dayNumber = dayNumber;
        this.day = day;
        this.shift = shift;
    }

    public String getGuardName() {
        return guardName;
    }

    public void setGuardName(String guardName) {
        this.guardName = guardName;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
