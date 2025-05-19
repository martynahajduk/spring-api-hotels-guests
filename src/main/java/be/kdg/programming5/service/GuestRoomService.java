package be.kdg.programming5.service;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.Room;
import be.kdg.programming5.domain.User;
import be.kdg.programming5.domain.exceptions.NotFoundException;
import be.kdg.programming5.repository.GuestRepository;
import be.kdg.programming5.repository.GuestRoomRepository;
import be.kdg.programming5.repository.RoomRepository;
import be.kdg.programming5.webapi.dto.BookingMapper;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import be.kdg.programming5.webapi.dto.GuestRoomMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class GuestRoomService implements GuestRoomServiceInterface {
    private final GuestRoomRepository guestRoomRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;
    private final GuestRoomMapper guestRoomMapper;
    private final BookingMapper bookingMapper;


    public GuestRoomService(GuestRoomRepository guestRoomRepository, GuestRepository guestRepository, RoomRepository roomRepository, GuestRoomMapper guestRoomMapper, BookingMapper bookingMapper) {
        this.guestRoomRepository = guestRoomRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
        this.guestRoomMapper = guestRoomMapper;
        this.bookingMapper = bookingMapper;
    }

    @Transactional
    @Override
    public List<GuestRoom> getAll() {
        return guestRoomRepository.findAllWithDetails();
    }

    @Override
    @Transactional
    public GuestRoom add(GuestRoomDto dto, User owner) {
        Guest guest = guestRepository.findById(dto.guestId())
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));

        Room room = roomRepository.findByHotel_HotelIdAndRoomNumber(dto.hotelId(), dto.roomNumber())
                .orElseThrow(() -> new IllegalArgumentException("Room not found in guest's hotel"));


        GuestRoom guestRoom = new GuestRoom();
        guestRoom.setGuest(guest);
        guestRoom.setRoom(room);
        if (owner != null) {
            guestRoom.setOwner(owner);
        }
        return guestRoomRepository.save(guestRoom);
    }

    @Transactional
    @Override
    public GuestRoom update(Integer id, GuestRoomDto dto) {
        GuestRoom guestRoom = guestRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("GuestRoom not found"));

        Guest guest = guestRepository.findById(dto.guestId())
                .orElseThrow(() -> new NotFoundException("Guest not found"));

        Room room = roomRepository.findByHotel_HotelIdAndRoomNumber(dto.hotelId(), dto.roomNumber())
                .orElseThrow(() -> new NotFoundException("Room not found"));

        guestRoom.setGuest(guest);
        guestRoom.setRoom(room);
        return guestRoomRepository.save(guestRoom);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        GuestRoom guestRoom = guestRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("GuestRoom not found"));
        guestRoomRepository.delete(guestRoom);
    }

    @Transactional
    @Override
    public GuestRoom getById(Integer id) {
        return guestRoomRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("GuestRoom not found"));
    }

    @Override
    public List<GuestRoomDto> searchByGuestName(String guestName) {
        List<GuestRoom> guestRooms = guestRoomRepository.findWithDetailsByGuestName(guestName);
        return guestRooms.stream()
                .map(bookingMapper::toDto)
                .toList();
    }


}
