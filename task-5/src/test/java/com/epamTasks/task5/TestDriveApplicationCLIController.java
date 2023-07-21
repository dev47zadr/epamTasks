package com.epamTasks.task5;

import com.epamTasks.task5.controllers.ApplicationCLIController;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestDriveApplicationCLIController {
    private static PrintStream originalPrintStream;
    private static PrintStream testPrintStream;
    private static ByteArrayOutputStream buffer;
    private ApplicationCLIController controller;

    static {
        buffer = new ByteArrayOutputStream();
        originalPrintStream = System.out;
        testPrintStream = new PrintStream(buffer);
    }

    @Before
    public void before() {
        turnOffOutput();
    }

    @Test
    public void testConstructor() {
        setDataInput(new String[]{
                "./",
                "n",
                "n",
                "n",
                "n"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Do you want to use a size range for the criteria?\n" +
                        "Y/n:src\n" +
                        "pom.xml\n" +
                        "target\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorName() {
        setDataInput(new String[]{
                "./",
                "y",
                "pom",
                "n",
                "n",
                "n"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Enter file name. (without extension)\n" +
                        "Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Do you want to use a size range for the criteria?\n" +
                        "Y/n:pom.xml\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorExtension() {
        setDataInput(new String[]{
                "./",
                "n",
                "y",
                "xml",
                "n",
                "n"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Enter extension.\n" +
                        "Do you want to use a time range for the criteria?\n" +
                        "Y/n:Do you want to use a size range for the criteria?\n" +
                        "Y/n:pom.xml\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorRangeOfDate_1() {
        setDataInput(new String[]{
                "./src/test",
                "n",
                "n",
                "y",
                "2023.07.13 17:24:37",
                "2099.01.01 00:00:00",
                "n"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Enter start date-time.\n" +
                        "yyyy.mm.dd hh:mm:ss :Enter finish date-time.\n" +
                        "yyyy.mm.dd hh:mm:ss :Do you want to use a size range for the criteria?\n" +
                        "Y/n:LoremIpsum.txt\n" +
                        "test.txt\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorRangeOfDate_2() {
        setDataInput(new String[]{
                "./src/test",
                "n",
                "n",
                "y",
                "2023.07.13 17:24:38",
                "2099.01.01 00:00:00",
                "n"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Enter start date-time.\n" +
                        "yyyy.mm.dd hh:mm:ss :Enter finish date-time.\n" +
                        "yyyy.mm.dd hh:mm:ss :Do you want to use a size range for the criteria?\n" +
                        "Y/n:LoremIpsum.txt\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorRangeOfSize_1() {
        setDataInput(new String[]{
                "./src/test",
                "n",
                "n",
                "n",
                "y",
                "Byte",
                "0",
                "4096"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Do you want to use a size range for the criteria?\n" +
                        "Y/n:Enter unit of size.\n" +
                        "Byte\n" +
                        "KB\n" +
                        "MB\n" +
                        "GB\n" +
                        "TB\n" +
                        "PB\n" +
                        "Enter min size.\n" +
                        "Enter max size.\n" +
                        "LoremIpsum.txt\n" +
                        "test.txt\n" +
                        "java\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorRangeOfSize_2() {
        setDataInput(new String[]{
                "./src/test",
                "n",
                "n",
                "n",
                "y",
                "KB",
                "0",
                "4"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Do you want to use a size range for the criteria?\n" +
                        "Y/n:Enter unit of size.\n" +
                        "Byte\n" +
                        "KB\n" +
                        "MB\n" +
                        "GB\n" +
                        "TB\n" +
                        "PB\n" +
                        "Enter min size.\n" +
                        "Enter max size.\n" +
                        "LoremIpsum.txt\n" +
                        "test.txt\n" +
                        "java\n",
                getOutput()
        );
    }

    @Test
    public void testConstructorRangeOfSize_3() {
        setDataInput(new String[]{
                "./src/test",
                "n",
                "n",
                "n",
                "y",
                "KB",
                "0",
                "3"
        });

        new ApplicationCLIController();
        assertEquals(
                "To find file or dir follow instructions.\n" +
                        "Enter path to source folder:\n" +
                        "Do you want to use a file name for the criteria?\n" +
                        "Y/n:Do you want to use a extension for the criteria?\n" +
                        "Y/n:Do you want to use a time range for the criteria?\n" +
                        "Y/n:Do you want to use a size range for the criteria?\n" +
                        "Y/n:Enter unit of size.\n" +
                        "Byte\n" +
                        "KB\n" +
                        "MB\n" +
                        "GB\n" +
                        "TB\n" +
                        "PB\n" +
                        "Enter min size.\n" +
                        "Enter max size.\n" +
                        "LoremIpsum.txt\n" +
                        "test.txt\n",
                getOutput()
        );
    }

    private static void setDataInput(String[] strings) {
        String str = Stream.of(strings).reduce("", (result, s) -> result + s + "\n");
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(inputStream);
    }

    private static String getOutput() {
        String result = buffer.toString();
        buffer.reset();
        return result;
    }

    private static void turnOnOutput() {
        System.setOut(originalPrintStream);
    }

    private static void turnOffOutput() {
        System.setOut(testPrintStream);
    }
}
