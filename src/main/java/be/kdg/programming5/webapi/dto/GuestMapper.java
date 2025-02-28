package be.kdg.programming5.webapi.dto;

import be.kdg.programming5.domain.Guest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GuestMapper {
    GuestDto toGuestDto(Guest guest);
    Guest toGuestEntity(GuestDto guestDto);
}
