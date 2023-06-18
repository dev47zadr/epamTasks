package com.epamTasks.task10;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask10 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 10";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 10";
    }
}