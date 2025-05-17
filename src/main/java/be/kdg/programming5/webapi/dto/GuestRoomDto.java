package be.kdg.programming5.webapi.dto;

import java.util.UUID;

public record GuestRoomDto(
        Integer id,
        UUID guestId,
        int roomNumber,
        UUID hotelId,
        String guestName
        ) {}
