package com.hiking.repository;

import com.hiking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @developer -- ilkercolakoglu
 */
public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<List<Booking>> findByTour_TourDate(LocalDate tour_tourDate);

    Optional<Booking> findByBookingNumber(String bookingNumber);

}
