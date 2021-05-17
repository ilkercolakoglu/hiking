package com.hiking.service;

import com.hiking.exception.BookingNotFoundException;
import com.hiking.exception.CancellationException;
import com.hiking.exception.TourNotFoundException;
import com.hiking.exception.ViolateAgeRulesException;
import com.hiking.dto.BookingDTO;
import com.hiking.dto.CancelBookingDTO;
import com.hiking.dto.HikerBookingRequestDTO;

import java.time.LocalDate;
import java.util.Set;

/**
 * @developer -- ilkercolakoglu
 */

public interface BookingService {

    BookingDTO createBooking(HikerBookingRequestDTO hikerBookingRequestDTO) throws ViolateAgeRulesException, TourNotFoundException;

    String cancelBooking(CancelBookingDTO cancelBookingDTO) throws CancellationException, BookingNotFoundException;

    Set<BookingDTO> findByBookingsInDate(LocalDate localDate) throws BookingNotFoundException;

    BookingDTO findByBookingNumber(String bookingNumber) throws BookingNotFoundException;


}
