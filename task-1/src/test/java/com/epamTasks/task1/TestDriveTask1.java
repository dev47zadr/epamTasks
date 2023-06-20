package com.epamTasks.task1;

import com.epamTasks.task1.entity.Entity;
import com.epamTasks.task1.entity.Product;
import com.epamTasks.task1.entity.Dress;
import com.epamTasks.testUtil.Helper;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.List;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class TestDriveTask1 {
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
    public void testProductContainerConstructor() {
        ProductContainer productContainer = new ProductContainer();
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(0,privateSize.get(productContainer));
            assertArrayEquals((Object[]) new Product[10], (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

        productContainer = new ProductContainer(2);
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(0,privateSize.get(productContainer));
            assertArrayEquals((Object[]) new Product[10], (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
        productContainer = new ProductContainer(11);
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(0,privateSize.get(productContainer));
            assertArrayEquals((Object[]) new Product[11], (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerAdd() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product2;

        ProductContainer productContainer = new ProductContainer();
        assertTrue(productContainer.add(product1));
        assertEquals(1,productContainer.size());
        assertTrue(productContainer.add(product2));
        assertEquals(2,productContainer.size());

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(2,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

        productContainer = new ProductContainer();
        productContainer.add(0,product1);

        expectedProducts = new Product[10];
        expectedProducts[0] = product1;

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(1,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

        productContainer = new ProductContainer();
        try {
            productContainer.add(null);
            fail("Exception expected");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void testProductContainerAddAll() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);
        List<Product> products = new ArrayList<>();
        products.add(product3);
        products.add(product4);

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product2;
        expectedProducts[2] = product3;
        expectedProducts[3] = product4;

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        assertTrue(productContainer.addAll(products));
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(4,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

        products = new ArrayList<>();
        products.add(product3);
        products.add(product4);

        expectedProducts = new Product[10];
        expectedProducts[0] = product3;
        expectedProducts[1] = product4;
        expectedProducts[2] = product1;
        expectedProducts[3] = product2;

        productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        assertTrue(productContainer.addAll(0,products));
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(4,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

        products = new ArrayList<>();
        products.add(product3);
        products.add(product4);

        expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product2;
        expectedProducts[2] = product3;
        expectedProducts[3] = product4;

        productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        assertTrue(productContainer.addAll(2,products));
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(4,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }


        products.set(1, null);
        try {
            productContainer.addAll(products);
            fail("Exception expected");
        } catch (Exception e) {
            assertTrue(true);
        }

    }

    @Test
    public void testProductContainerSet() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product4;
        expectedProducts[2] = product3;
        expectedProducts[3] = product4;

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertEquals(product2,productContainer.set(1,product4));

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(4,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerGet() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product2;
        expectedProducts[2] = product3;
        expectedProducts[3] = product4;

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertEquals(product2,productContainer.get(1));

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(4,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

    }

    @Test
    public void testProductContainerRemove() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product3;
        expectedProducts[2] = product4;

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertEquals(product2,productContainer.remove(1));

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(3,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

        expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product3;
        expectedProducts[2] = product4;

        productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertTrue(productContainer.remove(product2));

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(3,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }

    }

    @Test
    public void testProductContainerRemoveAll() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        expectedProducts[1] = product1;
        expectedProducts[2] = product4;

        List<Product> products = new ArrayList<>();
        products.add(product2);
        products.add(product3);

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertTrue(productContainer.removeAll(products));

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(3,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerRetainAll() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product2;
        expectedProducts[1] = product2;
        expectedProducts[2] = product3;

        List<Product> products = new ArrayList<>();
        products.add(product2);
        products.add(product3);

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertTrue(productContainer.retainAll(products));

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(3,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerClear() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        productContainer.clear();

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(0,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerSize() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);

        ProductContainer productContainer = new ProductContainer();
        assertEquals(0,productContainer.size());
        productContainer.add(product1);
        assertEquals(1,productContainer.size());
        productContainer.add(product2);
        assertEquals(2,productContainer.size());
        productContainer.remove(product1);
        assertEquals(1,productContainer.size());
        productContainer.remove(product2);
        assertEquals(0,productContainer.size());
        for(int i = 0; i < 100; i++) {
            productContainer.add(product1);
        }
        assertEquals(100,productContainer.size());
        for(int i = 0; i < 25; i++) {
            productContainer.remove(product1);
        }
        assertEquals(75,productContainer.size());

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");
            Product[] product = (Product[])privateProducts.get(productContainer);

            assertEquals(160,product.length);
            assertEquals(75, privateSize.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerIsEmpty() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);

        Product[] expectedProducts = new Product[10];

        ProductContainer productContainer = new ProductContainer();
        assertTrue(productContainer.isEmpty());
        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(0,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
        expectedProducts[0] = product1;

        productContainer.add(product1);
        assertFalse(productContainer.isEmpty());

        try {
            Field privateSize = Helper.makeFieldPublic(ProductContainer.class,"size");
            Field privateProducts = Helper.makeFieldPublic(ProductContainer.class,"products");

            assertEquals(1,privateSize.get(productContainer));
            assertArrayEquals((Object[]) expectedProducts, (Object[]) privateProducts.get(productContainer));
        } catch (Exception e) {
            fail("Exception doesn't expected");
        }
    }

    @Test
    public void testProductContainerIndexOf() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        assertEquals(1,productContainer.indexOf(product2));
    }

    @Test
    public void testProductContainerLastIndexOf() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProducts = new Product[10];

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        productContainer.add(product2);
        assertEquals(4,productContainer.lastIndexOf(product2));
    }

    @Test
    public void testProductContainerLastContains() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);

        Product[] expectedProducts = new Product[10];

        ProductContainer productContainer = new ProductContainer();

        assertFalse(productContainer.contains(product1));

        productContainer.add(product1);

        assertTrue(productContainer.contains(product1));
    }

    @Test
    public void testProductContainerLastContainsAll() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);

        List<Product> products = new ArrayList<>();

        ProductContainer productContainer = new ProductContainer();

        assertTrue(productContainer.containsAll(products));

        productContainer.add(product1);

        assertTrue(productContainer.containsAll(products));

        products.add(product1);

        assertTrue(productContainer.containsAll(products));

        products.add(product2);

        assertFalse(productContainer.containsAll(products));

        productContainer.add(product2);

        assertTrue(productContainer.containsAll(products));

        products.add(product2);

        assertTrue(productContainer.containsAll(products));

        products.add(product3);

        assertFalse(productContainer.containsAll(products));

    }

    @Test
    public void testProductContainerLastToArray() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        Product[] expectedProduct = new Product[10];
        expectedProduct[0] = product1;
        expectedProduct[1] = product2;
        expectedProduct[2] = product3;
        expectedProduct[3] = product4;

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);

        assertArrayEquals(expectedProduct,productContainer.toArray());

        assertArrayEquals(expectedProduct,productContainer.toArray(new Product[10]));

        expectedProduct = Arrays.copyOf(expectedProduct,4);
        assertArrayEquals(expectedProduct,productContainer.toArray(new Product[2]));

    }

    @Test
    public void testProductContainerLastSubList() {
        Product product1 = new Dress(1,12,"Red Dress", Color.RED,Dress.Size.M);
        Product product2 = new Dress(2,12,"Blue Dress", Color.BLUE,Dress.Size.S);
        Product product3 = new Dress(3,13,"Black Dress", Color.BLACK,Dress.Size.S);
        Product product4 = new Dress(4,12,"GREEN Dress", Color.GREEN,Dress.Size.M);

        List<Product> expectedListProducts = new ProductContainer();
        expectedListProducts.add(product2);
        expectedListProducts.add(product3);

        ProductContainer productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);

        assertArrayEquals(expectedListProducts.toArray(),productContainer.subList(1,3).toArray());

        assertThat(expectedListProducts.toArray(),not(equalTo(productContainer.subList(1,2).toArray())));

    }

    @Test
    public void testProductContainerIterator() {
        Product product1 = new Dress(1, 12, "Red Dress", Color.RED, Dress.Size.M);
        Product product2 = new Dress(2, 12, "Blue Dress", Color.BLUE, Dress.Size.S);
        Product product3 = new Dress(3, 13, "Black Dress", Color.BLACK, Dress.Size.S);
        Product product4 = new Dress(4, 12, "GREEN Dress", Color.GREEN, Dress.Size.M);

        ProductContainer productContainer = new ProductContainer();
        Iterator<Product> iterator = productContainer.iterator();

        assertFalse(iterator.hasNext());

        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);
        iterator = productContainer.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(product1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(product2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(product3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(product4, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testProductContainerListIterator() {
        Product product1 = new Dress(1, 12, "Red Dress", Color.RED, Dress.Size.M);
        Product product2 = new Dress(2, 12, "Blue Dress", Color.BLUE, Dress.Size.S);
        Product product3 = new Dress(3, 13, "Black Dress", Color.BLACK, Dress.Size.S);
        Product product4 = new Dress(4, 12, "GREEN Dress", Color.GREEN, Dress.Size.M);

        ProductContainer productContainer = new ProductContainer();
        ListIterator<Product> iterator = productContainer.listIterator();

        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(0,iterator.nextIndex());
        assertEquals(-1,iterator.previousIndex());

        iterator.add(product1);
        assertFalse(iterator.hasPrevious());
        assertTrue(iterator.hasNext());

        Product[] expectedProducts = new Product[10];
        expectedProducts[0] = product1;
        assertArrayEquals(expectedProducts,productContainer.toArray());

        productContainer = new ProductContainer();
        productContainer.add(product1);
        productContainer.add(product2);
        productContainer.add(product3);
        productContainer.add(product4);

        iterator = productContainer.listIterator();
        assertEquals(product1,iterator.next());
        assertEquals(product2,iterator.previous());
        assertEquals(0,iterator.nextIndex());
        assertEquals(-1,iterator.previousIndex());
        iterator.add(product4);
        expectedProducts = new Product[10];
        expectedProducts[0] = product4;
        expectedProducts[1] = product1;
        expectedProducts[2] = product2;
        expectedProducts[3] = product3;
        expectedProducts[4] = product4;
        assertArrayEquals(expectedProducts,productContainer.toArray());
    }
}