package com.hiking.service;


import com.hiking.dto.*;
import com.hiking.entity.Booking;
import com.hiking.entity.Hiker;
import com.hiking.entity.HikingTrail;
import com.hiking.entity.Tour;
import com.hiking.exception.BookingNotFoundException;
import com.hiking.exception.CancellationException;
import com.hiking.exception.TourNotFoundException;
import com.hiking.exception.ViolateAgeRulesException;
import com.hiking.mapper.BookingMapper;
import com.hiking.mapper.HikerMapper;
import com.hiking.repository.BookingRepository;
import com.hiking.service.impl.BookingServiceImpl;
import com.hiking.service.impl.TourServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.hiking.util.Consts.CANCEL_BOOKING;
import static com.hiking.util.Consts.NO_TOUR_FOUND;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;


/**
 * @developer -- ilkercolakoglu
 */
public class BookingServiceTest {

    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private TourServiceImpl tourService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private HikerMapper hikerMapper;

    @Mock
    private BookingMapper bookingMapper;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_booking() {
        HikerBookingRequestDTO hikerBookingRequestDTO = createHikerBookingRequestDTO(31);

        Tour tour = createTour();

        try {
            when(tourService.getById(1L)).thenReturn(tour);
            when(hikerMapper.toEntitySet(hikerBookingRequestDTO.getHikerSet())).thenReturn(createHikerSet());

            Booking booking = createBooking();
            BookingDTO bookingDTO = createBookingDTO();

            when(bookingRepository.save(Mockito.any())).thenReturn(booking);
            when(bookingMapper.toDTO(Mockito.any())).thenReturn(bookingDTO);
            Assert.assertNotNull(bookingService.createBooking(hikerBookingRequestDTO));
        } catch (ViolateAgeRulesException e) {
            fail("should_create_booking encounter ViolateAgeRulesException");
        } catch (TourNotFoundException e) {
            fail("should_create_booking exception TourNotFoundException");
        }
    }

    @Test(expected = ViolateAgeRulesException.class)
    public void should_get_violate_age_rules_exception() throws ViolateAgeRulesException {
        HikerBookingRequestDTO hikerBookingRequestDTO = createHikerBookingRequestDTO(3);

        Tour tour = createTour();

        try {
            when(tourService.getById(1L)).thenReturn(tour);
            when(hikerMapper.toEntitySet(hikerBookingRequestDTO.getHikerSet())).thenReturn(createHikerSet());

            Booking booking = createBooking();
            BookingDTO bookingDTO = createBookingDTO();

            when(bookingRepository.save(Mockito.any())).thenReturn(booking);
            when(bookingMapper.toDTO(Mockito.any())).thenReturn(bookingDTO);
            Assert.assertNotNull(bookingService.createBooking(hikerBookingRequestDTO));
        } catch (TourNotFoundException e) {
            fail("should_get_violate_age_rules_exception exception TourNotFoundException");
        }
    }

    @Test(expected = TourNotFoundException.class)
    public void should_get_tour_not_found_exception() throws TourNotFoundException {
        HikerBookingRequestDTO hikerBookingRequestDTO = createHikerBookingRequestDTO(31);

        try {
            when(tourService.getById(1L)).thenThrow(new TourNotFoundException(NO_TOUR_FOUND));
            when(hikerMapper.toEntitySet(hikerBookingRequestDTO.getHikerSet())).thenReturn(createHikerSet());

            Booking booking = createBooking();
            BookingDTO bookingDTO = createBookingDTO();

            when(bookingRepository.save(Mockito.any())).thenReturn(booking);
            when(bookingMapper.toDTO(Mockito.any())).thenReturn(bookingDTO);
            Assert.assertNotNull(bookingService.createBooking(hikerBookingRequestDTO));
        } catch (ViolateAgeRulesException e) {
            fail("should_get_tour_not_found_exception exception ViolateAgeRulesException");
        }
    }

    @Test
    public void should_cancel_booking() {
        try {
            when(bookingRepository.findByBookingNumber("12345678")).thenReturn(Optional.of(createBooking()));

            doNothing().when(bookingRepository).delete(createBooking());


            Assert.assertEquals(CANCEL_BOOKING + "12345678", bookingService.cancelBooking(createCancelBookingDTO()));
        } catch (CancellationException e) {
            fail("should_cancel_booking exception CancellationException");
        } catch (BookingNotFoundException e) {
            fail("should_cancel_booking exception BookingNotFoundException");
        }
    }

