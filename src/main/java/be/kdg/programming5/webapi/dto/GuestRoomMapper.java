package be.kdg.programming5.webapi.dto;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.Room;
import be.kdg.programming5.repository.GuestRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GuestRoomMapper {

    @Mapping(target = "guestId", source = "guest.guestId")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    @Mapping(source = "guest.name", target = "guestName")
    @Mapping(target = "hotelId", source = "room.hotel.hotelId")
    GuestRoomDto toDto(GuestRoom guestRoom);

    @Mapping(source = "guestId", target = "guest.guestId")
    GuestRoom toEntity(GuestRoomDto dto);
}
