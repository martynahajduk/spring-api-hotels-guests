package be.kdg.programming5.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@IdClass(RoomId.class)
@Table(name = "rooms")
public class Room {
    @Id
    @Column(name = "room_number")
    private int roomNumber;

    @Column(name = "price")
    private double price;

    @Column(name = "floor")
    private int floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomType roomType;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<GuestRoom> guestRooms;

    public double getPrice() {
        return price;
    }

    public int getFloor() {
        return floor;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<GuestRoom> getGuestRooms() {
        return guestRooms;
    }

    public void setGuestRooms(List<GuestRoom> booking) {
        this.guestRooms = booking;
    }

}
