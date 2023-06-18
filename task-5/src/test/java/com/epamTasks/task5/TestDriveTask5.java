package com.epamTasks.task5;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask5 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 5";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 5";
    }
}