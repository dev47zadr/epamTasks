package com.epamTasks.task5.services.filesearch;

import java.io.File;
import java.util.List;

public interface FileSearcher {
    public <T extends File> List<T> filter(List<T> listOfFiles);
    public void setNext(FileSearcher fileSearcher);
}
