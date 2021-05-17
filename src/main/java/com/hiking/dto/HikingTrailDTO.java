package com.hiking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

/**
 * @developer -- ilkercolakoglu
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class HikingTrailDTO extends BaseDTO {

    private String trailName;

    private LocalTime startTime;

    private LocalTime endTime;

    private int minAge;

    private int maxAge;

    private double price;

}
