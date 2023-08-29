package com.example.mi_propgram.models;

public class GuardData {
    private String name;
    private int dayNumber;
    private String dayName; // Nuevo campo para el nombre del día
    private String shift;
    public GuardData() {
    }

    public GuardData(String name, int dayNumber, String dayName, String shift) {
        this.name = name;
        this.dayNumber = dayNumber;
        this.dayName = dayName;
        this.shift = shift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
