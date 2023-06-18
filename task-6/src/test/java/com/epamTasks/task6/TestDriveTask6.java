package com.epamTasks.task6;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask6 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 6";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 6";
    }
}