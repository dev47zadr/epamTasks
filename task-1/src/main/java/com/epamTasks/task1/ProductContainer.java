package com.epamTasks.task1;

import com.epamTasks.task1.entity.*;

import java.util.*;
import java.util.function.Predicate;

public class ProductContainer implements List<Product> {
    private final int DEFAULT_SIZE = 10;
    private int size = 0;
    private Product[] products;

    public ProductContainer() {
        this.products = new Product[this.DEFAULT_SIZE];
    }

    public ProductContainer(int defaultSize) {
        if (defaultSize < this.DEFAULT_SIZE) {
            defaultSize = this.DEFAULT_SIZE;
        }
        this.products = new Product[defaultSize];
    }

    private void resizeIfNeeded() {
        if (this.size == this.products.length) {
            Product[] newProducts = new Product[this.products.length * 2];
            System.arraycopy(this.products, 0, newProducts, 0, this.size);
            this.products = newProducts;
        }
        if (this.products.length > this.DEFAULT_SIZE && this.products.length > 3 * this.size) {
            Product[] newProducts = new Product[this.products.length / 2];
            System.arraycopy(this.products, 0, newProducts, 0, this.size);
            this.products = newProducts;
        }
    }

    public boolean add(Product product) {
        Objects.requireNonNull(product);
        this.resizeIfNeeded();
        this.products[this.size++] = product;
        return true;
    }

    public void add(int i, Product product) {
        Objects.checkIndex(i, this.size+1);
        this.resizeIfNeeded();
        System.arraycopy(this.products, i, this.products, i + 1, this.size++ - i);
        this.products[i] = product;
    }

    public boolean addAll(Collection<? extends Product> collection) {
        Product[] oldProducts = Arrays.copyOf(this.products, this.products.length);
        int oldSize = this.size;
        for (Product product : collection) {
            if (!this.add(product))
                return false;
        }
        return true;
    }

    public boolean addAll(int i, Collection<? extends Product> collection) {
        Objects.checkIndex(i, this.size+1);
        Product[] newProducts = new Product[collection.size()];
        int arrayIndex = 0;
        for (Product product : collection) {
            Objects.requireNonNull(product);
            newProducts[arrayIndex++] = product;
        }
        if (this.products.length < this.size + newProducts.length) {
            this.products = Arrays.copyOf(this.products, this.products.length + newProducts.length);
        }
        System.arraycopy(this.products, i, this.products, i + newProducts.length, this.size - i);
        System.arraycopy(newProducts, 0, this.products, i, newProducts.length);
        this.size = this.size + newProducts.length;
        this.resizeIfNeeded();
        return true;
    }

    public Product set(int i, Product product) {
        Objects.checkIndex(i, this.size);
        Product previousProduct = this.products[i];
        this.products[i] = product;
        return previousProduct;
    }

