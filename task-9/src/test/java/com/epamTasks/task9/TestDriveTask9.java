package com.epamTasks.task9;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask9 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 9";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 9";
    }
}