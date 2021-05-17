package com.hiking.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class HikingTrailTest {

    private HikingTrail hikingTrail;

    @Before
    public void setup() {
        hikingTrail = new HikingTrail();
        hikingTrail.setId(1L);
        hikingTrail.setPrice(29.90);
        hikingTrail.setMinAge(10);
        hikingTrail.setMaxAge(90);
        hikingTrail.setEndTime(LocalTime.MAX);
        hikingTrail.setStartTime(LocalTime.MIN);
        hikingTrail.setTrailName("trail of hiking");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(hikingTrail.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(hikingTrail.toString());
    }

    @Test
    public void equalsTest() {
        HikingTrail instanceToCompare = createHikingTrail();
        HikingTrail nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(hikingTrail) && hikingTrail.equals(instanceToCompare));
        assertFalse(hikingTrail.equals(nullRequest));
        assertFalse(hikingTrail.equals(nullObject));
    }

    private HikingTrail createHikingTrail() {
        HikingTrail hikingTrail = new HikingTrail();
        hikingTrail.setId(1L);
        hikingTrail.setPrice(29.90);
        hikingTrail.setMinAge(10);
        hikingTrail.setMaxAge(90);
        hikingTrail.setEndTime(LocalTime.MAX);
        hikingTrail.setStartTime(LocalTime.MIN);
        hikingTrail.setTrailName("trail of hiking");
        return hikingTrail;
    }

}
