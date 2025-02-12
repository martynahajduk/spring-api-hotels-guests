package be.kdg.programming5.repository;

import be.kdg.programming5.domain.Hotel;
import be.kdg.programming5.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {
    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.rooms WHERE h.name LIKE %:name%")
    List<Hotel> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.rooms r LEFT JOIN FETCH r.guestRooms")
    List<Hotel> findAllWithRoomsAndGuests();

    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.rooms WHERE h.hotelId = :hotelId")
    Optional<Hotel> findByIdWithRoomsAndGuests(@Param("hotelId") UUID hotelId);
}
