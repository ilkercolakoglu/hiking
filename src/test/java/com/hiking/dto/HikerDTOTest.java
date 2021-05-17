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
public class HikerDTOTest {

    private HikerDTO hikerDTO;

    @Before
    public void setup() {
        hikerDTO = new HikerDTO();
        hikerDTO.setId(1L);
        hikerDTO.setHikerName("ilker");
        hikerDTO.setHikerSurname("colakoglu");
        hikerDTO.setAge(31);
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(hikerDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(hikerDTO.toString());
    }

    @Test
    public void equalsTest() {
        HikerDTO instanceToCompare = createHikerDTO();
        HikerDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(hikerDTO) && hikerDTO.equals(instanceToCompare));
        assertFalse(hikerDTO.equals(nullRequest));
        assertFalse(hikerDTO.equals(nullObject));
    }

    private HikerDTO createHikerDTO() {
        HikerDTO hikerDTO = new HikerDTO();
        hikerDTO.setId(1L);
        hikerDTO.setHikerName("ilker");
        hikerDTO.setHikerSurname("colakoglu");
        hikerDTO.setAge(31);
        return hikerDTO;
    }

}
