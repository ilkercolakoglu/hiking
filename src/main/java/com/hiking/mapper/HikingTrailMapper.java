package com.hiking.mapper;

import com.hiking.dto.HikingTrailDTO;
import com.hiking.entity.HikingTrail;
import org.mapstruct.Mapper;

/**
 * @developer -- ilkercolakoglu
 */

@Mapper
public interface HikingTrailMapper extends BaseMapper<HikingTrail, HikingTrailDTO>{
}
