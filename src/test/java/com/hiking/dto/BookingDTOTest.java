package com.hiking.dto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class BookingDTOTest {

    private BookingDTO bookingDTO;

    @Before
    public void setup() {
        bookingDTO = new BookingDTO();
        bookingDTO.setId(1L);
        bookingDTO.setBookingNumber("12345678");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(bookingDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(bookingDTO.toString());
    }

    @Test
    public void equalsTest() {
        BookingDTO instanceToCompare = createBookingDTO();
        BookingDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(bookingDTO) && bookingDTO.equals(instanceToCompare));
        assertFalse(bookingDTO.equals(nullRequest));
        assertFalse(bookingDTO.equals(nullObject));
    }

    private BookingDTO createBookingDTO() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setId(1L);
        bookingDTO.setBookingNumber("12345678");
        return bookingDTO;
    }

}
