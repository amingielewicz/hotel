package com.reservationsystem.dto;

import com.reservationsystem.dto.Common;

public class Customer extends Common {

public static int counterId = 1;
private int id = counterId;
private String pesel;

    public int getId() {
        return id;
    }
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    @Override
    public String toString() {
        return "ID: " + id + " " +
                "Imię: " + super.getName() + " " +
                "Nazwisko: " + super.getSurname() + " " +
                "PESEL: " + pesel;
    }

    public Customer(String name, String surname, String pesel) {
        super(name, surname);
        this.pesel = pesel;
    }

    public Customer(int id,String name, String surname, String pesel) {
        super(name, surname);
        this.id = id;
        this.pesel = pesel;
    }
}
