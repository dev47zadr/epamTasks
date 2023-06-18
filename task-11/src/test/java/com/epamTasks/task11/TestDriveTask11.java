package com.epamTasks.task11;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask11 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 11";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 11";
    }
}