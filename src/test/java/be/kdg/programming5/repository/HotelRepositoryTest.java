package be.kdg.programming5.repository;

import be.kdg.programming5.TestHelper;
import be.kdg.programming5.domain.Hotel;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HotelRepositoryTest {

    @Autowired
    private HotelRepository hotelRepository;
    @Autowired
    private TestHelper testHelper;


    private UUID hotelId;

    @BeforeAll
    void setup() {
        Hotel hotel = new Hotel();
        hotel.setName("Test Hotel 123");
        hotel.setLocation("Przemysl");
        hotelId = hotelRepository.save(hotel).getHotelId();
    }

    @Test //failure save
    void testHotelNameMustNotBeNull() {
        Hotel hotel = new Hotel();
        hotel.setLocation("No name hotel");
        assertThrows(DataIntegrityViolationException.class, () -> {
            hotelRepository.save(hotel);
        });
    }

    @Test //success
    void testDeleteHotel() {
        hotelRepository.deleteById(hotelId);
        Optional<Hotel> deletedHotel = hotelRepository.findById(hotelId);
        assertFalse(deletedHotel.isPresent(), "Hotel should be deleted");
    }

    @Test
    void testLazyLoadingGuestRoomsFailsOutsideTransaction() {
        Hotel hotel = testHelper.createHotel("LazyLoadHotel", "Nowhere");

        Hotel fetched = hotelRepository.findById(hotel.getHotelId()).orElseThrow();

        assertThrows(LazyInitializationException.class, () -> fetched.getRooms().size());
    }

}


