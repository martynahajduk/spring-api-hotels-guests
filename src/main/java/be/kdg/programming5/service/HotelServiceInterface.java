package be.kdg.programming5.service;

import be.kdg.programming5.domain.Hotel;

import java.util.List;
import java.util.UUID;

public interface HotelServiceInterface {
    List<Hotel> getAllHotels();
    Hotel addHotel(String name, String city);
    List<Hotel> filterHotelsByName(String nameFilter);
    Hotel findHotelById(UUID id);
    void deleteHotel(UUID id);
}
