package be.kdg.programming5.service;


import be.kdg.programming5.domain.*;
import be.kdg.programming5.repository.GuestRoomRepository;
import be.kdg.programming5.repository.GuestRepository;
import be.kdg.programming5.repository.RoomRepository;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import be.kdg.programming5.webapi.dto.GuestRoomMapper;
import be.kdg.programming5.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GuestRoomServiceUnitTest {

    private GuestRoomService guestRoomService;

    private GuestRoomRepository guestRoomRepository;
    private GuestRepository guestRepository;
    private RoomRepository roomRepository;
//    private GuestRoomMapper guestRoomMapper;

    @BeforeEach
    void setUp() {
        guestRoomRepository = mock(GuestRoomRepository.class);
        guestRepository = mock(GuestRepository.class);
        roomRepository = mock(RoomRepository.class);

        guestRoomService = new GuestRoomService(guestRoomRepository, guestRepository, roomRepository);
    }

    @Test
    void shouldUpdateGuestRoomIfExists() {
        Integer id = 190;
        UUID guestId = UUID.randomUUID();
        UUID hotelId = UUID.randomUUID();

        Guest guest = new Guest(); guest.setGuestId(guestId);
        Room room = new Room(); room.setRoomNumber(101); room.setHotel(new Hotel()); room.getHotel().setHotelId(hotelId);

        GuestRoom existing = new GuestRoom();
        existing.setId(id);
        existing.setGuest(guest);
        existing.setRoom(room);

        GuestRoomDto dto = new GuestRoomDto(id, guestId, 101, hotelId);

        when(guestRoomRepository.findById(id)).thenReturn(Optional.of(existing));
        when(guestRepository.findById(guestId)).thenReturn(Optional.of(guest));
        when(roomRepository.findByHotel_HotelIdAndRoomNumber(hotelId, 101)).thenReturn(Optional.of(room));
        when(guestRoomRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        GuestRoom updated = guestRoomService.update(id, dto);

        assertEquals(guest, updated.getGuest());
        assertEquals(room, updated.getRoom());

        ArgumentCaptor<GuestRoom> captor = ArgumentCaptor.forClass(GuestRoom.class);
        verify(guestRoomRepository).save(captor.capture());
        assertEquals(id, captor.getValue().getId());
    }

    @Test
    void shouldThrowIfGuestRoomDoesNotExist() {
        Integer id = 213766;
        GuestRoomDto dto = new GuestRoomDto(id, UUID.randomUUID(), 101, UUID.randomUUID());

        when(guestRoomRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> guestRoomService.update(id, dto));
        verify(guestRoomRepository, never()).save(any());
    }

    @Test
    void shouldDeleteGuestRoomIfExists() {
        Integer id = 5444;
        GuestRoom gr = new GuestRoom(); gr.setId(id);
        when(guestRoomRepository.findById(id)).thenReturn(Optional.of(gr));

        guestRoomService.delete(id);

        verify(guestRoomRepository).delete(gr);
    }

    @Test
    void shouldThrowIfDeletingNonExistingGuestRoom() {
        Integer id = 1703;
        when(guestRoomRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> guestRoomService.delete(id));
    }

    @Test
    void shouldFindById() {
        Integer id = 888;
        GuestRoom gr = new GuestRoom(); gr.setId(id);
        when(guestRoomRepository.findById(id)).thenReturn(Optional.of(gr));

        GuestRoom result = guestRoomService.getById(id);

        assertEquals(gr, result);
    }

    @Test
    void shouldThrowWhenNotFoundById() {
        when(guestRoomRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> guestRoomService.getById(1));
    }
}

