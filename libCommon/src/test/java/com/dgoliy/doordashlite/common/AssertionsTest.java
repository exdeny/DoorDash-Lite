package com.dgoliy.doordashlite.common;

import org.junit.Test;

public class AssertionsTest {
    @Test(expected = AssertionError.class)
    public void assertNotNull_errorThrown() {
        Assertions.assertNotNull(null);
    }

    @Test
    public void assertNotNull_errorNotThrown() {
        Assertions.assertNotNull(new Object());
    }
}