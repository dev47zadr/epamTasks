package com.epamTasks.task13;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask13 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 13";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 13";
    }
}