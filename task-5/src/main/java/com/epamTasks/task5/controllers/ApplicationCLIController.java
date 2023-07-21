package com.epamTasks.task5.controllers;

import com.epamTasks.task5.services.filesearch.FileSearch;

import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ApplicationCLIController {
    private final InputStream in = System.in;
    private final PrintStream out = System.out;
    private final Scanner scanner = new Scanner(in);
    private FileSearch fileSearchService;
    public ApplicationCLIController() {
        printGeneral();
        handleOptions();
        printResult();
    }
    private void printGeneral() {
        out.println("To find file or dir follow instructions.");
    }
    private void handleOptions() {
        handleDir();
        handleName();
        handleExtension();
        handleRangeDate();
        handleRangeSize();
    }
    private void printResult() {
        fileSearchService.getResult().stream()
                .map(File::getName)
                .forEach(out::println);
    }
    private FileTime convertToFileTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return FileTime.from(dateTime.toInstant(ZoneOffset.UTC));
    }
    private long convertToLong(String sizeString) {
        return Long.parseLong(sizeString);
    }
    private boolean validateDir(String dir) {
        return dir.matches("^(.*)\\/([^\\/]*)$");
    }
    private boolean validateDate(String date) {
        try {
            convertToFileTime(date);
            return true;
        } catch(DateTimeParseException e) {
            return false;
        }
    }
    private boolean validateRangeDate(String fromString, String toString) {
        FileTime fromFileTime = convertToFileTime(fromString);
        FileTime toFileTime = convertToFileTime(toString);
        return fromFileTime.compareTo(toFileTime) < 0;
    }
    private boolean validateRangeSize(long from, long to) {
        return from < to;
    }
    private boolean validateUnitOfSieOption(String unit) {
        return fileSearchService.getSizeOptions().stream().anyMatch(s -> s.equals(unit));
    }
    private boolean validateLong(String longString) {
        try {
            Long.parseLong(longString);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean validateName(String name) {
        return name.matches("^[^/]+$");
    }
    private boolean validateExtension(String extension) {
        return extension.matches("^\\w+$");
    }
    private void handleDir() {
        while(true) {
            out.println("Enter path to source folder:");
            String dirPath = scanner.nextLine();
            if (!validateDir(dirPath))
                continue;
            try {
                File dir = new File(dirPath);
                fileSearchService = new FileSearch(dir);
                break;
            } catch (Exception ignore) {}
        }
    }
    private void handleRangeDate() {
        out.println("Do you want to use a time range for the criteria?");
        out.print("Y/n:");
        if (!scanner.nextLine().equalsIgnoreCase("y"))
            return;
        while(true) {
            String dateStringStart;
            String dateStringFinish;
            do {
                out.println("Enter start date-time.");
                out.print("yyyy.mm.dd hh:mm:ss :");
                dateStringStart = scanner.nextLine();
            } while (!validateDate(dateStringStart));
            do {
                out.println("Enter finish date-time.");
                out.print("yyyy.mm.dd hh:mm:ss :");
                dateStringFinish = scanner.nextLine();
            } while (!validateDate(dateStringFinish));


            if (validateRangeDate(dateStringStart,dateStringFinish)) {
                try {
                    fileSearchService.setRangeOfDateFrom(convertToFileTime(dateStringStart));
                    fileSearchService.setRangeOfDateTo(convertToFileTime(dateStringFinish));
                    break;
                } catch (Exception ignore) {}
            }
            out.println("The start date-time bigger then the finish date-time");
        }
    }
    private void handleRangeSize() {
        out.println("Do you want to use a size range for the criteria?");
        out.print("Y/n:");
        if (!scanner.nextLine().equalsIgnoreCase("y"))
            return;
        while (true) {
            out.println("Enter unit of size.");
            for (String option : fileSearchService.getSizeOptions()) {
                out.println(option);
            }
            String unit = scanner.nextLine();
            if (validateUnitOfSieOption(unit)) {
                try {
                    fileSearchService.setRangeOfSizeType(unit);
                    break;
                } catch (Exception ignore) {}
            }
            out.println("Unknown unit size.");
        }
        while(true) {
            String fromString;
            String toString;
            long from;
            long to;
            do {
                out.println("Enter min size.");
                fromString = scanner.nextLine();
            } while (!validateLong(fromString));
            do {
                out.println("Enter max size.");
                toString = scanner.nextLine();
            } while (!validateLong(toString));
            from = convertToLong(fromString);
            to = convertToLong(toString);
            if (validateRangeSize(from,to)) {
                try {
                    fileSearchService.setRangeOfSizeFrom(from);
                    fileSearchService.setRangeOfSizeTo(to);
                    break;
                } catch (Exception ignore) {}
            }
            out.println("The min size bigger then the max size.");
        }
    }
    private void handleName() {
        out.println("Do you want to use a file name for the criteria?");
        out.print("Y/n:");
        if (!scanner.nextLine().equalsIgnoreCase("y"))
            return;
        while(true) {
            out.println("Enter file name. (without extension)");
            String fileName = scanner.nextLine();
            if (validateName(fileName)) {
                try {
                    fileSearchService.setFileName(fileName);
                    break;
                } catch (Exception ignore) {}
            }
            out.println("Invalid file name.");
        }
    }
    private void handleExtension() {
        out.println("Do you want to use a extension for the criteria?");
        out.print("Y/n:");
        if (!scanner.nextLine().equalsIgnoreCase("y"))
            return;
        while(true) {
            out.println("Enter extension.");
            String extension = scanner.nextLine();
            if (validateExtension(extension)) {
                try {
                    fileSearchService.setFileExtension(extension);
                    break;
                } catch (Exception ignore) {}
            }
            out.println("Invalid extension.");
        }
    }
}
