package com.epamTasks.task5.models;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class FileWrapper extends File implements Iterable<String> {
    public FileWrapper(String pathName) {
        super(pathName);
        if (!isFile()) {
            throw new IllegalArgumentException("the specified path is not a file:" + getAbsolutePath());
        }
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private BufferedReader reader;
            private String next;

            {
                try {
                    reader = new BufferedReader(new FileReader(FileWrapper.this));
                } catch (FileNotFoundException ignore) {
                }
                try {
                    next = reader.readLine();
                } catch (IOException ignore) {
                }
            }

            public boolean hasNext() {
                return !Objects.isNull(reader) && !Objects.isNull(next);
            }

            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String result = next;
                try {
                    next = reader.readLine();
                } catch (IOException ignore) {
                }
                return result;
            }
        };
    }
}