    @Test(expected = BookingNotFoundException.class)
    public void should_get_booking_not_found_exception_in_cancel() throws BookingNotFoundException {
        try {
            when(bookingRepository.findByBookingNumber("123456789")).thenReturn(Optional.of(createBooking()));

            doNothing().when(bookingRepository).delete(createBooking());


            Assert.assertEquals(CANCEL_BOOKING + "12345678", bookingService.cancelBooking(createCancelBookingDTO()));
        } catch (CancellationException e) {
            fail("should_get_booking_not_found_exception_in_cancel exception CancellationException");
        }
    }


    @Test(expected = CancellationException.class)
    public void should_get_cancellation_exception_in_cancel() throws CancellationException {
        try {
            when(bookingRepository.findByBookingNumber("12345678")).thenReturn(Optional.of(createBooking()));

            doNothing().when(bookingRepository).delete(createBooking());


            CancelBookingDTO cancelBookingDTO = createCancelBookingDTO();
            cancelBookingDTO.setSurname("wrongSurname");
            Assert.assertEquals(CANCEL_BOOKING + "12345678", bookingService.cancelBooking(cancelBookingDTO));
        } catch (BookingNotFoundException e) {
            fail("should_get_cancellation_exception_in_cancel exception BookingNotFoundException");
        }
    }

    @Test(expected = CancellationException.class)
    public void should_get_cancellation_exception() throws CancellationException {
        try {
            when(bookingRepository.findByBookingNumber("12345678")).thenReturn(Optional.of(createBooking()));

            doNothing().when(bookingRepository).delete(createBooking());


            CancelBookingDTO cancelBookingDTO = createCancelBookingDTO();
            cancelBookingDTO.setSurname("wrongSurname");
            Assert.assertEquals(CANCEL_BOOKING + "12345678", bookingService.cancelBooking(cancelBookingDTO));
        } catch (BookingNotFoundException e) {
            fail("should_get_cancellation_exception exception BookingNotFoundException");
        }
    }

    @Test
    public void should_get_find_by_tour_date() {
        try {
            LocalDate localDate = LocalDate.now();
            when(bookingRepository.findByTour_TourDate(localDate)).thenReturn(Optional.of(createBookingList()));
            when(bookingMapper.toDTOSet(createBookingSet())).thenReturn(createBookingDTOSet());


            Assert.assertEquals(createBookingDTOSet(), bookingService.findByBookingsInDate(localDate));
        } catch (BookingNotFoundException e) {
            fail("should_get_find_by_tour_date exception BookingNotFoundException");
        }
    }

    @Test(expected = BookingNotFoundException.class)
    public void should_get_booking_not_found_exception_in_find_by_tour_date() throws BookingNotFoundException {
        LocalDate localDate = LocalDate.now();
        when(bookingRepository.findByTour_TourDate(localDate)).thenReturn(Optional.of(createBookingList()));
        when(bookingMapper.toDTOSet(createBookingSet())).thenReturn(createBookingDTOSet());

        LocalDate differentLocalDate = LocalDate.of(2000, 01, 01);
        Assert.assertEquals(createBookingDTOSet(), bookingService.findByBookingsInDate(differentLocalDate));
    }

    @Test
    public void should_get_find_by_booking_number() {
        try {
            String bookingNumber = "12345678";
            when(bookingRepository.findByBookingNumber(bookingNumber)).thenReturn(Optional.of(createBooking()));
            when(bookingMapper.toDTO(createBooking())).thenReturn(createBookingDTO());

            Assert.assertEquals(createBookingDTO(), bookingService.findByBookingNumber(bookingNumber));
        } catch (BookingNotFoundException e) {
            fail("should_get_find_by_booking_number exception BookingNotFoundException");
        }
    }

    @Test(expected = BookingNotFoundException.class)
    public void should_get_booking_not_found_exception_in_find_by_booking_number() throws BookingNotFoundException {
        String bookingNumber = "12345678";
        when(bookingRepository.findByBookingNumber(bookingNumber)).thenReturn(Optional.of(createBooking()));
        when(bookingMapper.toDTO(createBooking())).thenReturn(createBookingDTO());

        String notExistenceBookingNumber = "123456789";
        Assert.assertEquals(createBookingDTO(), bookingService.findByBookingNumber(notExistenceBookingNumber));
    }


