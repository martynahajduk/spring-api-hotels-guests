package be.kdg.programming5.repository;

import be.kdg.programming5.domain.Guest;
import be.kdg.programming5.domain.Nationality;
import be.kdg.programming5.domain.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GuestRepository extends JpaRepository<Guest, UUID> {
    @Query("SELECT g FROM Guest g LEFT JOIN FETCH g.guestRooms " +
            "WHERE (:nationality IS NULL OR g.nationality = :nationality) " +
            "AND (:birthDate IS NULL OR g.dateOfBirth = :birthDate)")
    List<Guest> filterByNationalityAndBirthDate(@Param("nationality") Nationality nationality,
                                                @Param("birthDate") LocalDate birthDate);

    @Query("SELECT g FROM Guest g LEFT JOIN FETCH g.guestRooms WHERE g.guestId = :id")
    Optional<Guest> findByIdWithBookings(@Param("id") UUID id);

    @Query("SELECT g FROM Guest g LEFT JOIN FETCH g.guestRooms")
    List<Guest> findAllWithBookings();
}
