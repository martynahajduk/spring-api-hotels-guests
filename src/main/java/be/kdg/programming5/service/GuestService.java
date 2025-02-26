package be.kdg.programming5.service;

import be.kdg.programming5.domain.*;
import be.kdg.programming5.repository.GuestRepository;
import be.kdg.programming5.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class GuestService implements GuestServiceInterface {
    private final GuestRepository guestRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository, HotelRepository hotelRepository) {
        this.guestRepository = guestRepository;
        this.hotelRepository = hotelRepository;
    }

    @Transactional
    @Override
    public Guest addGuest(String name,
                          LocalDate birthDate,
                          Nationality nationality,
                          String hotelName,
                          List<Integer> roomNumber) {
        Hotel hotel = hotelRepository.findByNameContainingIgnoreCase(hotelName)
                .stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        Room room = hotel.getRooms()
                .stream()
                .filter(r -> roomNumber.contains(r.getRoomNumber()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        Guest guest = new Guest();
        guest.setName(name);
        guest.setDateOfBirth(birthDate);
        guest.setNationality(nationality);
        guest.setHotel(hotel);

        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setGuest(guest);
        guestRoom.setRoom(room);

        guest.getGuestRooms().add(guestRoom);

        return guestRepository.save(guest);
    }

    @Transactional
    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAllWithBookings();
    }

    @Transactional
    @Override
    public Guest findGuestById(UUID id) {
        return guestRepository.findByIdWithBookings(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));
    }

    @Transactional
    @Override
    public List<Guest> filterGuestsByNationalityAndBirthDate(Nationality nationality, LocalDate birthDate) {
        List<Guest> guests;
        if (nationality == null && birthDate == null) {
            guests = guestRepository.findAllWithBookings();
        } else {
            guests = guestRepository.filterByNationalityAndBirthDate(nationality, birthDate);
        }
        guests.forEach(guest -> guest.getGuestRooms().size());
        return guests;
    }


    @Override
    @Transactional
    public boolean deleteGuest(UUID guestId) {
        guestRepository.deleteById(guestId);
        return false;
    }


}
