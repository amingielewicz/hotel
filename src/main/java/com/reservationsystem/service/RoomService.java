package com.reservationsystem.service;

import com.reservationsystem.dto.Employee;
import com.reservationsystem.dto.Room;

import java.util.ArrayList;
import java.util.List;
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

    public boolean delete(int id) {
        int index = getIndexListById(id);
        boolean idIsOnList;
        if (index != id) {
            roomList.remove(index);
            idIsOnList = true;
            System.out.println("Usunieto pokój o id: " + id);
        }else {
            System.err.println("Brak pokoju o podanym id: " + id);
            idIsOnList = false;
        }
        return idIsOnList;
    }

    private int getIndexListById(int id) {
        int index = 0;
        if (roomList != null) {
            for (Room room : roomList) {
                if (room.getId() == id){
                    index = roomList.indexOf(room);
                    return index;
                }
            }
        }else {
            System.err.println("Brak pokojów na liscie");
            return id;
        }
        return id;
    }
}

