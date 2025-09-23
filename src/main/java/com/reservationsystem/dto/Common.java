package com.reservationsystem.dto;

public class Common {

    private String name;
    private String surname;

    public Common(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Common() {
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

}
