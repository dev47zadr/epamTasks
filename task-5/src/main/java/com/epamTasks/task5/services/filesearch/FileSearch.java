package com.epamTasks.task5.services.filesearch;

import java.io.File;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FileSearch {
    private File dir;
    private boolean isFileName = false;
    private String fileName;
    private boolean isFileExtension = false;
    private String fileExtension;
    private boolean isRangeOfDate = false;
    private FileTime rangeOfDateFrom;
    private FileTime rangeOfDateTo;
    private boolean isRangeOfSize = false;
    private Long rangeOfSizeFrom;
    private Long rangeOfSizeTo;
    private FileSearcherRangeOfSize.Type rangeOfSizeType;
    public FileSearch(File dir) {
        Objects.requireNonNull(dir);
        this.dir = dir;
    }
    public void setDir(File dir) {
        Objects.requireNonNull(dir);
        this.dir = dir;
    }
    public File getDir() {
        return dir;
    }
    public void setFileName(String fileName) {
        Objects.requireNonNull(fileName);
        this.fileName = fileName;
        this.isFileName = true;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileExtension(String fileExtension) {
        Objects.requireNonNull(fileExtension);
        this.fileExtension = fileExtension;
        this.isFileExtension = true;
    }
    public String getFileExtension() {
        return fileExtension;
    }
    public void setRangeOfDateFrom(FileTime rangeOfDateFrom) {
        Objects.requireNonNull(rangeOfDateFrom);
        if (!Objects.isNull(this.rangeOfDateTo) && rangeOfDateFrom.compareTo(this.rangeOfDateTo) >= 0) {
            throw new IllegalArgumentException("rangeOfDateFrom bigger then rangeOfDateTo");
        }
        this.rangeOfDateFrom = rangeOfDateFrom;
        if (!Objects.isNull(this.rangeOfDateTo) ) {
            this.isRangeOfDate = true;
        }
    }
    public FileTime getRangeOfDateFrom() {
        return rangeOfDateFrom;
    }
    public void setRangeOfDateTo(FileTime rangeOfDateTo) {
        Objects.requireNonNull(rangeOfDateTo);
        if (!Objects.isNull(this.rangeOfDateFrom) && rangeOfDateTo.compareTo(this.rangeOfDateFrom) <= 0) {
            throw new IllegalArgumentException("rangeOfDateTo less then rangeOfDateFrom");
        }
        this.rangeOfDateTo = rangeOfDateTo;
        if (!Objects.isNull(this.rangeOfDateFrom) ) {
            this.isRangeOfDate = true;
        }
    }
    public FileTime getRangeOfDateTo() {
        return rangeOfDateTo;
    }
    public void setRangeOfSizeFrom(long rangeOfSizeFrom) {
        if (!Objects.isNull(this.rangeOfSizeTo) && rangeOfSizeFrom >= this.rangeOfSizeTo) {
            throw new IllegalArgumentException("rangeOfSizeFrom bigger then rangeOfSizeTo");
        }
        this.rangeOfSizeFrom = rangeOfSizeFrom;
        if (!Objects.isNull(this.rangeOfSizeTo) ) {
            this.isRangeOfSize = true;
        }
    }
    public long getRangeOfSizeFrom() {
        return rangeOfSizeFrom;
    }
    public void setRangeOfSizeTo(long rangeOfSizeTo) {
        if (!Objects.isNull(this.rangeOfSizeFrom) && rangeOfSizeTo <= this.rangeOfSizeFrom) {
            throw new IllegalArgumentException("rangeOfSizeTo less then rangeOfSizeFrom");
        }
        this.rangeOfSizeTo = rangeOfSizeTo;
        if (!Objects.isNull(this.rangeOfSizeFrom) ) {
            this.isRangeOfSize = true;
        }
    }
    public long getRangeOfSizeTo() {
        return rangeOfSizeTo;
    }
    public void setRangeOfSizeType(String rangeOfSizeType) {
        Objects.requireNonNull(rangeOfSizeType);
        this.rangeOfSizeType = Stream.of(FileSearcherRangeOfSize.Type.values())
                .filter( t -> t.toString().equals(rangeOfSizeType))
                .findAny()
                .orElseGet( () -> FileSearcherRangeOfSize.Type.MB);
    }
    public FileSearcherRangeOfSize.Type getRangeOfSizeType() {
        return rangeOfSizeType;
    }
    public List<String> getSizeOptions() {
        return Stream.of(FileSearcherRangeOfSize.Type.values())
                .map(FileSearcherRangeOfSize.Type::toString)
                .toList();
    }
    public List<File> getResult() {
        List<FileSearcher> fileSearcherList = new ArrayList<>();
        if (isFileName) {
            fileSearcherList.add(new FileSearcherFileName(fileName));
        }
        if (isFileExtension) {
            fileSearcherList.add(new FileSearcherExtension(fileExtension));
        }
        if (isRangeOfDate) {
            fileSearcherList.add(new FileSearcherRangeOfDate(rangeOfDateFrom, rangeOfDateTo));
        }
        if (isRangeOfSize) {
            fileSearcherList.add(new FileSearcherRangeOfSize(rangeOfSizeType, rangeOfSizeFrom, rangeOfSizeTo));
        }

        if (fileSearcherList.size() > 0) {
            FileSearcher head = fileSearcherList.get(0);
            for (FileSearcher next: fileSearcherList) {
                if (next.equals(fileSearcherList.get(0)))
                    continue;
                head.setNext(next);
                head = next;
            }
            return head.filter(getFilesOfDir());
        }
        return new ArrayList<>(getFilesOfDir());
    }
    private List<File> getFilesOfDir() {
        return Stream.of(Objects.requireNonNull(dir.listFiles())).toList();
    }
}
