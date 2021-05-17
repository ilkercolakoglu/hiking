package com.hiking.controller;

import com.hiking.exception.BookingNotFoundException;
import com.hiking.exception.CancellationException;
import com.hiking.exception.TourNotFoundException;
import com.hiking.exception.ViolateAgeRulesException;
import com.hiking.dto.BookingDTO;
import com.hiking.dto.CancelBookingDTO;
import com.hiking.dto.HikerBookingRequestDTO;
import com.hiking.service.BookingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Set;

import static com.hiking.util.Consts.*;

/**
 * @developer -- ilkercolakoglu
 */

@RestController
@RequestMapping("/booking")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    /**
     * Endpoint that creating a booking
     *
     * @param hikerBookingRequestDTO
     * @return
     * @throws ViolateAgeRulesException
     * @throws TourNotFoundException
     */
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "booking for tours",
            notes = "make a booking via json request.",
            responseContainer = "booking for tour",
            response = ResponseEntity.class)
    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = AGE_RULE_EXCEPTION),
            @ApiResponse(code = 404, message = NO_TOUR_FOUND)
    })
    public BookingDTO createBooking(@Valid @RequestBody HikerBookingRequestDTO hikerBookingRequestDTO)
            throws ViolateAgeRulesException, TourNotFoundException {
        return bookingService.createBooking(hikerBookingRequestDTO);
    }


    /**
     * Endpoint that cancels the booking via bookingNumber and surname.
     * Surname matching is mandatory for canceling. The given surname must be one of the hikers' surname.
     *
     * @param cancelBookingDTO
     * @return
     * @throws CancellationException
     * @throws BookingNotFoundException
     */
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "cancellation booking with bookingNumber and surname",
            notes = "Surname matching is mandatory for canceling. The given surname must be one of the hikers' surname.",
            responseContainer = "cancel a booking",
            response = ResponseEntity.class)
    @PostMapping("/cancel")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = CANCEL_SURNAME_EXCEPTION),
            @ApiResponse(code = 404, message = NO_BOOKING_FOUND)
    })
    public String cancelBooking(@Valid @RequestBody CancelBookingDTO cancelBookingDTO) throws CancellationException, BookingNotFoundException {
        return bookingService.cancelBooking(cancelBookingDTO);
    }

    /**
     * Endpoint that views bookings in a date for tours
     *
     * @param localDate
     * @return
     * @throws BookingNotFoundException
     */
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "view booking in a date for tours",
            notes = "view bookings for a specific date.",
            responseContainer = "view bookings in a date",
            response = ResponseEntity.class)
    @GetMapping("/view_bookings_with_date")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = NO_BOOKING_FOUND)
    })
    public Set<BookingDTO> viewBookingWithDate(@RequestParam("localDate") @Valid
                                              @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate localDate) throws BookingNotFoundException {
        return bookingService.findByBookingsInDate(localDate);
    }

    /**
     * Endpoint that views a booking for tours
     *
     * @param bookingNumber
     * @return
     * @throws BookingNotFoundException
     */
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "viewing a booking for tours",
            notes = "view a booking via bookingNumber.",
            responseContainer = "view booking for tour",
            response = ResponseEntity.class)
    @GetMapping("/view_booking")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = NO_BOOKING_FOUND)
    })
    public BookingDTO viewBooking(@RequestParam String bookingNumber) throws BookingNotFoundException {
        return bookingService.findByBookingNumber(bookingNumber);
    }

}
