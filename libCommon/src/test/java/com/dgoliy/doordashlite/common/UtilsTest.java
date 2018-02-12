package com.dgoliy.doordashlite.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {
    private static final double DELTA = 1e-15;

    @Test
    public void centsToDollars_isCorrect() throws Exception {
        assertEquals(Utils.centsToDollars(399), 3.99, DELTA);
        assertEquals(Utils.centsToDollars(1), 0.01, DELTA);
        assertEquals(Utils.centsToDollars(100000), 1000.00, DELTA);
        assertEquals(Utils.centsToDollars(-599), -5.99, DELTA);
        assertEquals(Utils.centsToDollars(0), 0.0, DELTA);
    }
}