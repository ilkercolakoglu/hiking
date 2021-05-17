package com.hiking.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class TourTest {

    private Tour tour;

    @Before
    public void setup() {
        tour = new Tour();
        tour.setId(1L);
        tour.setTourDate(LocalDate.now());
        tour.setTourName("first tour");
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(tour.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(tour.toString());
    }

    @Test
    public void equalsTest() {
        Tour instanceToCompare = createTour();
        Tour nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(tour) && tour.equals(instanceToCompare));
        assertFalse(tour.equals(nullRequest));
        assertFalse(tour.equals(nullObject));
    }

    private Tour createTour() {
        Tour tour = new Tour();
        tour.setId(1L);
        tour.setTourDate(LocalDate.now());
        tour.setTourName("first tour");
        return tour;
    }

}
