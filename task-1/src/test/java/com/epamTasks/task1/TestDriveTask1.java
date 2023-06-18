package com.epamTasks.task1;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask1 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 1";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 1";
    }
}