package com.hiking.dto;

import com.hiking.entity.HikingTrail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.ManyToOne;
import java.time.LocalDate;

/**
 * @developer -- ilkercolakoglu
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class TourDTO extends BaseDTO{

    private String tourName;

    private LocalDate tourDate;

    private HikingTrailDTO hikingTrail;

}
