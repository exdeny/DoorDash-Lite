package com.dgoliy.doordashlite.common;

/**
 * Created by dgoliy on 2/11/18.
 */

public class Assertions {
    public static void assertNotNull(Object o) {
        if (o == null) throw new AssertionError(o + " is null!");
    }
}
