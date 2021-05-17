package com.hiking.dto;

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
public class TourDTOTest {

    private TourDTO tourDTO;

    @Before
    public void setup() {
        tourDTO = new TourDTO();
        tourDTO.setId(1L);
        tourDTO.setTourDate(LocalDate.now());
        tourDTO.setTourName("first tourDTO");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(tourDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(tourDTO.toString());
    }

    @Test
    public void equalsTest() {
        TourDTO instanceToCompare = createTourDTO();
        TourDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(tourDTO) && tourDTO.equals(instanceToCompare));
        assertFalse(tourDTO.equals(nullRequest));
        assertFalse(tourDTO.equals(nullObject));
    }

    private TourDTO createTourDTO() {
        TourDTO tourDTO = new TourDTO();
        tourDTO.setId(1L);
        tourDTO.setTourDate(LocalDate.now());
        tourDTO.setTourName("first tourDTO");
        return tourDTO;
    }

}
