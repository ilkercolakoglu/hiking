package com.hiking.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @developer -- ilkercolakoglu
 */

@RunWith(MockitoJUnitRunner.class)
public class HikerTest {

    private Hiker hiker;

    @Before
    public void setup() {
        hiker = new Hiker();
        hiker.setId(1L);
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(31);
    }

    @Test
    public void hashCodeTest() {
        assertNotNull(hiker.hashCode());
    }

    @Test
    public void toStringTest() {
        assertNotNull(hiker.toString());
    }

    @Test
    public void equalsTest() {
        Hiker instanceToCompare = createHiker();
        Hiker nullRequest = null;
        Object nullObject = null;
        assertTrue(instanceToCompare.equals(hiker) && hiker.equals(instanceToCompare));
        assertFalse(hiker.equals(nullRequest));
        assertFalse(hiker.equals(nullObject));
    }

    private Hiker createHiker() {
        Hiker hiker = new Hiker();
        hiker.setId(1L);
        hiker.setHikerName("ilker");
        hiker.setHikerSurname("colakoglu");
        hiker.setAge(31);
        return hiker;
    }

}
