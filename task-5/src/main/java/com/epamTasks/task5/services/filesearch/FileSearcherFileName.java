package com.epamTasks.task5.services.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcherFileName extends BaseFileSearcher implements FileSearcher{
    private String fileName;
    public FileSearcherFileName(String fileName) {
        this.fileName = fileName;
    }
    public <T extends File> List<T> filter(List<T> listOfFiles) {
        List<T> filteredListOfFiles = new ArrayList<>();
        for (T file : listOfFiles) {
            if (getNameWithoutExtension(file.getName()).equals(fileName)) {
                filteredListOfFiles.add(file);
            }
        }
        return filteredListOfFiles;
    }
    private String getNameWithoutExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex == -1)
            return fileName;
        return fileName.substring(0, lastIndex);
    }
}
