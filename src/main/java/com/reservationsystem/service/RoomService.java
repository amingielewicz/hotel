package com.reservationsystem.service;

import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Room;
import com.reservationsystem.exception.DuplicateRoomNumber;
import com.reservationsystem.exception.EmptyListException;
import com.reservationsystem.exception.WrongNumberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoomService implements RoomServiceInterface {
    private List<Room> roomList;

    public int create(Room room) {
        if (roomList != null) {
            roomList.add(room);
        } else {
            roomList = new ArrayList<>();
            roomList.add(room);
        }
        int id = room.getId();
        Room.counterId++;
        return room.getId();
    }

    public List<Room> findAll() {
        return roomList;
    }

    public Room getRoom(int id) {
        for (Room room : roomList) {
            if (room.getId() == id) {
                return room;
            }
        }
        return null;
    }


    public void update(Room updateRoom) {
        roomList.forEach(room -> {
                    if (room.getId() == updateRoom.getId()) {
                        room.setRoomNumber(updateRoom.getRoomNumber());
                        room.setRoomSize(updateRoom.getRoomSize());
                        room.setEquipment(updateRoom.getEquipment());
                        room.setPrice(updateRoom.getPrice());
                    }
                }
        );
    }

    public Room getByRoomNumber(int number) {
        for (Room room : roomList) {
            if (room.getRoomNumber() == number) {
                return room;
            } else {
                throw new WrongNumberException("Podany numer pokoju nie istnieje.");
            }
        }
        return null;
    }

    public boolean checkId(int id) {
        AtomicBoolean isId = new AtomicBoolean(false);
        roomList.forEach(room -> {
            if (room.getId() == id) {
                isId.set(true);
            }
        });
        if (isId.get()) {
            return true;
        }
        return false;
    }

    public boolean delete(int roomNumber) {
        int index = getRoomByNumber(roomNumber);
        boolean idIsOnList;
        if (index != roomNumber) {
            roomList.remove(index);
            idIsOnList = true;
            System.out.println("Usunięto pokój o numerze: " + roomNumber);
        }else {
            System.err.println("Brak pokoju o podanym numerze: " + roomNumber);
            idIsOnList = false;
        }
        return idIsOnList;
    }

    private int getRoomByNumber(int roomNumber) {
        int index = 0;
        if (roomList != null) {
            for (Room room : roomList) {
                if (room.getRoomNumber() == roomNumber){
                    index = roomList.indexOf(room);
                    return index;
                }
            }
        }else {
            System.err.println("Brak pokojów na liście");
            return roomNumber;
        }
        return roomNumber;
    }

    public void validateRoomNumber(int roomNumber) {
        if(roomList != null)
            for (Room room : roomList) {
                if (roomNumber == room.getRoomNumber()) {
                    throw new DuplicateRoomNumber("Pokój o takim numerze już istnieje.");
                }
            }
    }



}

