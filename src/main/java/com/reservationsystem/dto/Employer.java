package com.reservationsystem.dto;

import com.reservationsystem.dto.Common;

public class Employer extends Common {

    private int id =0;
    private String role;
    private String personalSkill;

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getPersonalSkill() {
        return personalSkill;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", personalSkill='" + personalSkill + '\'' +
                '}';
    }

    public Employer(int id, String name, String surname, String role, String personalSkill) {
        super(name, surname);
        this.id = id;
        this.role = role;
        this.personalSkill = personalSkill;
    }

}