    private HikerBookingRequestDTO createHikerBookingRequestDTO(int age) {
        HikerBookingRequestDTO hikerBookingRequestDTO = new HikerBookingRequestDTO();
        hikerBookingRequestDTO.setTourId(1L);
        Set<HikerDTO> hikerSet = new HashSet<>();
        HikerDTO hiker = new HikerDTO();
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(age);
        hikerSet.add(hiker);
        hikerBookingRequestDTO.setHikerSet(hikerSet);
        return hikerBookingRequestDTO;
    }

    private Tour createTour() {
        Tour tour = new Tour();
        tour.setTourDate(LocalDate.now());
        tour.setTourName("first tour");
        tour.setHikingTrail(createHikingTrail());
        return tour;
    }

    private TourDTO createTourDTO() {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setTourDate(LocalDate.now());
        tourDTO.setTourName("first tour");
        tourDTO.setHikingTrail(createHikingTrailDTO());
        return tourDTO;
    }

    private HikingTrail createHikingTrail() {
        HikingTrail hikingTrail = new HikingTrail();
        hikingTrail.setPrice(29.90);
        hikingTrail.setMinAge(10);
        hikingTrail.setMaxAge(90);
        hikingTrail.setEndTime(LocalTime.MAX);
        hikingTrail.setStartTime(LocalTime.MIN);
        hikingTrail.setTrailName("trail of hiking");
        return hikingTrail;
    }

    private HikingTrailDTO createHikingTrailDTO() {
        HikingTrailDTO hikingTrailDTO = new HikingTrailDTO();
        hikingTrailDTO.setPrice(29.90);
        hikingTrailDTO.setMinAge(10);
        hikingTrailDTO.setMaxAge(90);
        hikingTrailDTO.setEndTime(LocalTime.MAX);
        hikingTrailDTO.setStartTime(LocalTime.MIN);
        hikingTrailDTO.setTrailName("trail of hiking");
        return hikingTrailDTO;
    }

    private BookingDTO createBookingDTO() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingNumber("12345678");
        bookingDTO.setTour(createTourDTO());
        bookingDTO.setHikerSet(createHikerDTOSet());
        return bookingDTO;
    }

    private Booking createBooking() {
        Booking booking = new Booking();
        booking.setBookingNumber("12345678");
        booking.setTour(createTour());
        booking.setHikerSet(createHikerSet());
        return booking;
    }


    private Set<Hiker> createHikerSet() {
        Set<Hiker> hikerSet = new HashSet<>();
        Hiker hiker = new Hiker();
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(31);
        hikerSet.add(hiker);
        return hikerSet;
    }

    private Set<HikerDTO> createHikerDTOSet() {
        Set<HikerDTO> hikerDTOSet = new HashSet<>();
        HikerDTO hikerDTO = new HikerDTO();
        hikerDTO.setHikerName("ilker");
        hikerDTO.setHikerSurname("colakoglu");
        hikerDTO.setAge(31);
        hikerDTOSet.add(hikerDTO);
        return hikerDTOSet;
    }

    private CancelBookingDTO createCancelBookingDTO() {
        CancelBookingDTO cancelBookingDTO = new CancelBookingDTO();
        cancelBookingDTO.setBookingNumber("12345678");
        cancelBookingDTO.setSurname("colakoglu");
        return cancelBookingDTO;
    }

    private List<Booking> createBookingList() {
        List<Booking> bookingList = new ArrayList<>();
        Booking booking = createBooking();
        bookingList.add(booking);
        return bookingList;
    }

    private Set<Booking> createBookingSet() {
        Set<Booking> bookingSet = new HashSet<>();
        Booking booking = createBooking();
        bookingSet.add(booking);
        return bookingSet;
    }

    private Set<BookingDTO> createBookingDTOSet() {
        Set<BookingDTO> bookingDTOSet = new HashSet<>();
        BookingDTO bookingDTO = createBookingDTO();
        bookingDTOSet.add(bookingDTO);
        return bookingDTOSet;
    }


}
