package com.epamTasks.task5;

import com.epamTasks.task5.models.FileWrapper;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;

public class TestDriveFileWrapper {
    @Test
    public void testConstructor_1() {
        try {
            FileWrapper fileWrapper = new FileWrapper("./");
            fail("We expected exception IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            assertTrue("We expected exception IllegalArgumentException.", true);
        }
    }

    @Test
    public void testConstructor_2() {
        try {
            FileWrapper fileWrapper = new FileWrapper("./src/test/LoremIpsum.txt");
            assertTrue("We doesn't expected exception IllegalArgumentException.", true);
        } catch (IllegalArgumentException e) {
            fail("We doesn't expected exception IllegalArgumentException.");
        }
    }

    @Test
    public void testLoop_1() {
        String[] expectedStrings = new String[]{
                "row 1",
                "row 2",
                "next row is empty",
                "",
                "last row"
        };
        List<String> actualListStrings = new ArrayList<>();

        try {
            FileWrapper fileWrapper = new FileWrapper("./src/test/LoremIpsum.txt");
            for (String row : fileWrapper) {
                actualListStrings.add(row);
            }
        } catch (IllegalArgumentException e) {
            fail("We doesn't expected exception IllegalArgumentException.");
        }

        assertArrayEquals(expectedStrings, actualListStrings.toArray(new String[0]));
    }

    @Test
    public void testLoop_2() {
        String[] expectedStrings = new String[]{
                "row 1",
                "row 2",
                "next row is empty",
                "",
                "last row"
        };
        List<String> actualListStrings = new ArrayList<>();

        try {
            FileWrapper fileWrapper = new FileWrapper("./src/test/LoremIpsum.txt");
            Iterator<String> itr = fileWrapper.iterator();
            while (itr.hasNext()) {
                String row = itr.next();
                actualListStrings.add(row);
            }
        } catch (IllegalArgumentException e) {
            fail("We doesn't expected exception IllegalArgumentException.");
        }

        assertArrayEquals(expectedStrings, actualListStrings.toArray(new String[0]));
    }

    @Test
    public void testIterator_1() {
        try {
            FileWrapper fileWrapper = new FileWrapper("./src/test/test.txt");
            Iterator<String> itr = fileWrapper.iterator();
            itr.next();
            fail("We expected exception IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            fail("We doesn't expected exception IllegalArgumentException.");
        } catch (NoSuchElementException e) {
            assertTrue("We expected exception NoSuchElementException.", true);
        }
    }

    @Test
    public void testIterator_2() {
        try {
            FileWrapper fileWrapper = new FileWrapper("./src/test/test.txt");
            Iterator<String> itr = fileWrapper.iterator();
            assertFalse("We expected that next row don't exist.", itr.hasNext());
        } catch (IllegalArgumentException e) {
            fail("We doesn't expected exception IllegalArgumentException.");
        } catch (NoSuchElementException e) {
            fail("We doesn't expected exception NoSuchElementException.");
        }
    }
}