package com.epamTasks.task2;

import com.epamTasks.task1.entity.Product;

import java.util.Iterator;
import java.util.function.Predicate;

public class ProductContainer extends com.epamTasks.task1.ProductContainer {
    public Iterator<Product> iterator() {
        return new ProductIterator();
    }

    public Iterator<Product> iterator(Predicate<Product> predicate) {
        return new ProductIterator(predicate);
    }

    private class ProductIterator implements Iterator<Product> {
        private Product[] innerProducts = ProductContainer.this.toArray(new Product[ProductContainer.this.size]);
        private int innerSize = ProductContainer.this.size;
        private Predicate<Product> predicate;

        private boolean isSetupPredicate = false;

        int currentIndex = 0;

        public ProductIterator() {
        }

        public ProductIterator(Predicate<Product> predicate) {
            this.predicate = predicate;
            this.isSetupPredicate = true;
        }

        private boolean hasNextPredicate() {
            while (this.hasNextBasic()) {
                Product product = this.innerProducts[this.currentIndex];
                if (this.predicate.test(product)) {
                    return true;
                }
                this.currentIndex++;
            }
            return false;
        }

        private boolean hasNextBasic() {
            return this.currentIndex < this.innerSize;
        }

        public boolean hasNext() {
            return this.isSetupPredicate ? this.hasNextPredicate() : this.hasNextBasic();
        }

        public Product next() {
            return this.innerProducts[this.currentIndex++];
        }
    }
}
