package be.kdg.programming5.service;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.GuestRoom;
import be.kdg.programming5.domain.Room;
import be.kdg.programming5.domain.User;
import be.kdg.programming5.repository.GuestRepository;
import be.kdg.programming5.repository.GuestRoomRepository;
import be.kdg.programming5.repository.RoomRepository;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GuestRoomService implements GuestRoomServiceInterface {
    private final GuestRoomRepository guestRoomRepository;
    private final GuestRepository guestRepository;
    private final RoomRepository roomRepository;

    public GuestRoomService(GuestRoomRepository guestRoomRepository, GuestRepository guestRepository, RoomRepository roomRepository) {
        this.guestRoomRepository = guestRoomRepository;
        this.guestRepository = guestRepository;
        this.roomRepository = roomRepository;
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
        guestRoom.setOwner(owner);

        return guestRoomRepository.save(guestRoom);
    }

    @Override
    @Transactional
    public GuestRoom update(Integer id, GuestRoomDto dto) {
        GuestRoom guestRoom = guestRoomRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("GuestRoom not found"));

        Guest guest = guestRepository.findById(dto.guestId())
                .orElseThrow(() -> new IllegalArgumentException("Guest not found"));

        Room room = roomRepository.findByHotel_HotelIdAndRoomNumber(dto.hotelId(), dto.roomNumber())
                .orElseThrow(() -> new IllegalArgumentException("Room not found in guest's hotel"));


        guestRoom.setGuest(guest);
        guestRoom.setRoom(room);
        return guestRoomRepository.save(guestRoom);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        guestRoomRepository.deleteById(id);
    }
}
