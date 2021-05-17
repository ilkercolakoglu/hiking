package com.hiking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @developer -- ilkercolakoglu
 */

@Data
@Entity
@Table(name = "TOURS")
public class Tour extends BaseEntity {

    private String tourName;

    private LocalDate tourDate;

    @ManyToOne
    private HikingTrail hikingTrail;

}
