package com.reservationsystem.config;

import com.reservationsystem.dto.Customer;
import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Reservation;
import com.reservationsystem.dto.Room;

public class CheckList {
    private final Customer customer;
    private final Employee employee;
    private final Reservation reservation;
    private final Room room;

    public CheckList(Customer customer, Employee employee, Reservation reservation, Room room) {
        this.customer = customer;
        this.employee = employee;
        this.reservation = reservation;
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Room getRoom() {
        return room;
    }

    public static CheckListBuilder builder() {
        return new CheckListBuilder();
    }
public static class CheckListBuilder {
    private Customer customer;
    private Employee employee;
    private Reservation reservation;
    private Room room;

    public CheckListBuilder customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public CheckListBuilder employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public CheckListBuilder reservation(Reservation reservation) {
        this.reservation = reservation;
        return this;
    }

    public CheckListBuilder room(Room room) {
        this.room = room;
        return this;
    }

    public CheckList build() {
        return new CheckList(customer, employee, reservation, room);
    }
}
}
