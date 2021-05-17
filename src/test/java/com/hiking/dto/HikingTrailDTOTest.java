package com.hiking.dto;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class HikingTrailDTOTest {

    private HikingTrailDTO hikingTrailDTO;

    @Before
    public void setup() {
        hikingTrailDTO = new HikingTrailDTO();
        hikingTrailDTO.setId(1L);
        hikingTrailDTO.setPrice(29.90);
        hikingTrailDTO.setMinAge(10);
        hikingTrailDTO.setMaxAge(90);
        hikingTrailDTO.setEndTime(LocalTime.MAX);
        hikingTrailDTO.setStartTime(LocalTime.MIN);
        hikingTrailDTO.setTrailName("trail of hiking");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(hikingTrailDTO.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(hikingTrailDTO.toString());
    }

    @Test
    public void equalsTest() {
        HikingTrailDTO instanceToCompare = createHikingTrailDTO();
        HikingTrailDTO nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(hikingTrailDTO) && hikingTrailDTO.equals(instanceToCompare));
        assertFalse(hikingTrailDTO.equals(nullRequest));
        assertFalse(hikingTrailDTO.equals(nullObject));
    }

    private HikingTrailDTO createHikingTrailDTO() {
        HikingTrailDTO hikingTrailDTO = new HikingTrailDTO();
        hikingTrailDTO.setId(1L);
        hikingTrailDTO.setPrice(29.90);
        hikingTrailDTO.setMinAge(10);
        hikingTrailDTO.setMaxAge(90);
        hikingTrailDTO.setEndTime(LocalTime.MAX);
        hikingTrailDTO.setStartTime(LocalTime.MIN);
        hikingTrailDTO.setTrailName("trail of hiking");
        return hikingTrailDTO;
    }

}
