package com.epamTasks.task5.services.filesearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;

public class FileSearcherRangeOfDate extends BaseFileSearcher implements FileSearcher {
    private FileTime from;
    private FileTime to;

    public FileSearcherRangeOfDate(FileTime from, FileTime to) {
        this.from = from;
        this.to = to;
    }

    public <T extends File> List<T> filter(List<T> listOfFiles) {
        List<T> filteredListOfFiles = new ArrayList<>();
        for (T file : listOfFiles) {
            try {
                if (getLastModifiedTime(file).compareTo(from) >= 0 && getLastModifiedTime(file).compareTo(to) <= 0) {
                    filteredListOfFiles.add(file);
                }
            } catch (IOException ignore) {
            }
        }
        return filteredListOfFiles;
    }

    private FileTime getLastModifiedTime(File file) throws IOException {
        Path filePath = file.getAbsoluteFile().toPath();
        BasicFileAttributes attributes = Files.readAttributes(filePath, BasicFileAttributes.class);
        return attributes.lastModifiedTime();
    }
}
