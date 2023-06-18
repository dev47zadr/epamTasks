package com.epamTasks.task8;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask8 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 8";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 8";
    }
}