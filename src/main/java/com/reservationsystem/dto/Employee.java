package com.reservationsystem.dto;

public class Employee extends Common {

    public static int counterId = 1;
    private int id = counterId;
    private String role;
    private String personalSkill;

    public Employee(String name, String surname, String role, String personalSkill) {
        super(name, surname);
        this.role = role;
        this.personalSkill = personalSkill;
    }

    public Employee(String name, String surname, int id, String role, String personalSkill) {
        super(name, surname);
        this.id = id;
        this.role = role;
        this.personalSkill = personalSkill;
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }
    public String getRole() {
        return role;
    }
    public String getPersonalSkill() {
        return personalSkill;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public void setPersonalSkill(String personalSkill) {
        this.personalSkill = personalSkill;
    }

    @Override
    public String toString() {
            return "ID: " + getId() + " " +
                    "Imię: " + super.getName() + " " +
                    "Nazwisko: " + super.getSurname() + " " +
                    "stanowisko: " + getRole() + " " +
                    "umiejętności: " + getPersonalSkill();
        }


    public Employee(int id, String name, String surname, String role, String personalSkill) {
        super(name, surname);
        this.id = id;
        this.role = role;
        this.personalSkill = personalSkill;
    }

}
