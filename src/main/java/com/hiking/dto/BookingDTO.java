package com.hiking.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @developer -- ilkercolakoglu
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class BookingDTO extends BaseDTO{

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String bookingNumber;

    private TourDTO tour;

    private Set<HikerDTO> hikerSet;

}
