package com.epamTasks.task1;

import com.epamTasks.task1.entity.Entity;
import com.epamTasks.task1.entity.Product;
import com.epamTasks.task1.entity.Dress;

import java.awt.Color;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class TestDriveTask1 {
    private Product[] products = new Product[]{
            new Dress(1, 12, "Red Dress", Color.RED, Dress.Size.M),
            new Dress(2, 12, "Blue Dress", Color.BLUE, Dress.Size.S),
            new Dress(3, 9, "Black Dress", Color.BLACK, Dress.Size.S),
            new Dress(4, 12, "GREEN Dress", Color.GREEN, Dress.Size.M)
    };
    private ProductContainer productContainer;

    @Before
    public void initProductContainer() {
        System.out.println("Before");
        this.productContainer = new ProductContainer();
    }

    @Test
    public void testCheckDeepHierarchy() {
        assertEquals(Dress.class.getSuperclass(), Product.class);
        assertEquals(Product.class.getSuperclass(), Entity.class);
    }

    @Test
    public void testProductContainerIsList() {
        Class<?>[] interfaces = ProductContainer.class.getInterfaces();

        assertTrue(Arrays.asList(interfaces).contains(List.class));
    }

    @Test
    public void testProductContainerConstructor_1() {
        assertEquals(0, this.productContainer.size());
        assertArrayEquals((Object[]) new Product[10], this.productContainer.toArray());
    }

    @Test
    public void testProductContainerConstructor_2() {
        assertEquals(0, this.productContainer.size());
        assertArrayEquals((Object[]) new Product[10], this.productContainer.toArray());
    }

    @Test
    public void testProductContainerConstructor_3() {
        ProductContainer productContainer = new ProductContainer(11);

        assertEquals(0, productContainer.size());
        assertArrayEquals((Object[]) new Product[11], productContainer.toArray());
    }

    @Test
    public void testProductContainerAdd_1() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];

        assertTrue(this.productContainer.add(this.products[0]));
        assertEquals(1, this.productContainer.size());
        assertTrue(this.productContainer.add(this.products[1]));
        assertEquals(2, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerAdd_2() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];

        this.productContainer.add(0, this.products[0]);

        assertEquals(1, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerAdd_3() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[1];
        expectedProducts[1] = this.products[0];
        expectedProducts[2] = this.products[2];
        expectedProducts[3] = this.products[3];

        assertTrue(this.productContainer.add(this.products[2]));
        assertTrue(this.productContainer.add(this.products[3]));
        this.productContainer.add(0, this.products[0]);
        this.productContainer.add(0, this.products[1]);

        assertEquals(4, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerAddAll_1() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];
        expectedProducts[2] = this.products[2];
        expectedProducts[3] = this.products[3];
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);

        assertTrue(this.productContainer.addAll(products));
        assertEquals(4, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerAddAll_2() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[2];
        expectedProducts[1] = this.products[3];
        expectedProducts[2] = this.products[0];
        expectedProducts[3] = this.products[1];
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);

        assertTrue(this.productContainer.addAll(0, products));
        assertEquals(4, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerAddAll_3() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];
        expectedProducts[2] = this.products[2];
        expectedProducts[3] = this.products[3];
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);

        assertTrue(this.productContainer.addAll(2, products));
        assertEquals(4, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerAddAll_4() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];
        List<Product> products = new ArrayList<>();

        products.add(this.products[0]);
        products.add(this.products[1]);

        assertTrue(this.productContainer.addAll(0, products));
        assertEquals(2, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerSet() {
        this.productContainer.add(0, this.products[0]);

        assertEquals(this.products[0], this.productContainer.set(0, this.products[3]));
        assertEquals(this.products[3], this.productContainer.get(0));
    }

    @Test
    public void testProductContainerGet() {
        this.productContainer.add(0, this.products[0]);
        this.productContainer.add(1, this.products[1]);
        this.productContainer.add(2, this.products[2]);

        assertEquals(this.products[1], this.productContainer.get(1));
    }

    @Test
    public void testProductContainerRemove_1() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[2];
        expectedProducts[2] = this.products[3];

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        assertEquals(this.products[1], this.productContainer.remove(1));
        assertEquals(3, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerRemove_2() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[2];
        expectedProducts[2] = this.products[3];

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        assertTrue(this.productContainer.remove(this.products[1]));
        assertEquals(3, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerRemoveAll() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[0];
        expectedProducts[2] = this.products[3];
        List<Product> products = new ArrayList<>();
        products.add(this.products[1]);
        products.add(this.products[2]);

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        assertTrue(this.productContainer.removeAll(products));
        assertEquals(3, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerRetainAll() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[1];
        expectedProducts[1] = this.products[1];
        expectedProducts[2] = this.products[2];
        List<Product> products = new ArrayList<>();
        products.add(this.products[1]);
        products.add(this.products[2]);

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        assertTrue(this.productContainer.retainAll(products));
        assertEquals(3, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerClear() {
        Product[] expectedProducts = new Product[10];

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);
        this.productContainer.clear();

        assertEquals(0, this.productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, this.productContainer.toArray());
    }

    @Test
    public void testProductContainerSize() {
        assertEquals(0, this.productContainer.size());

        this.productContainer.add(this.products[0]);
        assertEquals(1, this.productContainer.size());

        this.productContainer.add(this.products[1]);
        assertEquals(2, this.productContainer.size());

        this.productContainer.remove(this.products[0]);
        assertEquals(1, this.productContainer.size());

        this.productContainer.remove(this.products[1]);
        assertEquals(0, this.productContainer.size());

        for (int i = 0; i < 100; i++) {
            this.productContainer.add(this.products[0]);
        }
        assertEquals(100, this.productContainer.size());

        for (int i = 0; i < 25; i++) {
            this.productContainer.remove(this.products[0]);
        }
        assertEquals(75, this.productContainer.size());
    }

    @Test
    public void testProductContainerIsEmpty_1() {
        assertTrue(this.productContainer.isEmpty());
    }

    @Test
    public void testProductContainerIsEmpty_2() {
        this.productContainer.add(this.products[0]);

        assertFalse(this.productContainer.isEmpty());
    }

    @Test
    public void testProductContainerIndexOf() {
        this.productContainer.add(0, this.products[0]);
        this.productContainer.add(1, this.products[1]);
        this.productContainer.add(2, this.products[2]);
        this.productContainer.add(3, this.products[3]);
        this.productContainer.add(4, this.products[0]);

        assertEquals(0, this.productContainer.indexOf(this.products[0]));
        assertEquals(2, this.productContainer.indexOf(this.products[2]));
    }

    @Test
    public void testProductContainerLastIndexOf() {
        this.productContainer.add(0, this.products[0]);
        this.productContainer.add(1, this.products[1]);
        this.productContainer.add(2, this.products[2]);
        this.productContainer.add(3, this.products[3]);
        this.productContainer.add(4, this.products[0]);

        assertEquals(4, this.productContainer.lastIndexOf(this.products[0]));
        assertEquals(2, this.productContainer.lastIndexOf(this.products[2]));
    }

    @Test
    public void testProductContainerContains_1() {
        this.productContainer.add(this.products[2]);

        assertFalse(this.productContainer.contains(this.products[0]));
    }

    @Test
    public void testProductContainerContains_2() {
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[0]);

        assertTrue(this.productContainer.contains(this.products[0]));
    }

    @Test
    public void testProductContainerContainsAll() {
        List<Product> products = new ArrayList<>();

        assertTrue(this.productContainer.containsAll(products));

        this.productContainer.add(this.products[0]);

        assertTrue(this.productContainer.containsAll(products));

        products.add(this.products[0]);

        assertTrue(this.productContainer.containsAll(products));

        products.add(this.products[1]);

        assertFalse(this.productContainer.containsAll(products));

        this.productContainer.add(this.products[1]);

        assertTrue(this.productContainer.containsAll(products));

        products.add(this.products[1]);

        assertTrue(this.productContainer.containsAll(products));

        products.add(this.products[2]);

        assertFalse(this.productContainer.containsAll(products));
    }

    @Test
    public void testProductContainerToArray() {
        Product[] expectedProduct = new Product[10];
        expectedProduct[0] = this.products[0];
        expectedProduct[1] = this.products[1];
        expectedProduct[2] = this.products[2];
        expectedProduct[3] = this.products[3];
        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.productContainer.toArray());
        assertArrayEquals(expectedProduct, this.productContainer.toArray(new Product[10]));
        expectedProduct = Arrays.copyOf(expectedProduct, 4);
        assertArrayEquals(expectedProduct, this.productContainer.toArray(new Product[2]));
    }

    @Test
    public void testProductContainerSubList() {
        List<Product> expectedListProducts = new ProductContainer();
        expectedListProducts.add(this.products[1]);
        expectedListProducts.add(this.products[2]);
        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        assertArrayEquals(expectedListProducts.toArray(), this.productContainer.subList(1, 3).toArray());
        assertThat(expectedListProducts.toArray(), not(equalTo(this.productContainer.subList(1, 2).toArray())));
    }

    @Test
    public void testProductContainerIterator_1() {
        Iterator<Product> iterator = this.productContainer.iterator();

        assertFalse(iterator.hasNext());

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);
        iterator = this.productContainer.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(this.products[0], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(this.products[1], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(this.products[2], iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(this.products[3], iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testProductContainerIterator_2() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];
        expectedProducts[2] = this.products[3];
        Product[] actualProducts = new Product[10];

        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        Iterator<Product> iterator = this.productContainer.iterator(p -> p.getPrice() > 10);

        int i = 0;
        while (iterator.hasNext()) {
            actualProducts[i++] = iterator.next();
        }

        assertArrayEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testProductContainerListIterator() {
        ListIterator<Product> iterator = this.productContainer.listIterator();

        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());

        iterator.add(this.products[0]);
        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        assertArrayEquals(expectedProducts, this.productContainer.toArray());

        this.productContainer = new ProductContainer();
        this.productContainer.add(this.products[0]);
        this.productContainer.add(this.products[1]);
        this.productContainer.add(this.products[2]);
        this.productContainer.add(this.products[3]);

        iterator = this.productContainer.listIterator();
        assertEquals(this.products[0], iterator.next());
        assertEquals(this.products[1], iterator.previous());
        assertEquals(0, iterator.nextIndex());
        assertEquals(-1, iterator.previousIndex());
        iterator.add(this.products[3]);
        expectedProducts = new Product[10];
        expectedProducts[0] = this.products[3];
        expectedProducts[1] = this.products[0];
        expectedProducts[2] = this.products[1];
        expectedProducts[3] = this.products[2];
        expectedProducts[4] = this.products[3];
        assertArrayEquals(expectedProducts, this.productContainer.toArray());
    }
}