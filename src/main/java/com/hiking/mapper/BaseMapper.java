package com.hiking.mapper;

import com.hiking.dto.BaseDTO;
import com.hiking.entity.BaseEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

/**
 * @developer -- ilkercolakoglu
 */


public interface BaseMapper<Entity extends BaseEntity, DTO extends BaseDTO> {

    @Named("toEntity")
    @Mapping(target = "creationDate", ignore = true)
    Entity toEntity(DTO dto);

    @Named("toDTO")
    DTO toDTO(Entity entity);

    @IterableMapping(qualifiedByName = "toEntity")
    Set<Entity> toEntitySet(Set<DTO> dtoSet);

    @IterableMapping(qualifiedByName = "toDto")
    Set<DTO> toDTOSet(Set<Entity> entitySet);


}
