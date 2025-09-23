package com.reservationsystem.service;

import com.reservationsystem.dto.Room;

import java.util.List;

public interface RoomServiceInterface {
    int create(Room room);
    List<Room> findAll();
    Room getRoom(int roomNumber);
    void update(Room updateRoom);
    boolean checkId(int id);
    boolean delete(int roomNumber);
    void validateRoomNumber(int roomNumber);
}
