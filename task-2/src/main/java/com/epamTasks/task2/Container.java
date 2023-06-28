package com.epamTasks.task2;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public class Container<E> implements List<E> {
    protected final int DEFAULT_SIZE = 10;
    protected int modifySize = 0;
    protected Object[] initElements = new Object[0];
    protected Object[] modifyElements = new Object[this.DEFAULT_SIZE];

    public Container() {
    }

    public Container(List<E> newElements) {
        this.initElements = newElements.toArray();
    }

    protected void resize(int newRange) {
        this.modifyElements = Arrays.copyOf(this.modifyElements, newRange);
    }

    protected void resizeIfNeeded() {
        if (this.modifySize == this.modifyElements.length) {
            this.resize(this.modifySize * 2);
        }
        double fullness = (double) this.modifyElements.length / this.modifySize * 100;
        double threshold = (double) this.modifyElements.length / 30 * 100;
        if (fullness <= threshold && this.modifySize * 2 <= this.DEFAULT_SIZE) {
            this.resize(this.modifyElements.length / 2);
        }
    }

    protected void checkModifiableIndex(int index) {
        if (index < this.initElements.length) {
            throw new UnmodifiableListException("Min modifiable index:" + this.initElements.length + " actual:" + index);
        }
    }

    protected int convertToModifyIndex(int index) {
        return index - this.initElements.length;
    }

    public int size() {
        return this.initElements.length + this.modifySize;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean contains(Object o) {
        Objects.requireNonNull(o);
        return this.indexOf(o) >= 0;
    }

    public Object[] toArray() {
        Object[] resultElements = Arrays.copyOf(this.initElements, this.size());
        System.arraycopy(this.modifyElements, 0, resultElements, this.initElements.length, this.modifySize);
        return resultElements;
    }

    public <T> T[] toArray(T[] a) {
        if (a.length < this.size()) a = (T[]) Arrays.copyOf(this.initElements, this.size());
        else System.arraycopy(this.initElements, 0, a, 0, this.initElements.length);

        System.arraycopy(this.modifyElements, 0, a, this.initElements.length, this.modifySize);
        return a;
    }

    public void add(int index, E element) {
        Objects.checkIndex(index, this.size() + 1);
        this.checkModifiableIndex(index);
        int modIndex = this.convertToModifyIndex(index);
        this.resizeIfNeeded();
        System.arraycopy(this.modifyElements, modIndex, this.modifyElements, modIndex + 1, this.modifySize++ - modIndex);
        this.modifyElements[modIndex] = element;
    }

    public boolean add(E e) {
        Objects.requireNonNull(e);
        this.add(this.size(), e);
        return true;
    }

    public E remove(int index) {
        Objects.checkIndex(index, this.size());
        this.checkModifiableIndex(index);
        int modIndex = this.convertToModifyIndex(index);
        E oldElement = (E) this.modifyElements[modIndex];
        System.arraycopy(this.modifyElements, modIndex + 1, this.modifyElements, modIndex, --this.modifySize - modIndex);
        return oldElement;
    }

    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        for (int i = this.initElements.length; i < this.size(); i++) {
            Object element = this.modifyElements[this.convertToModifyIndex(i)];
            if (element.equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean containsAll(Collection<?> c) {
        for (var element : c) {
            Objects.requireNonNull(element);
            if (this.indexOf(element) < 0) {
                return false;
            }
        }
        return true;
    }

    public boolean addAll(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        return this.addAll(this.size(), c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        Objects.checkIndex(index, this.size() + 1);
        this.checkModifiableIndex(index);
        Objects.requireNonNull(c);
        int modIndex = this.convertToModifyIndex(index);
        if (this.modifyElements.length < this.modifySize + c.size()) {
            this.resize(this.modifyElements.length * 2);
        }
        System.arraycopy(this.modifyElements, modIndex, this.modifyElements, modIndex + c.size(), this.modifySize - modIndex);
        System.arraycopy(c.toArray(), 0, this.modifyElements, modIndex, c.size());
        this.modifySize += c.size();
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        boolean isRemoved = false;
        for (int i = this.initElements.length; i < this.size(); i++) {
            Object element = this.modifyElements[this.convertToModifyIndex(i)];
            for (var val : c) {
                if (element.equals(val)) {
                    isRemoved = true;
                    this.remove(i--);
                    break;
                }
            }
        }
        return isRemoved;
    }

    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        Object[] newModifyElements = new Object[this.modifyElements.length];
        int newModifySize = 0;
        for (int i = this.initElements.length; i < this.size(); i++) {
            Object element = this.modifyElements[this.convertToModifyIndex(i)];
            for (var val : c) {
                if (element.equals(val)) {
                    newModifyElements[newModifySize++] = element;
                    break;
                }
            }
        }
        boolean isChanges = this.modifySize != newModifySize;
        this.modifyElements = newModifyElements;
        this.modifySize = --newModifySize;
        this.resizeIfNeeded();
        this.modifySize = ++newModifySize;
        return isChanges;
    }

    public void clear() {
        this.modifyElements = new Object[this.DEFAULT_SIZE];
        this.modifySize = 0;
    }

    public E get(int index) {
        Objects.checkIndex(index, this.size());
        if (index < this.initElements.length) {
            return (E) initElements[index];
        }
        int modIndex = this.convertToModifyIndex(index);
        return (E) this.modifyElements[modIndex];
    }

    public E set(int index, E element) {
        Objects.requireNonNull(element);
        Objects.checkIndex(index, this.size());
        this.checkModifiableIndex(index);
        int modIndex = this.convertToModifyIndex(index);
        E resultElement = (E) this.modifyElements[modIndex];
        this.modifyElements[modIndex] = element;
        return resultElement;
    }

    public int indexOf(Object o) {
        Objects.requireNonNull(o);
        for (int i = 0; i < this.size(); i++) {
            E element = this.get(i);
            if (element.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        Objects.requireNonNull(o);
        for (int i = this.size() - 1; i >= 0; i--) {
            E element = this.get(i);
            if (element.equals(o)) {
                return i;
            }
        }
        return -1;
    }

    public Iterator<E> iterator() {
        return new ContainerIterator();
    }

    public ListIterator<E> listIterator() {
        return new ContainerListIterator();
    }

    public ListIterator<E> listIterator(int index) {
        return new ContainerListIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex) {
        Objects.checkIndex(fromIndex, this.size());
        Objects.checkIndex(toIndex, this.size());

        int elementsSize = 0;
        Object[] elements = new Object[toIndex - fromIndex];

        if (fromIndex < this.initElements.length) {
            System.arraycopy(this.initElements, fromIndex, elements, 0, this.initElements.length - fromIndex);
            elementsSize = this.initElements.length - fromIndex;
            fromIndex = this.convertToModifyIndex(this.initElements.length);
            toIndex = this.convertToModifyIndex(toIndex);
        }
        System.arraycopy(this.modifyElements, fromIndex, elements, elementsSize, toIndex - fromIndex);
        return new Container<E>(Arrays.asList((E[]) elements));
    }

    private class ContainerIterator implements Iterator<E> {
        private int cursor = 0;

        public boolean hasNext() {
            return this.cursor < Container.this.size();
        }

        public E next() {
            return (E) Container.this.get(this.cursor++);
        }
    }

    private class ContainerListIterator implements ListIterator<E> {
        private int cursor = 0;

        public ContainerListIterator() {
        }

        public ContainerListIterator(int index) {
            this.cursor = index;
        }

        public boolean hasNext() {
            return this.cursor < Container.this.size();
        }

        public E next() {
            return (E) Container.this.get(this.cursor++);
        }

        public boolean hasPrevious() {
            return this.cursor > 0;
        }

        public E previous() {
            return (E) Container.this.get(this.cursor--);
        }

        public int nextIndex() {
            return this.cursor;
        }

        public int previousIndex() {
            return this.cursor - 1;
        }

        public void remove() {
            Container.this.checkModifiableIndex(this.cursor);
            Container.this.remove(this.cursor);
        }

        public void set(E e) {
            Container.this.checkModifiableIndex(this.cursor);
            Container.this.set(this.cursor, e);
        }

        public void add(E e) {
            Container.this.checkModifiableIndex(this.cursor);
            Container.this.add(this.cursor, e);
        }
    }
}
