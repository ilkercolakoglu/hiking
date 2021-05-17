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
public class CancelBookingDTOTest {

    private CancelBookingDTO cancelBookingDTO;

    @Before
    public void setup() {
        cancelBookingDTO = new CancelBookingDTO();
        cancelBookingDTO.setSurname("colakoglu");
        cancelBookingDTO.setBookingNumber("12345678");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(cancelBookingDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(cancelBookingDTO.toString());
    }

    @Test
    public void equalsTest() {
        CancelBookingDTO instanceToCompare = createCancelBookingDTO();
        CancelBookingDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(cancelBookingDTO) && cancelBookingDTO.equals(instanceToCompare));
        assertFalse(cancelBookingDTO.equals(nullRequest));
        assertFalse(cancelBookingDTO.equals(nullObject));
    }

    private CancelBookingDTO createCancelBookingDTO() {
        CancelBookingDTO cancelBookingDTO = new CancelBookingDTO();
        cancelBookingDTO.setSurname("colakoglu");
        cancelBookingDTO.setBookingNumber("12345678");
        return cancelBookingDTO;
    }

}
