package com.hiking.service.impl;

import com.hiking.exception.BookingNotFoundException;
import com.hiking.exception.CancellationException;
import com.hiking.exception.TourNotFoundException;
import com.hiking.exception.ViolateAgeRulesException;
import com.hiking.dto.*;
import com.hiking.entity.Booking;
import com.hiking.entity.Hiker;
import com.hiking.entity.HikingTrail;
import com.hiking.entity.Tour;
import com.hiking.mapper.BookingMapper;
import com.hiking.mapper.HikerMapper;
import com.hiking.repository.BookingRepository;
import com.hiking.service.BookingService;
import com.hiking.service.TourService;
import com.hiking.util.BookingUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.hiking.util.Consts.*;

/**
 * @developer -- ilkercolakoglu
 */

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingMapper bookingMapper;

    private final HikerMapper hikerMapper;

    private final BookingRepository bookingRepository;

    private final TourService tourService;

    /**
     * Setter injections
     *
     * @param bookingRepository
     * @param bookingMapper
     * @param hikerMapper
     * @param tourService
     */
    public BookingServiceImpl(BookingRepository bookingRepository,
                              BookingMapper bookingMapper, HikerMapper hikerMapper, TourService tourService) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.hikerMapper = hikerMapper;
        this.tourService = tourService;
    }

    /**
     * creating bookings with custom hikerBookingRequestDTO that includes tourId and hikerSet
     * @param hikerBookingRequestDTO
     * @return
     * @throws ViolateAgeRulesException
     * @throws TourNotFoundException
     */
    @Override
    public BookingDTO createBooking(HikerBookingRequestDTO hikerBookingRequestDTO) throws ViolateAgeRulesException, TourNotFoundException {
        Tour tour = tourService.getById(hikerBookingRequestDTO.getTourId());

        HikingTrail hikingTrail = tour.getHikingTrail();
        boolean validBooking = validateHikers(hikingTrail, hikerBookingRequestDTO.getHikerSet());

        if (!validBooking) {
            throw new ViolateAgeRulesException(AGE_RULE_EXCEPTION +
                    MIN_AGE + hikingTrail.getMinAge() +
                    MAX_AGE + hikingTrail.getMaxAge());
        }

        Set<Hiker> hikerSet = hikerMapper.toEntitySet(hikerBookingRequestDTO.getHikerSet());

        Booking booking=new Booking();
        booking.setTour(tour);
        booking.setBookingNumber(BookingUtil.createUniqueNumber());
        booking.setHikerSet(hikerSet);


        booking = bookingRepository.save(booking);
        return bookingMapper.toDTO(booking);
    }

    /**
     * cancel booking via cancelBookingDTO that includes bookingNumber and surname that is one of the hikers' surname.
     * Surname matching is mandatory for canceling.
     *
     * @param cancelBookingDTO
     * @return
     * @throws CancellationException
     * @throws BookingNotFoundException
     */
    @Override
    public String cancelBooking(CancelBookingDTO cancelBookingDTO) throws CancellationException, BookingNotFoundException {
        Booking booking = bookingRepository.findByBookingNumber(cancelBookingDTO.getBookingNumber())
                .orElseThrow(
                        () -> new BookingNotFoundException(NO_BOOKING_FOUND)
                );
        boolean surnameChecking = checkSurname(booking, cancelBookingDTO.getSurname());

        if (!surnameChecking) {
            throw new CancellationException(CANCEL_SURNAME_EXCEPTION);
        }

        bookingRepository.delete(booking);
        return CANCEL_BOOKING + cancelBookingDTO.getBookingNumber();
    }

    /**
     * finding all bookings in a specific date
     *
     * @param localDate
     * @return
     * @throws BookingNotFoundException
     */
    @Override
    public Set<BookingDTO> findByBookingsInDate(LocalDate localDate) throws BookingNotFoundException {
        List<Booking> bookingList = bookingRepository.findByTour_TourDate(localDate).orElseThrow(
                () -> new BookingNotFoundException(NO_BOOKING_FOUND)
        );

        Set<Booking> bookingSet=new HashSet<>();
        bookingSet.addAll(bookingList);

        return bookingMapper.toDTOSet(bookingSet);
    }

    /**
     * finding a booking with unique bookingNumber
     *
     * @param bookingNumber
     * @return
     * @throws BookingNotFoundException
     */
    @Override
    public BookingDTO findByBookingNumber(String bookingNumber) throws BookingNotFoundException {
        Booking booking = bookingRepository.findByBookingNumber(bookingNumber).orElseThrow(
                () -> new BookingNotFoundException(NO_BOOKING_FOUND)
        );
        return bookingMapper.toDTO(booking);
    }


    /**
     * checks that the age of the hikers is not within the age limits of the tour
     *
     * @param hikingTrailOfBooking
     * @param hikerDTOSet
     * @return
     */
    private boolean validateHikers(HikingTrail hikingTrailOfBooking, Set<HikerDTO> hikerDTOSet) {
        int minAge = hikingTrailOfBooking.getMinAge();
        int maxAge = hikingTrailOfBooking.getMaxAge();

        for (HikerDTO hikerDTO : hikerDTOSet) {
            int hikerAge = hikerDTO.getAge();
            if (hikerAge < minAge || hikerAge > maxAge) {
                return false;
            }
        }
        return true;
    }

    /**
     * checks that the surname of one of the hikers is matched the booking
     *
     * @param booking
     * @param givenSurname
     * @return
     * @throws BookingNotFoundException
     */
    private boolean checkSurname(Booking booking, String givenSurname) throws BookingNotFoundException {

        for (Hiker hiker : booking.getHikerSet()) {
            if (hiker.getHikerSurname().equalsIgnoreCase(givenSurname)) {
                return true;
            }
        }

        return false;
    }

}
