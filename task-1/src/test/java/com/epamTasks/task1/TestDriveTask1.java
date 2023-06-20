package com.epamTasks.task1;

import com.epamTasks.task1.entity.Entity;
import com.epamTasks.task1.entity.Product;
import com.epamTasks.task1.entity.Dress;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class TestDriveTask1 {
    private Product[] products = new Product[] {
        new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M),
        new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S),
        new Dress(3,9,"Black Dress", Color.BLACK,Dress.Size.S),
        new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M)
    };
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
        ProductContainer productContainer = new ProductContainer();
        assertEquals(0,productContainer.size());
        assertArrayEquals((Object[]) new Product[10], productContainer.toArray());
    }

    @Test
    public void testProductContainerConstructor_2() {
        ProductContainer productContainer = new ProductContainer(2);
        assertEquals(0,productContainer.size());
        assertArrayEquals((Object[]) new Product[10], productContainer.toArray());
    }

    @Test
    public void testProductContainerConstructor_3() {
        ProductContainer productContainer = new ProductContainer(11);
        assertEquals(0,productContainer.size());
        assertArrayEquals((Object[]) new Product[11], productContainer.toArray());
    }

    @Test
    public void testProductContainerAdd_1() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];

        ProductContainer productContainer = new ProductContainer();

        assertTrue(productContainer.add(this.products[0]));
        assertEquals(1,productContainer.size());
        assertTrue(productContainer.add(this.products[1]));
        assertEquals(2,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerAdd_2() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(0,this.products[0]);
        assertEquals(1,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerAdd_3() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[1];
        expectedProducts[1] = this.products[0];
        expectedProducts[2] = this.products[2];
        expectedProducts[3] = this.products[3];

        ProductContainer productContainer = new ProductContainer();
        assertTrue(productContainer.add(this.products[2]));
        assertTrue(productContainer.add(this.products[3]));

        productContainer.add(0,this.products[0]);
        productContainer.add(0,this.products[1]);

        assertEquals(4,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
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

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);

        assertTrue(productContainer.addAll(products));

        assertEquals(4,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
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

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);

        assertTrue(productContainer.addAll(0,products));

        assertEquals(4,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
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

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);

        assertTrue(productContainer.addAll(2,products));

        assertEquals(4,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerAddAll_4() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[1];

        List<Product> products = new ArrayList<>();
        products.add(this.products[0]);
        products.add(this.products[1]);

        ProductContainer productContainer = new ProductContainer();

        assertTrue(productContainer.addAll(0,products));

        assertEquals(2,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerSet() {
        ProductContainer productContainer = new ProductContainer();
        productContainer.add(0,this.products[0]);

        assertEquals(this.products[0],productContainer.set(0,this.products[3]));
        assertEquals(this.products[3],productContainer.get(0));
    }

    @Test
    public void testProductContainerGet() {
        ProductContainer productContainer = new ProductContainer();
        productContainer.add(0,this.products[0]);
        productContainer.add(1,this.products[1]);
        productContainer.add(2,this.products[2]);

        assertEquals(this.products[1],productContainer.get(1));
    }

    @Test
    public void testProductContainerRemove_1() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[2];
        expectedProducts[2] = this.products[3];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        assertEquals(this.products[1],productContainer.remove(1));

        assertEquals(3,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerRemove_2() {
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        expectedProducts[1] = this.products[2];
        expectedProducts[2] = this.products[3];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);
        assertTrue(productContainer.remove(this.products[1]));

        assertEquals(3,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
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

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        assertTrue(productContainer.removeAll(products));

        assertEquals(3,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
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

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        assertTrue(productContainer.retainAll(products));

        assertEquals(3,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerClear() {
        Product[] expectedProducts = new Product[10];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        productContainer.clear();

        assertEquals(0,productContainer.size());
        assertArrayEquals((Object[]) expectedProducts, productContainer.toArray());
    }

    @Test
    public void testProductContainerSize() {
        ProductContainer productContainer = new ProductContainer();

        assertEquals(0,productContainer.size());

        productContainer.add(this.products[0]);
        assertEquals(1,productContainer.size());

        productContainer.add(this.products[1]);
        assertEquals(2,productContainer.size());

        productContainer.remove(this.products[0]);
        assertEquals(1,productContainer.size());

        productContainer.remove(this.products[1]);
        assertEquals(0,productContainer.size());

        for(int i = 0; i < 100; i++) {
            productContainer.add(this.products[0]);
        }
        assertEquals(100,productContainer.size());

        for(int i = 0; i < 25; i++) {
            productContainer.remove(this.products[0]);
        }
        assertEquals(75,productContainer.size());
    }

    @Test
    public void testProductContainerIsEmpty_1() {
        ProductContainer productContainer = new ProductContainer();

        assertTrue(productContainer.isEmpty());
    }

    @Test
    public void testProductContainerIsEmpty_2() {
        ProductContainer productContainer = new ProductContainer();

        productContainer.add(this.products[0]);

        assertFalse(productContainer.isEmpty());
    }

    @Test
    public void testProductContainerIndexOf() {
        ProductContainer productContainer = new ProductContainer();

        productContainer.add(0,this.products[0]);
        productContainer.add(1,this.products[1]);
        productContainer.add(2,this.products[2]);
        productContainer.add(3,this.products[3]);
        productContainer.add(4,this.products[0]);

        assertEquals(0,productContainer.indexOf(this.products[0]));
        assertEquals(2,productContainer.indexOf(this.products[2]));
    }

    @Test
    public void testProductContainerLastIndexOf() {
        ProductContainer productContainer = new ProductContainer();

        productContainer.add(0,this.products[0]);
        productContainer.add(1,this.products[1]);
        productContainer.add(2,this.products[2]);
        productContainer.add(3,this.products[3]);
        productContainer.add(4,this.products[0]);

        assertEquals(4,productContainer.lastIndexOf(this.products[0]));
        assertEquals(2,productContainer.lastIndexOf(this.products[2]));
    }

    @Test
    public void testProductContainerContains_1() {
        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[2]);

        assertFalse(productContainer.contains(this.products[0]));
    }

    @Test
    public void testProductContainerContains_2() {
        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[2]);
        productContainer.add(this.products[0]);

        assertTrue(productContainer.contains(this.products[0]));
    }

    @Test
    public void testProductContainerContainsAll() {
        List<Product> products = new ArrayList<>();

        ProductContainer productContainer = new ProductContainer();

        assertTrue(productContainer.containsAll(products));

        productContainer.add(this.products[0]);

        assertTrue(productContainer.containsAll(products));

        products.add(this.products[0]);

        assertTrue(productContainer.containsAll(products));

        products.add(this.products[1]);

        assertFalse(productContainer.containsAll(products));

        productContainer.add(this.products[1]);

        assertTrue(productContainer.containsAll(products));

        products.add(this.products[1]);

        assertTrue(productContainer.containsAll(products));

        products.add(this.products[2]);

        assertFalse(productContainer.containsAll(products));
    }

    @Test
    public void testProductContainerToArray() {
        Product[] expectedProduct = new Product[10];
        expectedProduct[0] = this.products[0];
        expectedProduct[1] = this.products[1];
        expectedProduct[2] = this.products[2];
        expectedProduct[3] = this.products[3];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct,productContainer.toArray());
        assertArrayEquals(expectedProduct,productContainer.toArray(new Product[10]));

        expectedProduct = Arrays.copyOf(expectedProduct,4);
        assertArrayEquals(expectedProduct,productContainer.toArray(new Product[2]));
    }

    @Test
    public void testProductContainerSubList() {
        List<Product> expectedListProducts = new ProductContainer();
        expectedListProducts.add(this.products[1]);
        expectedListProducts.add(this.products[2]);

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        assertArrayEquals(expectedListProducts.toArray(),productContainer.subList(1,3).toArray());
        assertThat(expectedListProducts.toArray(),not(equalTo(productContainer.subList(1,2).toArray())));
    }

    @Test
    public void testProductContainerIterator_1() {
        ProductContainer productContainer = new ProductContainer();
        Iterator<Product> iterator = productContainer.iterator();

        assertFalse(iterator.hasNext());

        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);
        iterator = productContainer.iterator();

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
        ProductContainer productContainer = new ProductContainer();
        Product[] actualProducts = new Product[10];

        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        Iterator<Product> iterator = productContainer.iterator(p -> p.getPrice() > 10);

        int i = 0;
        while (iterator.hasNext()) {
            actualProducts[i++] = iterator.next();
        }

        assertArrayEquals(expectedProducts,actualProducts);
    }

    @Test
    public void testProductContainerListIterator() {
        ProductContainer productContainer = new ProductContainer();
        ListIterator<Product> iterator = productContainer.listIterator();

        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0,iterator.nextIndex());
        assertEquals(-1,iterator.previousIndex());

        iterator.add(this.products[0]);
        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = this.products[0];
        assertArrayEquals(expectedProducts,productContainer.toArray());

        productContainer = new ProductContainer();
        productContainer.add(this.products[0]);
        productContainer.add(this.products[1]);
        productContainer.add(this.products[2]);
        productContainer.add(this.products[3]);

        iterator = productContainer.listIterator();
        assertEquals(this.products[0],iterator.next());
        assertEquals(this.products[1],iterator.previous());
        assertEquals(0,iterator.nextIndex());
        assertEquals(-1,iterator.previousIndex());
        iterator.add(this.products[3]);
        expectedProducts = new Product[10];
        expectedProducts[0] = this.products[3];
        expectedProducts[1] = this.products[0];
        expectedProducts[2] = this.products[1];
        expectedProducts[3] = this.products[2];
        expectedProducts[4] = this.products[3];
        assertArrayEquals(expectedProducts,productContainer.toArray());
    }
}