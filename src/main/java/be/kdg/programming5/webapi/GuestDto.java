package be.kdg.programming5.webapi;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.Nationality;

import java.time.LocalDate;

public record GuestDto(String name, LocalDate dateOfBirth, Nationality nationality, String hotelName, int roomNumber) {
    public static GuestDto fromGuest(final Guest guest) {
        guest.getGuestRooms().size();
        Integer roomNumber = guest.getGuestRooms().stream()
                .findFirst()
                .map(guestRoom -> guestRoom.getRoom().getRoomNumber())
                .orElse(0);
        return new GuestDto(
                guest.getName(),
                guest.getDateOfBirth(),
                guest.getNationality(),
                guest.getHotel().getName(),
                roomNumber
        );
    }

}