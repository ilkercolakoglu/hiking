package com.hiking.service;


import com.hiking.dto.*;
import com.hiking.entity.Booking;
import com.hiking.entity.Hiker;
import com.hiking.entity.HikingTrail;
import com.hiking.entity.Tour;
import com.hiking.exception.BookingNotFoundException;
import com.hiking.exception.CancellationException;
import com.hiking.exception.TourNotFoundException;
import com.hiking.exception.ViolateAgeRulesException;
import com.hiking.mapper.BookingMapper;
import com.hiking.mapper.HikerMapper;
import com.hiking.repository.BookingRepository;
import com.hiking.repository.HikingTrailRepository;
import com.hiking.service.impl.BookingServiceImpl;
import com.hiking.service.impl.HikingTrailServiceImpl;
import com.hiking.service.impl.TourServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static com.hiking.util.Consts.CANCEL_BOOKING;
import static com.hiking.util.Consts.NO_TOUR_FOUND;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


/**
 * @developer -- ilkercolakoglu
 */
public class HikingTrailServiceTest {

    @InjectMocks
    private HikingTrailServiceImpl hikingTrailService;


    @Mock
    private HikingTrailRepository hikingTrailRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_hiking_trail() {
        HikingTrail hikingTrail = createHikingTrail();

        when(hikingTrailRepository.save(hikingTrail)).thenReturn(hikingTrail);
        Assert.assertEquals(hikingTrail, hikingTrailService.createHikingTrail(hikingTrail));

    }

    private HikingTrail createHikingTrail() {
        HikingTrail hikingTrail = new HikingTrail();
        hikingTrail.setPrice(29.90);
        hikingTrail.setMinAge(10);
        hikingTrail.setMaxAge(90);
        hikingTrail.setEndTime(LocalTime.MAX);
        hikingTrail.setStartTime(LocalTime.MIN);
        hikingTrail.setTrailName("trail of hiking");
        return hikingTrail;
    }



}
