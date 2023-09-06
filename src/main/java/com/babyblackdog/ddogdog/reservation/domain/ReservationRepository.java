package com.babyblackdog.ddogdog.reservation.domain;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.roomId = :roomId AND r.checkIn <= :checkOut AND r.checkOut >= :checkIn")
    List<Reservation> findOverlappingReservations(Long roomId, LocalDate checkIn,
            LocalDate checkOut);
}