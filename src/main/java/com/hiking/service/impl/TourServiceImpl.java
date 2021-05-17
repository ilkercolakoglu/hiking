package com.hiking.service.impl;

import com.hiking.HikingTrailService;
import com.hiking.entity.HikingTrail;
import com.hiking.entity.Tour;
import com.hiking.exception.BookingNotFoundException;
import com.hiking.exception.TourNotFoundException;
import com.hiking.repository.TourRepository;
import com.hiking.service.TourService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.hiking.util.Consts.NO_BOOKING_FOUND;
import static com.hiking.util.Consts.NO_TOUR_FOUND;

/**
 * @developer -- ilkercolakoglu
 */

@Service
public class TourServiceImpl implements TourService {


    private final TourRepository tourRepository;

    private final HikingTrailService hikingTrailService;

    public TourServiceImpl(TourRepository tourRepository, HikingTrailService hikingTrailService) {
        this.tourRepository = tourRepository;
        this.hikingTrailService = hikingTrailService;
    }


    /**
     * Provides creating ready data for HikingTrail and Tours
     *
     */
    @PostConstruct
    public void createTours() {

        // Create Shire Tour

        HikingTrail shire = new HikingTrail(
                "Shire", LocalTime.of(7, 0), LocalTime.of(9, 0),
                5,
                100,
                29.90
        );

        shire = hikingTrailService.createHikingTrail(shire);

        Tour firstShireTour = new Tour();
        firstShireTour.setTourName("first shire");
        firstShireTour.setTourDate(LocalDate.of(2020, 12, 2));
        firstShireTour.setHikingTrail(shire);

        tourRepository.save(firstShireTour);

        // Create Gondor Tour

        HikingTrail gondor = new HikingTrail(
                "Gondor", LocalTime.of(10, 0), LocalTime.of(13, 0),
                11,
                50,
                59.90
        );

        gondor = hikingTrailService.createHikingTrail(gondor);

        Tour firstGondorTour = new Tour();
        firstGondorTour.setTourName("first gondor");
        firstGondorTour.setTourDate(LocalDate.of(2021, 1, 4));
        firstGondorTour.setHikingTrail(gondor);

        tourRepository.save(firstGondorTour);

        // Create Mordor Tour

        HikingTrail mordor = new HikingTrail(
                "Mordor", LocalTime.of(14, 0), LocalTime.of(19, 0),
                18,
                40,
                99.90
        );


        mordor = hikingTrailService.createHikingTrail(mordor);

        Tour firstMordorTour = new Tour();
        firstMordorTour.setTourName("first mordor");
        firstMordorTour.setTourDate(LocalDate.of(2021, 2, 7));
        firstMordorTour.setHikingTrail(mordor);

        tourRepository.save(firstMordorTour);

    }

    @Override
    public Tour getById(Long id) throws TourNotFoundException {
        return tourRepository.getById(id).orElseThrow(
                () -> new TourNotFoundException(NO_TOUR_FOUND)
        );
    }
}
