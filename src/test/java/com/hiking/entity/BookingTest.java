package com.hiking.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class BookingTest {

    private Booking booking;

    @Before
    public void setup() {
        booking = new Booking();
        booking.setId(1L);
        booking.setBookingNumber("12345678");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(booking.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(booking.toString());
    }

    @Test
    public void equalsTest() {
        Booking instanceToCompare = createBooking();
        Booking nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(booking) && booking.equals(instanceToCompare));
        assertFalse(booking.equals(nullRequest));
        assertFalse(booking.equals(nullObject));
    }

    private Booking createBooking() {
        Booking booking = new Booking();
        booking.setId(1L);
        booking.setBookingNumber("12345678");
        return booking;
    }

}
