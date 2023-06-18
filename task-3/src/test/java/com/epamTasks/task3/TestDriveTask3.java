package com.epamTasks.task3;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask3 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 3";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 3";
    }
}