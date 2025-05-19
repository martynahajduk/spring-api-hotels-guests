package be.kdg.programming5.webapi.dto;

import be.kdg.programming5.domain.GuestRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface BookingMapper {

    @Mapping(target = "guestId", source = "guest.guestId")
    @Mapping(target = "roomNumber", source = "room.roomNumber")
    @Mapping(source = "guest.name", target = "guestName")
    @Mapping(target = "hotelId", source = "room.hotel.hotelId")
    GuestRoomDto toDto(GuestRoom guestRoom);

    @Mapping(source = "guestId", target = "guest.guestId")
    GuestRoom toEntity(GuestRoomDto dto);
}

