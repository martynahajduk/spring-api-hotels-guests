package be.kdg.programming5.service;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.Nationality;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface GuestServiceInterface {
    Guest addGuest(String name, LocalDate birthDate, Nationality nationality, String hotelName, int roomNumber);

    List<Guest> getAllGuests();

    List<Guest> filterGuestsByNationalityAndBirthDate(Nationality nationality, LocalDate birthDate);

    Guest findGuestById(UUID id);

    boolean deleteGuest(UUID guestId);

}
