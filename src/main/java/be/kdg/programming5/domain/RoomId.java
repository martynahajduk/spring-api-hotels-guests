package be.kdg.programming5.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class RoomId implements Serializable {
    private int roomNumber;
    private Hotel hotel;

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomId roomId = (RoomId) o;
        return Objects.equals(roomNumber, roomId.roomNumber) &&
                Objects.equals(hotel, roomId.hotel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber, hotel);
    }

}
