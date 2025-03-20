package be.kdg.programming5.service;

import be.kdg.programming5.domain.*;
import be.kdg.programming5.repository.*;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRoomServiceTest {
    @Autowired
    private GuestRoomService guestRoomService;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private GuestRoomRepository guestRoomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    private Guest guest;
    private Room room;
    private Hotel hotel;
    private User owner;

    @BeforeEach
    @Transactional
    void setup() {
        hotel = new Hotel();
        hotel.setName("Test Hotel");
        hotel.setLocation("Test City");
        hotel = hotelRepository.save(hotel);

        // ⬇️ DODAJ TO: reload hotel to get attached instance
        hotel = hotelRepository.findById(hotel.getHotelId()).orElseThrow();

        room = new Room();
        room.setRoomNumber(101);
        room.setRoomType(RoomType.DELUXE);
        room.setPrice(500);
        room.setFloor(1);
        room.setHotel(hotel); // używamy attached hotel
        room = roomRepository.save(room);

        hotel = hotelRepository.findById(hotel.getHotelId()).orElseThrow();


        guest = new Guest();
        guest.setName("Test Guest");
        guest.setNationality(Nationality.BELGIAN);
        guest.setDateOfBirth(LocalDate.of(1990, 1, 1));
        guest.setHotel(hotel);
        guest = guestRepository.save(guest);

        owner = new User();
        owner.setUsername("testuser_" + UUID.randomUUID());
        owner.setPassword("secret");
        owner.setRole(Role.USER);
        owner = userRepository.save(owner);
    }


    @Test
    @Transactional
    void canAddGuestRoom() {
        GuestRoomDto dto = new GuestRoomDto(null, guest.getGuestId(), room.getRoomNumber(), hotel.getHotelId());

        GuestRoom guestRoom = guestRoomService.add(dto, owner);

        assertNotNull(guestRoom);
        assertEquals(guest.getGuestId(), guestRoom.getGuest().getGuestId());
        assertEquals(room.getRoomNumber(), guestRoom.getRoom().getRoomNumber());
    }

    @Test
    @Transactional
    void failsOnInvalidRoomNumber() {
        GuestRoomDto invalidDto = new GuestRoomDto(null, guest.getGuestId(), 999, hotel.getHotelId());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            guestRoomService.add(invalidDto, owner);
        });

        assertTrue(exception.getMessage().contains("Room not found"));
    }

    @AfterEach
    void cleanup() {
        guestRoomRepository.deleteAll();
        guestRepository.deleteAll();
        hotelRepository.deleteAll();
        userRepository.deleteAll();
    }

}