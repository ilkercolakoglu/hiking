package com.hiking.service;

import com.hiking.entity.Tour;
import com.hiking.exception.TourNotFoundException;

/**
 * @developer -- ilkercolakoglu
 */
public interface TourService {

    Tour getById(Long id) throws TourNotFoundException;

}
