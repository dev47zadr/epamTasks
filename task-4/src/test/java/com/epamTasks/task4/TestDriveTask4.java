package com.epamTasks.task4;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask4 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 4";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 4";
    }
}