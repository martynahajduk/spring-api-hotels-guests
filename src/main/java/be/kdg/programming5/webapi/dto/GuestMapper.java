package be.kdg.programming5.webapi.dto;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.GuestRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface GuestMapper {
    @Mapping(target = "roomNumbers", source = "guestRooms", qualifiedByName = "mapRoomNumbers")
    GuestDto toGuestDto(Guest guest);
    Guest toGuestEntity(GuestDto guestDto);

    @Named("mapRoomNumbers")
    default List<Integer> mapRoomNumbers(List<GuestRoom> guestRooms) {
        return guestRooms.stream()
                .map(guestRoom -> guestRoom.getRoom().getRoomNumber())
                .collect(Collectors.toList());
    }
}
