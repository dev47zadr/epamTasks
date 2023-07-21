package com.epamTasks.task5.services.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcherExtension extends BaseFileSearcher implements FileSearcher{
    private String extension;
    public FileSearcherExtension(String extension) {
        this.extension = extension;
    }
    public <T extends File> List<T> filter(List<T> listOfFiles) {
        List<T> filteredListOfFiles = new ArrayList<>();
        for (T file : listOfFiles) {
            if (getExtension(file.getName()).equals(extension)) {
                filteredListOfFiles.add(file);
            }
        }
        return filteredListOfFiles;
    }
    private String getExtension(String fileName) {
        int firstIndex = fileName.lastIndexOf(".") + 1;
        return fileName.substring(firstIndex);
    }
}
