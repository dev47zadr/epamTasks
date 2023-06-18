package com.epamTasks.task2;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask2 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 2";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 2";
    }
}