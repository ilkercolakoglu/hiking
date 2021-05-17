package com.hiking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * @developer -- ilkercolakoglu
 */

@Data
@Entity
@Table(name = "BOOKINGS")
public class Booking extends BaseEntity{

    @Column
    private String bookingNumber;

    @ManyToOne
    private Tour tour;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Hiker> hikerSet;
}
