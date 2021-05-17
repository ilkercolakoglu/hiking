package com.hiking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

/**
 * @developer -- ilkercolakoglu
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "HIKING_TRAILS")
public class HikingTrail extends BaseEntity{

    private String trailName;

    private LocalTime startTime;

    private LocalTime endTime;

    private int minAge;

    private int maxAge;

    private double price;

}
