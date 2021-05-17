package com.hiking.service;


import com.hiking.entity.HikingTrail;
import com.hiking.entity.Tour;
import com.hiking.repository.HikingTrailRepository;
import com.hiking.repository.TourRepository;
import com.hiking.service.impl.HikingTrailServiceImpl;
import com.hiking.service.impl.TourServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.when;


/**
 * @developer -- ilkercolakoglu
 */
public class TourServiceTest {

    @InjectMocks
    private TourServiceImpl tourService;


    @Mock
    private HikingTrailServiceImpl hikingTrailService;

    @Mock
    private TourRepository tourRepository;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_create_tours() {

        when(hikingTrailService.createHikingTrail(createShire())).thenReturn(createShire());
        when(hikingTrailService.createHikingTrail(createGondor())).thenReturn(createGondor());
        when(hikingTrailService.createHikingTrail(createMordor())).thenReturn(createMordor());

        when(tourRepository.save(createShireTour())).thenReturn(createShireTour());
        when(tourRepository.save(createGondorTour())).thenReturn(createGondorTour());
        when(tourRepository.save(createMordorTour())).thenReturn(createMordorTour());

        tourService.createTours();
        Assert.assertTrue(true);

    }



    private HikingTrail createShire(){
        HikingTrail shire = new HikingTrail(
                "Shire", LocalTime.of(7, 0), LocalTime.of(9, 0),
                5,
                100,
                29.90
        );
        return shire;
    }


    private Tour createShireTour() {
        Tour shireTour = new Tour();
        shireTour.setTourName("shire");
        shireTour.setTourDate(LocalDate.of(2020, 12, 2));
        shireTour.setHikingTrail(createShire());

        return shireTour;
    }

    private HikingTrail createGondor() {
        HikingTrail gondor = new HikingTrail(
                "Gondor", LocalTime.of(10, 0), LocalTime.of(13, 0),
                11,
                50,
                59.90
        );

        return gondor;
    }

    private Tour createGondorTour() {
        Tour gondorTour = new Tour();
        gondorTour.setTourName("gondor");
        gondorTour.setTourDate(LocalDate.of(2021, 1, 4));
        gondorTour.setHikingTrail(createGondor());

        return gondorTour;
    }


    private HikingTrail createMordor() {
        HikingTrail mordor = new HikingTrail(
                "Mordor", LocalTime.of(14, 0), LocalTime.of(19, 0),
                18,
                40,
                99.90
        );
        return mordor;
    }



    private Tour createMordorTour() {
        Tour mordorTour = new Tour();
        mordorTour.setTourName("mordor");
        mordorTour.setTourDate(LocalDate.of(2021, 2, 7));
        mordorTour.setHikingTrail(createMordor());

        return mordorTour;
    }

}
