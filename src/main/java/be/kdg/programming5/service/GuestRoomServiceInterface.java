package be.kdg.programming5.service;

import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.User;
import be.kdg.programming5.webapi.dto.GuestRoomDto;

import java.util.List;

public interface GuestRoomServiceInterface {
    List<GuestRoom> getAll();
    GuestRoom add(GuestRoomDto dto, User owner);
    GuestRoom update(Integer id, GuestRoomDto dto);
    void delete(Integer id);
    GuestRoom getById(Integer id);
    List<GuestRoomDto> searchByGuestName(String guestName);
}
