package com.hiking.util;

import java.security.SecureRandom;

/**
 * @developer -- ilkercolakoglu
 */
public class BookingUtil {

    static SecureRandom ng = null;

    /**
     * provide unique number for bookings
     *
     * @return
     */
    public static String createUniqueNumber() {
        if (BookingUtil.ng == null) {
            ng = new SecureRandom();
        }
        long ngLong = ng.nextLong();
        ngLong = Math.abs(ngLong);
        return String.valueOf(ngLong);
    }
}
