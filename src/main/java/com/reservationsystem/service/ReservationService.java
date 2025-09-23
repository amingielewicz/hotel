package com.reservationsystem.service;

import com.reservationsystem.dto.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ReservationService implements ReservationServiceInterface {
    private List<Reservation> reservationList;

    public int create(Reservation reservation) {
        if (reservationList != null) {
            reservationList.add(reservation);
        } else {
            reservationList = new ArrayList<>();
            reservationList.add(reservation);
        }
        int id = reservation.getId();
        Reservation.counterId++;
        return reservation.getId();
    }

    public List<Reservation> findAll() {
        return reservationList;
    }

    public Reservation getReservation(int id) {
        for (Reservation reservation : reservationList) {
            if (reservation.getId() == id) {
                return reservation;
            }
        }
        return null;
    }

    public void update(Reservation updateReservation) {
            for (Reservation reservation : reservationList) {
                if (reservation.getId() == updateReservation.getId()) {
                    reservation.setCustomerId(updateReservation.getCustomerId());
                    reservation.setStartReservationDate(updateReservation.getStartReservationDate());
                    reservation.setEndReservationDate(updateReservation.getEndReservationDate());
                    reservation.setSum(updateReservation.getSum());
                    reservation.setDeposit(updateReservation.getDeposit());
                    reservation.setFullPaid(updateReservation.getFullPaid());
                    reservation.setEmployerId(updateReservation.getEmployerId());
                }
        }

    }

    public boolean checkId(int id) {
        AtomicBoolean isId = new AtomicBoolean(false);
        reservationList.forEach(reservation -> {
            if (reservation.getId() == id) {
                isId.set(true);
            }
        });
        if (isId.get()) {
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        int index = getIndexListById(id);
        boolean idIsOnList;
        if (index != id) {
            reservationList.remove(index);
            idIsOnList = true;
            System.out.println("Usunięto rezerwację o id: " + id);
        }else {
            System.err.println("Brak rezerwacji o podanym id: " + id);
            idIsOnList = false;
        }
        return idIsOnList;
    }

    private int getIndexListById(int id) {
        int index = 0;
        if (reservationList != null) {
            for (Reservation reservation : reservationList) {
                if (reservation.getId() == id){
                    index = reservationList.indexOf(reservation);
                    return index;
                }
            }
        }else {
            System.err.println("Brak rezerwacji na liscie");
            return id;
        }
        return id;
    }
}
