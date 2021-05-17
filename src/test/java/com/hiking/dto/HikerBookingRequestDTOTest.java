package com.hiking.dto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class HikerBookingRequestDTOTest {

    private HikerBookingRequestDTO hikerBookingRequestDTO;

    @Before
    public void setup() {
        hikerBookingRequestDTO = new HikerBookingRequestDTO();
        hikerBookingRequestDTO.setTourId(1L);
        Set<HikerDTO> hikerSet = new HashSet<>();
        HikerDTO hiker = new HikerDTO();
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(31);
        hikerSet.add(hiker);
        hikerBookingRequestDTO.setHikerSet(hikerSet);
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(hikerBookingRequestDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(hikerBookingRequestDTO.toString());
    }

    @Test
    public void equalsTest() {
        HikerBookingRequestDTO instanceToCompare = createHikerBookingRequestDTO();
        HikerBookingRequestDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(hikerBookingRequestDTO) && hikerBookingRequestDTO.equals(instanceToCompare));
        assertFalse(hikerBookingRequestDTO.equals(nullRequest));
        assertFalse(hikerBookingRequestDTO.equals(nullObject));
    }

    private HikerBookingRequestDTO createHikerBookingRequestDTO() {
        HikerBookingRequestDTO hikerBookingRequestDTO = new HikerBookingRequestDTO();
        hikerBookingRequestDTO.setTourId(1L);
        Set<HikerDTO> hikerSet = new HashSet<>();
        HikerDTO hiker = new HikerDTO();
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(31);
        hikerSet.add(hiker);
        hikerBookingRequestDTO.setHikerSet(hikerSet);
        return hikerBookingRequestDTO;
    }

}
