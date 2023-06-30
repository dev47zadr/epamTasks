package com.epamTasks.task3;

import com.epamTasks.task3.entity.Dress;
import com.epamTasks.task3.entity.Product;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


public class TestDriveTask3 {
    private ArraySet<Product> arraySet;
    private Product[] products = new Product[]{
            new Dress(1, 12, "Red Dress", Color.RED, Dress.Size.M),
            new Dress(2, 12, "Blue Dress", Color.BLUE, Dress.Size.S),
            new Dress(3, 9, "Black Dress", Color.BLACK, Dress.Size.S),
            new Dress(4, 12, "GREEN Dress", Color.GREEN, Dress.Size.M)
    };

    @Before
    public void initProductContainer() {
        this.arraySet = new ArraySet<Product>();
    }

    @Test
    public void testArraySetIsExtendsArrayList() {
        Class<? super ArraySet<?>> superClass = ArraySet.class.getSuperclass();

        assertEquals(ArrayList.class, superClass);
    }

    @Test
    public void testArraySetConstructor_1() {
        assertArrayEquals(new Object[0], arraySet.toArray());
        assertEquals(0, this.arraySet.size());
    }

    @Test
    public void testArraySetConstructor_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };

        this.arraySet = new ArraySet<>(Arrays.asList(
                this.products[0],
                this.products[1]
        ));

        assertArrayEquals(expectedProducts, arraySet.toArray());
        assertEquals(2, this.arraySet.size());
    }

    @Test
    public void testArraySetConstructor_3() {
        try {
            this.arraySet = new ArraySet<>(Arrays.asList(
                    this.products[0],
                    this.products[0]
            ));
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetConstructor_4() {
        this.arraySet = new ArraySet<>(5);

        assertArrayEquals(new Object[0], arraySet.toArray());
        assertEquals(0, this.arraySet.size());
    }

    @Test
    public void testArraySetAdd_1() {
        assertTrue(this.arraySet.add(this.products[0]));

        try {
            this.arraySet.add(this.products[0]);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetAdd_2() {
        assertTrue(this.arraySet.add(this.products[0]));

        try {
            this.arraySet.add(0, this.products[0]);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetAdd_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };

        assertTrue(this.arraySet.add(this.products[0]));
        assertTrue(this.arraySet.add(this.products[1]));

        assertArrayEquals(expectedProducts, arraySet.toArray());
        assertEquals(2, this.arraySet.size());
    }

    @Test
    public void testArraySetAdd_4() {
        Object[] expectedProducts = new Object[]{
                this.products[1],
                this.products[0]
        };

        this.arraySet.add(0, this.products[0]);
        this.arraySet.add(0, this.products[1]);

        assertArrayEquals(expectedProducts, arraySet.toArray());
        assertEquals(2, this.arraySet.size());
    }

    @Test
    public void testArraySetSet_1() {
        this.arraySet.add(0, this.products[0]);

        assertEquals(this.products[0], this.arraySet.set(0, this.products[1]));
        assertEquals(this.products[1], this.arraySet.get(0));
    }

    @Test
    public void testArraySetSet_3() {
        this.arraySet.add(this.products[0]);

        try {
            this.arraySet.set(0, this.products[0]);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetAddAll_1() {
        Object[] expectedProducts = new Object[]{
                this.products[2],
                this.products[0],
                this.products[1],
        };

        List<Product> products = new ArrayList<>(Arrays.asList(
                this.products[0],
                this.products[1]
        ));

        this.arraySet.add(this.products[2]);
        assertTrue(this.arraySet.addAll(1, products));
        assertArrayEquals(expectedProducts, this.arraySet.toArray());
        assertEquals(3, this.arraySet.size());
    }

    @Test
    public void testArraySetAddAll_2() {
        List<Product> products = new ArrayList<>(Arrays.asList(
                this.products[1],
                this.products[1]
        ));

        this.arraySet.add(this.products[0]);

        try {
            this.arraySet.addAll(0, products);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetAddAll_3() {
        List<Product> products = new ArrayList<>(Arrays.asList(
                this.products[0],
                this.products[1]
        ));

        this.arraySet.add(this.products[0]);

        try {
            this.arraySet.addAll(0, products);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetAddAll_4() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };

        List<Product> products = new ArrayList<>(Arrays.asList(
                this.products[0],
                this.products[1]
        ));

        assertTrue(this.arraySet.addAll(products));
        assertArrayEquals(expectedProducts, this.arraySet.toArray());
        assertEquals(2, this.arraySet.size());
    }

    @Test
    public void testArraySetAddAll_5() {
        List<Product> products = new ArrayList<>(Arrays.asList(
                this.products[1],
                this.products[1]
        ));

        this.arraySet.add(this.products[0]);

        try {
            this.arraySet.addAll(products);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetAddAll_6() {
        List<Product> products = new ArrayList<>(Arrays.asList(
                this.products[0],
                this.products[1]
        ));

        this.arraySet.add(this.products[0]);

        try {
            this.arraySet.addAll(products);
            fail("We expected DuplicateValueException");
        } catch (DuplicateValueException e) {
            assertTrue("We get DuplicateValueException", true);
        }
    }

    @Test
    public void testArraySetListIterator_1() {
        try {
            this.arraySet.listIterator();
            fail("We expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertTrue("We get UnsupportedOperationException", true);
        }
    }

    @Test
    public void testArraySetListIterator_2() {
        try {
            this.arraySet.listIterator(0);
            fail("We expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            assertTrue("We get UnsupportedOperationException", true);
        }
    }

    @Test
    public void testArraySetCheckAsMap() {
        Product[] products = new Product[]{
                new Dress(1, 12, "Red Dress", Color.RED, Dress.Size.M),
                new Dress(2, 12, "Blue Dress", Color.BLUE, Dress.Size.S),
                new Dress(3, 9, "Black Dress", Color.BLACK, Dress.Size.S),
                new Dress(4, 12, "GREEN Dress", Color.GREEN, Dress.Size.M)
        };
        ArraySet<Product> arraySet = new ArraySet<>(List.of(products));
        Iterator<Product> arraySetIterator = arraySet.iterator();

        Map<String, Product> hashMap = new HashMap<>();
        hashMap.put(products[0].toString(), products[0]);
        hashMap.put(products[3].toString(), products[3]);
        hashMap.put(products[1].toString(), products[1]);
        hashMap.put(products[2].toString(), products[2]);
        Iterator<Product> hashMapIterator = arraySet.iterator();

        Map<String, Product> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(products[0].toString(), products[0]);
        linkedHashMap.put(products[2].toString(), products[2]);
        linkedHashMap.put(products[3].toString(), products[3]);
        linkedHashMap.put(products[1].toString(), products[1]);
        Iterator<Product> linkedHashMapIterator = arraySet.iterator();

        assertArrayEquals(products, getIteratedArray(arraySetIterator));
        assertArrayEquals(products, getIteratedArray(hashMapIterator));
        assertArrayEquals(products, getIteratedArray(linkedHashMapIterator));
    }

    private Object[] getIteratedArray(Iterator<?> itr) {
        List<Object> resultList = new ArrayList<>();
        while (itr.hasNext()) {
            resultList.add(itr.next());
        }
        return resultList.toArray();
    }
}