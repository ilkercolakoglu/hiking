package com.hiking.repository;

import com.hiking.entity.HikingTrail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @developer -- ilkercolakoglu
 */
public interface HikingTrailRepository extends JpaRepository<HikingTrail,Long> {


}
