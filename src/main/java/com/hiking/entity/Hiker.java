package com.hiking.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @developer -- ilkercolakoglu
 */

@Data
@Entity
@Table(name = "HIKERS")
public class Hiker extends BaseEntity {

    private String hikerName;

    private String hikerSurname;

    private int age;




}
