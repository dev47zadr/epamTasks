package com.epamTasks.task15;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask15 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 15";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 15";
    }
}