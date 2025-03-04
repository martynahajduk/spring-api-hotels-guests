package be.kdg.programming5.webapi.dto;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.Nationality;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record GuestDto(String name, LocalDate dateOfBirth, Nationality nationality, String hotelName, List<Integer> roomNumbers) {
//    public static GuestDto fromGuest(final Guest guest) {
//        guest.getGuestRooms().size();
//        List<Integer> roomNumbers = guest.getGuestRooms().stream()
//                .map(guestRoom -> guestRoom.getRoom().getRoomNumber())
//                .collect(Collectors.toList());
//        return new GuestDto(
//                guest.getName(),
//                guest.getDateOfBirth(),
//                guest.getNationality(),
//                guest.getHotel().getName(),
//                roomNumbers
//        );
//    }

}