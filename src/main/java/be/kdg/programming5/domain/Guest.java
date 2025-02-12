package be.kdg.programming5.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "guests")
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "guest_id")
    private UUID guestId;

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality")
    private Nationality nationality;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuestRoom> guestRooms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;


    public UUID getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public List<GuestRoom> getGuestRooms() {
        return guestRooms;
    }

    public void setGuestRooms(List<GuestRoom> booking) {
        this.guestRooms = booking;
    }


    public String roomListString() {
        return guestRooms == null ? "" :
                guestRooms.stream()
                        .map(guestRoom -> guestRoom.getRoom().getRoomNumber())
                        .map(String::valueOf)
                        .collect(Collectors.joining(", "));
    }

    public String roomTypeListString() {
        return guestRooms == null ? "" :
                guestRooms.stream()
                        .map(guestRoom -> guestRoom.getRoom().getRoomType().toString())
                        .collect(Collectors.joining(", "));
    }

}
