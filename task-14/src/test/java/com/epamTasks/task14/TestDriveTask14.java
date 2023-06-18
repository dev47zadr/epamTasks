package com.epamTasks.task14;

import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestDriveTask14 {
    @Test
    public void testPrintMessage() {
        String expected = "Test 14";
        String actual = getMessage();
        assertEquals(expected, actual);
    }

    private String getMessage() {
        return "Test 14";
    }
}