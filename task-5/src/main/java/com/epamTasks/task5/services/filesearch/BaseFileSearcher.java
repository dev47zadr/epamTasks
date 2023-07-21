package com.epamTasks.task5.services.filesearch;

abstract public class BaseFileSearcher implements FileSearcher{
    protected FileSearcher next;
    public void setNext(FileSearcher next) {
        this.next = next;
    }
}
