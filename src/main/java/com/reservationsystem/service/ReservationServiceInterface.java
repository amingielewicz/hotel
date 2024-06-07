package com.reservationsystem.service;

import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Reservation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ReservationServiceInterface {
    int create(Reservation reservation);
    List<Reservation> findAll();
    Reservation getReservation(int id);
    void update(Reservation updateReservation);
    boolean checkId(int id);
    Boolean delete(int id);
    BigDecimal sum(int roomId, LocalDate startDateReservation, LocalDate endDateReservation);
}
