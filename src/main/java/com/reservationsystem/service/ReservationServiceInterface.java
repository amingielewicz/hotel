package com.reservationsystem.service;

import com.reservationsystem.dto.Reservation;

import java.util.List;

public interface ReservationServiceInterface {
    int create(Reservation reservation);
    List<Reservation> findAll();
    Reservation getReservation(int id);
    void update(Reservation updateReservation);
    boolean checkId(int id);
    boolean delete(int id);
}