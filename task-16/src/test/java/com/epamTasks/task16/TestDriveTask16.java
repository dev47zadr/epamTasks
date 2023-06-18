package com.epamTasks.task16;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask16 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 16";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 16";
    }
}