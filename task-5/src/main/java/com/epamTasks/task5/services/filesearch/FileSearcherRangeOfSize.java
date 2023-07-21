package com.epamTasks.task5.services.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcherRangeOfSize extends BaseFileSearcher implements FileSearcher {
    private long from;
    private long to;
    private Type type;

    public FileSearcherRangeOfSize(Type type, long from, long to) {
        this.type = type;
        this.from = from * calculateCoefficient(type);
        this.to = to * calculateCoefficient(type);
    }

    public <T extends File> List<T> filter(List<T> listOfFiles) {
        List<T> filteredListOfFiles = new ArrayList<>();
        for (T file : listOfFiles) {
            if (file.length() >= from && file.length() <= to) {
                filteredListOfFiles.add(file);
            }
        }
        return filteredListOfFiles;
    }

    private long calculateCoefficient(Type type) {
        long coefficient = 1;
        switch (type) {
            case PB:
                coefficient *= 1024;
            case GB:
                coefficient *= 1024;
            case MB:
                coefficient *= 1024;
            case KB:
                coefficient *= 1024;
            case Byte:
        }
        return coefficient;
    }

    public enum Type {Byte, KB, MB, GB, TB, PB}
}
