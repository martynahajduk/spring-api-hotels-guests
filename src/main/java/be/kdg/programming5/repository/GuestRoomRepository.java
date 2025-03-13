package be.kdg.programming5.repository;

import be.kdg.programming5.domain.GuestRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GuestRoomRepository extends JpaRepository<GuestRoom, Integer> {
    @Query("SELECT gr FROM GuestRoom gr " +
            "JOIN FETCH gr.room r " +
            "JOIN FETCH r.hotel " +
            "JOIN FETCH gr.guest g " +
            "JOIN FETCH g.hotel")
    List<GuestRoom> findAllWithDetails();
}
