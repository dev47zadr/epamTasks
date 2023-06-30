package com.epamTasks.task3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

public class ArraySet<E> extends ArrayList<E> {
    private void checkDuplicate(Collection<? extends E> elements) {
        var arrElements = elements.toArray();
        for (int i = 0; i < arrElements.length; i++) {
            for (int j = 0; j < arrElements.length; j++) {
                if (arrElements[i] == arrElements[j] && i != j) {
                    throw new DuplicateValueException();
                }
            }
        }
        for (E element : elements) {
            if (this.contains(element))
                throw new DuplicateValueException();
        }
    }

    private void checkDuplicate(E element) {
        if (this.contains(element))
            throw new DuplicateValueException();
    }

    public ArraySet() {
        super();
    }

    public ArraySet(int initialCapacity) {
        super(initialCapacity);
    }

    public ArraySet(List<E> asList) {
        super();
        this.checkDuplicate(asList);
        this.addAll(asList);
    }

    public boolean add(E e) {
        this.checkDuplicate(e);
        return super.add(e);
    }

    public void add(int index, E element) {
        this.checkDuplicate(element);
        super.add(index, element);
    }

    public E set(int index, E element) {
        this.checkDuplicate(element);
        return super.set(index, element);
    }

    public boolean addAll(Collection<? extends E> c) {
        this.checkDuplicate(c);
        return super.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        this.checkDuplicate(c);
        return super.addAll(index, c);
    }

    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }
}
