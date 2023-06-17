package com.my.task1;
import com.my.task1.entity.*;

import java.util.*;
import java.util.function.Consumer;

public class ProductContainer implements List<Product> {
    private Product[] products = new Product[10];
    private int length = 0;
    public int size() {
        return this.length;
    }

    public boolean isEmpty() {
        return this.length == 0;
    }
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    public Iterator<Product> iterator() {
        return new Itr();
    }

    public Object[] toArray() {
        return Arrays.copyOf(this.products, this.length);
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] ts) {
        if (ts.length < this.length) {
            return (T[]) Arrays.copyOf(this.products, this.length, ts.getClass());
        } else {
            System.arraycopy(this.products, 0, ts, 0, this.length);
            if (ts.length > this.length) {
                ts[this.length] = null;
            }

            return ts;
        }
    }

    public boolean add(Product product) {
        return false;
    }

    public boolean remove(Object o) {
        return false;
    }

    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    public boolean addAll(Collection<? extends Product> collection) {
        return false;
    }

    public boolean addAll(int i, Collection<? extends Product> collection) {
        return false;
    }

    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    public void clear() {

    }

    public Product get(int i) {
        return null;
    }

    public Product set(int i, Product product) {
        return null;
    }

    public void add(int i, Product product) {

    }

    public Product remove(int i) {
        return null;
    }

    public int indexOf(Object o) {
        return this.indexOfRange(o, 0, this.length);
    }
    int indexOfRange(Object o, int start, int end) {
        Object[] es = this.products;
        int i;
        if (o == null) {
            for(i = start; i < end; ++i) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for(i = start; i < end; ++i) {
                if (o.equals(es[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    public int lastIndexOf(Object o) {
        return 0;
    }

    public ListIterator<Product> listIterator() {
        return null;
    }

    public ListIterator<Product> listIterator(int i) {
        return null;
    }

    public List<Product> subList(int i, int i1) {
        return null;
    }
    private class Itr implements Iterator<Product> {
        private int current = 0;
        public boolean hasNext() {
            return ProductContainer.this.size() > this.current;
        }
        public Product next() {
            return ProductContainer.this.products[this.current++];
        }
    }
}
