package com.epamTasks.task7;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask7 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 7";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 7";
    }
}