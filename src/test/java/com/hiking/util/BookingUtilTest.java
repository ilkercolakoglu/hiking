package com.hiking.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * @developer -- ilkercolakoglu
 */

public class BookingUtilTest {

    @Test
    public void should_create_different_numbers() {
        String first = BookingUtil.createUniqueNumber();
        String second = BookingUtil.createUniqueNumber();
        Assert.assertNotEquals(first, second);

    }
}
