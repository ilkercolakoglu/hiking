package com.hiking.mapper;

import com.hiking.dto.HikerDTO;
import com.hiking.entity.Hiker;
import org.mapstruct.Mapper;

/**
 * @developer -- ilkercolakoglu
 */

@Mapper
public interface HikerMapper extends BaseMapper<Hiker, HikerDTO>{
}
