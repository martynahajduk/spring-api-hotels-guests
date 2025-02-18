package be.kdg.programming5.repository;

import be.kdg.programming5.domain.GuestRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRoomRepository extends JpaRepository<GuestRoom, Integer> {
}
