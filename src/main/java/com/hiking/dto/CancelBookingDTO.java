package com.hiking.dto;

import lombok.Data;

/**
 * @developer -- ilkercolakoglu
 */

/**
 * This DTO has been created because of cancellation security.
 * Users can just be cancelled their booking with their surnames like airways reservation system.
 *
 */

@Data
public class CancelBookingDTO {

    private String bookingNumber;

    private String surname;
}
