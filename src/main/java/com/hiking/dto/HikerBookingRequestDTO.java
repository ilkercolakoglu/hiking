package com.hiking.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @developer -- ilkercolakoglu
 */

@Data
public class HikerBookingRequestDTO {

    @NotNull
    private Long tourId;

    private Set<HikerDTO> hikerSet;
}
