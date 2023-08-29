package com.example.mi_propgram.models;

public class InfoGuarda {
    String name;
    String surname;
    String identification;
    String phone;
    String email;

    public InfoGuarda() {
    }

    public InfoGuarda(String name, String surname, String identification, String phone, String email) {
        this.name = name;
        this.surname = surname;
        this.identification = identification;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "InfoGuarda{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", identification='" + identification + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
