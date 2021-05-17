package com.hiking.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @developer -- ilkercolakoglu
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class HikerDTO extends BaseDTO{

    private String hikerName;

    @NotNull(message = "hikerSurname field is mandatory")
    private String hikerSurname;

    @NotNull(message = "age field is mandatory")
    private int age;

}
