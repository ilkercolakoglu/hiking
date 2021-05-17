package com.hiking.service.impl;

import com.hiking.HikingTrailService;
import com.hiking.entity.HikingTrail;
import com.hiking.repository.HikingTrailRepository;
import org.springframework.stereotype.Service;

/**
 * @developer -- ilkercolakoglu
 */

@Service
public class HikingTrailServiceImpl implements HikingTrailService {

    private final HikingTrailRepository hikingTrailRepository;

    public HikingTrailServiceImpl(HikingTrailRepository hikingTrailRepository) {
        this.hikingTrailRepository = hikingTrailRepository;
    }

    /**
     * Creating hikingTrail and return data that has been created
     *
     * @param hikingTrail
     * @return
     */
    @Override
    public HikingTrail createHikingTrail(HikingTrail hikingTrail) {
        return hikingTrailRepository.save(hikingTrail);
    }



}