    public Product get(int i) {
        Objects.checkIndex(i, this.size);
        return this.products[i];
    }

    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        int index = this.indexOf(o);
        System.arraycopy(this.products, index + 1, this.products, index, this.size-- - index);
        resizeIfNeeded();
        return true;
    }

    public Product remove(int i) {
        Objects.checkIndex(i, this.size);
        Product previousProduct = this.products[i];
        System.arraycopy(this.products, i + 1, this.products, i, this.size-- - i);
        resizeIfNeeded();
        return previousProduct;
    }

    public boolean removeAll(Collection<?> collection) {
        for (var val : collection) {
            for(int i = this.size-1; i >= 0 ; i--) {
                Product product = this.products[i];
                if (product.equals(val)) {
                    this.remove(i);
                }
            }
        }
        return true;
    }

    public boolean retainAll(Collection<?> collection) {
        Product[] newProducts = new Product[this.products.length];
        int newSize = 0;
        for(int i = 0; i < this.size; i++) {
            Product product = this.products[i];
            for(var val: collection) {
                if (product.equals(val)) {
                    newProducts[newSize++] = product;
                }
            }
        }
        this.size = --newSize;
        this.products = newProducts;
        this.resizeIfNeeded();
        this.size++;
        return true;
    }

    public void clear() {
        this.products = new Product[this.DEFAULT_SIZE];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int indexOf(Object o) {
        Objects.requireNonNull(o);
        for (int i = 0; i < this.size; i++) {
            Product product = this.products[i];
            if (product.equals(o))
                return i;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        Objects.requireNonNull(o);
        for (int i = this.size - 1; i >= 0; i--) {
            Product product = this.products[i];
            if (product.equals(o))
                return i;
        }
        return -1;
    }

    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    public boolean containsAll(Collection<?> collection) {
        for (var val : collection) {
            if (!this.contains(val))
                return false;
        }
        return true;
    }

    public Object[] toArray() {
        return this.products;
    }

    public <T> T[] toArray(T[] ts) {
        if (ts.length >= this.size) {
            System.arraycopy(this.products, 0, ts, 0, this.size);
        } else {
            ts = (T[]) Arrays.copyOf(this.products, this.size, ts.getClass());
        }
        return ts;
    }

    public List<Product> subList(int i, int i1) {
        List<Product> sublist = new ProductContainer(i1 - i);
        sublist.addAll(Arrays.asList(this.products).subList(i, i1));
        return sublist;
    }

    public Iterator<Product> iterator() {
        return new ProductIterator();
    }

    public Iterator<Product> iterator(Predicate<Product> predicate) {
        return new ProductIterator(predicate);
    }

    public ListIterator<Product> listIterator() {
        return new ProductListIterator();
    }

    public ListIterator<Product> listIterator(int i) {
        Objects.checkIndex(i, this.size);
        return new ProductListIterator(i);
    }

    private class ProductIterator implements Iterator<Product> {
        private Predicate<Product> predicate;
        private boolean isSetupPredicate = false;

        int currentIndex = 0;
        Product currentProduct;
        public ProductIterator() {}
        public ProductIterator(Predicate<Product> predicate) {
            this.predicate = predicate;
            this.isSetupPredicate = true;
        }

        public boolean hasNext() {
            if (this.isSetupPredicate) {
                Optional<Product> productOptional = ProductContainer.this.stream()
                        .filter(p -> ProductContainer.this.indexOf(p) >= this.currentIndex)
                        .filter(this.predicate)
                        .findFirst();
                if (productOptional.isEmpty()) {
                    return false;
                }
                this.currentIndex = ProductContainer.this.indexOf(productOptional.get());
                this.currentProduct = ProductContainer.this.products[this.currentIndex];
                return true;
            }
            return this.currentIndex < ProductContainer.this.size;
        }

        public Product next() {
            if (this.isSetupPredicate) {
                if (Objects.isNull(this.currentProduct)) {
                    throw new NoSuchElementException();
                }
                return ProductContainer.this.products[this.currentIndex++];
            }
            return ProductContainer.this.products[this.currentIndex++];
        }
    };

    private class ProductListIterator implements ListIterator<Product> {
        private final int DEFAULT_CURSOR = 0;
        private int cursor;

        public ProductListIterator() {
            this.cursor = this.DEFAULT_CURSOR;
        }

        public ProductListIterator(int index) {
            Objects.checkIndex(index, ProductContainer.this.size);
            this.cursor = index;
        }

        public boolean hasNext() {
            return this.cursor < ProductContainer.this.size;
        }

        public Product next() {
            return ProductContainer.this.products[this.cursor++];
        }

        public boolean hasPrevious() {
            return this.cursor > 0;
        }

        public Product previous() {
            return ProductContainer.this.products[this.cursor--];
        }

        public int nextIndex() {
            return this.cursor;
        }

        public int previousIndex() {
            return this.cursor - 1;
        }

        public void remove() {
            ProductContainer.this.remove(this.cursor);
        }

        public void set(Product product) {
            ProductContainer.this.set(this.cursor, product);
        }

        public void add(Product product) {
            ProductContainer.this.add(this.cursor, product);
        }
    }
}
