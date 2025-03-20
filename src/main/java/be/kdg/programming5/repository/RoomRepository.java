package be.kdg.programming5.repository;

import be.kdg.programming5.domain.Room;
import be.kdg.programming5.domain.RoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, RoomId> {
    Optional<Room> findByHotel_HotelIdAndRoomNumber(UUID hotelId, int roomNumber);
}
