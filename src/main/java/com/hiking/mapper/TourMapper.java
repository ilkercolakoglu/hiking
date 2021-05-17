package com.hiking.mapper;

import com.hiking.dto.TourDTO;
import com.hiking.entity.Tour;
import org.mapstruct.Mapper;

/**
 * @developer -- ilkercolakoglu
 */

@Mapper
public interface TourMapper extends BaseMapper<Tour, TourDTO>{
}
