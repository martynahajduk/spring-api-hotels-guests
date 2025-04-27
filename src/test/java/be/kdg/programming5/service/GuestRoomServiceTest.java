package be.kdg.programming5.service;

import be.kdg.programming5.TestHelper;
import be.kdg.programming5.domain.*;
import be.kdg.programming5.webapi.dto.GuestRoomDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GuestRoomServiceTest {

    @Autowired private GuestRoomService guestRoomService;
    @Autowired private TestHelper testHelper;

    private User owner;
    private Guest guest;
    private Room room;
    private Hotel hotel;

    @BeforeEach
    @Transactional
    void setup() {
        testHelper.cleanUp();
        testHelper.seedUser("testuser");

        hotel = testHelper.createHotel("Test Hotel", "Test City");
        room = testHelper.createRoom(601, RoomType.DELUXE, 500, 6, hotel);
        guest = testHelper.createGuest("Test Guest", Nationality.BELGIAN, LocalDate.of(1990, 1, 1), hotel);
        owner = testHelper.findUser("testuser");
    }

    @Test
    @Transactional //success add method
    void canAddGuestRoom() {
        GuestRoomDto dto = new GuestRoomDto(
                null,
                guest.getGuestId(),
                room.getRoomNumber(),
                hotel.getHotelId()
        );

        GuestRoom guestRoom = guestRoomService.add(dto, owner);

        assertNotNull(guestRoom);
        assertEquals(dto.guestId(), guestRoom.getGuest().getGuestId());
        assertEquals(dto.roomNumber(), guestRoom.getRoom().getRoomNumber());
    }

    @Test
    @Transactional
    void failsOnInvalidRoomNumber() { //failure add method
        GuestRoomDto invalidDto = new GuestRoomDto(
                null,
                guest.getGuestId(),
                999,
                hotel.getHotelId()
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            guestRoomService.add(invalidDto, owner);
        });

        assertTrue(exception.getMessage().contains("Room not found"));
    }

    @AfterEach
    void cleanup() {
        testHelper.cleanUp();
    }
}
