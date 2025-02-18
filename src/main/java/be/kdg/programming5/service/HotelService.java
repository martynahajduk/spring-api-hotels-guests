package be.kdg.programming5.service;

import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.Hotel;
import be.kdg.programming5.domain.Room;
import be.kdg.programming5.repository.GuestRoomRepository;
import be.kdg.programming5.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelService implements HotelServiceInterface {
    private final HotelRepository hotelRepository;
    private final GuestRoomRepository guestRoomRepository;

    @Autowired
    public HotelService(HotelRepository hotelRepository, GuestRoomRepository guestRoomRepository) {
        this.hotelRepository = hotelRepository;
        this.guestRoomRepository = guestRoomRepository;
    }

    @Transactional
    @Override
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    @Override
    @Transactional
    public Hotel addHotel(String name, String location) {
            Hotel hotel = new Hotel();
            hotel.setName(name);
            hotel.setLocation(location);

            return hotelRepository.save(hotel);
        }

    @Transactional
    @Override
    public List<Hotel> filterHotelsByName(String nameFilter) {
        if (nameFilter == null || nameFilter.isBlank()) {
            return hotelRepository.findAll();
        }
        return hotelRepository.findByNameContainingIgnoreCase(nameFilter);
    }


    @Transactional
    @Override
    public Hotel findHotelById(UUID id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));
    }

    @Override
    @Transactional
    public void deleteHotel(UUID hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new IllegalArgumentException("Hotel not found"));

        List<GuestRoom> guestRooms = hotel.getRooms().stream()
                .flatMap(room -> room.getGuestRooms().stream())
                .collect(Collectors.toList());
        guestRoomRepository.deleteAll(guestRooms);
        hotelRepository.deleteById(hotelId);
    }
}
