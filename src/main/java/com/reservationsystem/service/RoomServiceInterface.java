package com.reservationsystem.service;

import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Reservation;
import com.reservationsystem.dto.Room;

import java.math.BigDecimal;
import java.util.List;

public interface RoomServiceInterface {
    int create(Room room);
    List<Room> findAll();
    Room getRoom(int id);
    void update(Room updateRoom);
    boolean checkId(int id);
    Boolean delete(int id);
}
