package com.hiking.repository;

import com.hiking.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @developer -- ilkercolakoglu
 */
public interface TourRepository extends JpaRepository<Tour,Long> {

   Optional<Tour> getById(Long id);
}
