package com.reservationsystem.dto;

import java.math.BigDecimal;

public class Room {

    private int id = 1;
    private int roomNumber;
    private int roomSize;
    private String equipment;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public String getEquipment() {
        return equipment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber=" + roomNumber +
                ", roomSize=" + roomSize +
                ", equipment='" + equipment + '\'' +
                ", price=" + price +
                '}';
    }

    public Room(int roomNumber, int roomSize, String equipment, BigDecimal price) {

        this.roomNumber = roomNumber;
        this.roomSize = roomSize;
        this.equipment = equipment;
        this.price = price;
    }
    public static RoomBuilder builder(){
        return new RoomBuilder();
    }
    public static class RoomBuilder {

        private int roomNumber;
        private int roomSize;
        private String equipment;
        private BigDecimal price;


        public RoomBuilder roomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }
        public RoomBuilder roomSize(int roomSize) {
            this.roomSize = roomSize;
            return  this;
        }
        public RoomBuilder equipment(String equipment) {
            this.equipment = equipment;
            return this;
        }
        public  RoomBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Room build() {
            return new Room(roomNumber, roomSize, equipment, price);
        }

    }
}
