package com.epamTasks.task12;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask12 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 12";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 12";
    }
}