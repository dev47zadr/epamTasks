package com.epamTasks.task2;

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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThat;


public class TestDriveTask2 {
    private Product[] products = new Product[]{
            new Dress(1, 12, "Red Dress", Color.RED, Dress.Size.M),
            new Dress(2, 12, "Blue Dress", Color.BLUE, Dress.Size.S),
            new Dress(3, 9, "Black Dress", Color.BLACK, Dress.Size.S),
            new Dress(4, 12, "GREEN Dress", Color.GREEN, Dress.Size.M)
    };
    private Container<Product> filledContainer;
    private Container<Product> emptyContainer;

    @Before
    public void initContainer() {
        this.filledContainer = new Container<Product>(Arrays.asList(
                this.products[0],
                this.products[1]
        ));
        this.emptyContainer = new Container<Product>();
    }

    @Test
    public void testContainerIsList() {
        Class<?>[] interfaces = Container.class.getInterfaces();

        assertTrue(Arrays.asList(interfaces).contains(List.class));
    }

    @Test
    public void testContainerConstructorEmpty() {
        assertEquals(0, this.emptyContainer.size());
        assertArrayEquals(new Object[0], this.emptyContainer.toArray());
    }

    @Test
    public void testContainerConstructor() {
        Object[] expectedProducts = new Object[]{this.products[0], this.products[1]};

        assertEquals(2, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerAddEmpty_1() {
        Object[] expectedProducts = new Object[]{this.products[0]};

        assertTrue(this.emptyContainer.add(this.products[0]));

        assertEquals(1, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerAddEmpty_2() {
        Object[] expectedProducts = new Object[]{this.products[0]};

        this.emptyContainer.add(0, this.products[0]);

        assertEquals(1, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerAddFilled_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };

        assertTrue(this.filledContainer.add(this.products[3]));

        assertEquals(3, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerAddFilled_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };

        this.filledContainer.add(2, this.products[3]);

        assertEquals(3, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerAddFilled_3() {
        try {
            this.filledContainer.add(1, this.products[3]);
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }
    }

    @Test
    public void testContainerAddAllEmpty_1() {
        Object[] expectedProducts = new Object[]{
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        assertTrue(this.emptyContainer.addAll(products));

        assertEquals(2, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }


    @Test
    public void testContainerAddAllEmpty_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);
        this.emptyContainer.add(this.products[0]);

        assertTrue(this.emptyContainer.addAll(products));

        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerAddAllEmpty_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);
        this.emptyContainer.add(this.products[0]);

        assertTrue(this.emptyContainer.addAll(1, products));

        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerAddAllEmpty_4() {
        Object[] expectedProducts = new Object[]{
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        assertTrue(this.emptyContainer.addAll(0, products));

        assertEquals(2, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerAddAllEmpty_5() {
        Object[] expectedProducts = new Object[]{
                this.products[2],
                this.products[3],
                this.products[0]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);
        this.emptyContainer.add(this.products[0]);

        assertTrue(this.emptyContainer.addAll(0, products));

        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerAddAllFilled_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        assertTrue(this.filledContainer.addAll(products));

        assertEquals(4, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerAddAllFilled_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);

        assertTrue(this.filledContainer.addAll(2, products));

        assertEquals(4, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerAddAllFilled_3() {
        List<Product> products = new ArrayList<>();
        products.add(this.products[2]);
        products.add(this.products[3]);
        try {
            this.filledContainer.addAll(0, products);
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }
    }

    @Test
    public void testContainerSetEmpty() {
        this.emptyContainer.add(0, this.products[0]);

        assertEquals(this.products[0], this.emptyContainer.set(0, this.products[3]));
        assertEquals(this.products[3], this.emptyContainer.get(0));
    }

    @Test
    public void testContainerSetFilled_1() {
        this.filledContainer.add(this.products[2]);
        assertEquals(this.products[2], this.filledContainer.set(2, this.products[3]));
        assertEquals(this.products[3], this.filledContainer.get(2));
    }

    @Test
    public void testContainerSetFilled_2() {
        try {
            this.filledContainer.set(1, this.products[3]);
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }
    }

    @Test
    public void testContainerGetEmpty() {
        this.emptyContainer.add(0, this.products[0]);
        this.emptyContainer.add(1, this.products[1]);
        this.emptyContainer.add(2, this.products[2]);

        assertEquals(this.products[1], this.emptyContainer.get(1));
    }

    @Test
    public void testContainerGetFilled() {
        this.filledContainer.add(2, this.products[2]);
        this.filledContainer.add(3, this.products[3]);

        assertEquals(this.products[0], this.filledContainer.get(0));
        assertEquals(this.products[3], this.filledContainer.get(3));
    }

    @Test
    public void testContainerRemoveEmpty_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertEquals(this.products[2], this.emptyContainer.remove(2));
        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRemoveEmpty_2() {
        Object[] expectedProducts = new Object[]{
                this.products[1],
                this.products[0],
                this.products[3]
        };
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[3]);

        assertTrue(this.emptyContainer.remove(this.products[0]));
        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRemoveEmpty_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[0],
                this.products[3]
        };
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[3]);

        assertFalse(this.emptyContainer.remove(this.products[2]));
        assertEquals(4, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRemoveFilled_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertEquals(this.products[2], this.filledContainer.remove(2));
        assertEquals(3, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRemoveFilled_2() {
        try {
            this.filledContainer.remove(0);
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }
    }

    @Test
    public void testContainerRemoveFilled_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };
        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[3]);

        assertTrue(this.filledContainer.remove(this.products[0]));
        assertEquals(3, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRemoveFilled_4() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };
        this.filledContainer.add(this.products[3]);

        assertFalse(this.filledContainer.remove(this.products[0]));
        assertEquals(3, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRemoveFilled_5() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[0],
                this.products[3]
        };
        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[3]);

        assertFalse(this.filledContainer.remove(this.products[2]));
        assertEquals(4, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRemoveAllEmpty_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[0],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[1]);
        products.add(this.products[2]);
        products.add(this.products[2]);

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertTrue(this.emptyContainer.removeAll(products));
        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRemoveAllEmpty_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[0],
                this.products[1],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertFalse(this.emptyContainer.removeAll(products));
        assertEquals(6, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRemoveAllEmpty_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[0],
                this.products[1],
                this.products[1],
                this.products[2],
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[3]);

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);

        assertFalse(this.emptyContainer.removeAll(products));
        assertEquals(5, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRemoveAllFilled_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[0],
                this.products[0],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[1]);
        products.add(this.products[2]);
        products.add(this.products[2]);

        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertTrue(this.filledContainer.removeAll(products));
        assertEquals(5, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRemoveAllFilled_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[0]);
        products.add(this.products[1]);

        assertFalse(this.filledContainer.removeAll(products));
        assertEquals(2, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRemoveAllFilled_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };
        List<Product> products = new ArrayList<>();

        assertFalse(this.filledContainer.removeAll(products));
        assertEquals(2, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRetainAllEmpty_1() {
        Object[] expectedProducts = new Object[]{
                this.products[1],
                this.products[1],
                this.products[2]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[1]);
        products.add(this.products[2]);

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertTrue(this.emptyContainer.retainAll(products));
        assertEquals(3, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRetainAllEmpty_2() {
        Object[] expectedProducts = new Object[0];
        List<Product> products = new ArrayList<>();

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertTrue(this.emptyContainer.retainAll(products));
        assertEquals(0, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRetainAllEmpty_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[0]);
        products.add(this.products[1]);

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);

        assertFalse(this.emptyContainer.retainAll(products));
        assertEquals(2, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerRetainAllFilled_1() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[1],
                this.products[1],
                this.products[2]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[1]);
        products.add(this.products[2]);

        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertTrue(this.filledContainer.retainAll(products));
        assertEquals(5, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRetainAllFilled_2() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };
        List<Product> products = new ArrayList<>();

        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertTrue(this.filledContainer.retainAll(products));
        assertEquals(2, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerRetainAllFilled_3() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1],
                this.products[3]
        };
        List<Product> products = new ArrayList<>();
        products.add(this.products[3]);

        this.filledContainer.add(this.products[3]);

        assertFalse(this.filledContainer.retainAll(products));
        assertEquals(3, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerClearEmpty() {
        Object[] expectedProducts = new Object[0];

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);
        this.emptyContainer.clear();

        assertEquals(0, this.emptyContainer.size());
        assertArrayEquals(expectedProducts, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerClearFilled() {
        Object[] expectedProducts = new Object[]{
                this.products[0],
                this.products[1]
        };

        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[0]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[1]);
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);
        this.filledContainer.clear();

        assertEquals(2, this.filledContainer.size());
        assertArrayEquals(expectedProducts, this.filledContainer.toArray());
    }

    @Test
    public void testContainerSizeEmpty() {
        assertEquals(0, this.emptyContainer.size());

        this.emptyContainer.add(this.products[0]);
        assertEquals(1, this.emptyContainer.size());

        this.emptyContainer.add(this.products[1]);
        assertEquals(2, this.emptyContainer.size());

        this.emptyContainer.remove(this.products[0]);
        assertEquals(1, this.emptyContainer.size());

        this.emptyContainer.remove(this.products[1]);
        assertEquals(0, this.emptyContainer.size());

        for (int i = 0; i < 100; i++) {
            this.emptyContainer.add(this.products[0]);
        }
        assertEquals(100, this.emptyContainer.size());

        for (int i = 0; i < 25; i++) {
            this.emptyContainer.remove(this.products[0]);
        }
        assertEquals(75, this.emptyContainer.size());
    }

    @Test
    public void testContainerSizeFilled() {
        assertEquals(2, this.filledContainer.size());

        this.filledContainer.add(this.products[0]);
        assertEquals(3, this.filledContainer.size());

        this.filledContainer.add(this.products[1]);
        assertEquals(4, this.filledContainer.size());

        this.filledContainer.remove(this.products[0]);
        assertEquals(3, this.filledContainer.size());

        this.filledContainer.remove(this.products[1]);
        assertEquals(2, this.filledContainer.size());

        for (int i = 0; i < 100; i++) {
            this.filledContainer.add(this.products[0]);
        }
        assertEquals(102, this.filledContainer.size());

        for (int i = 0; i < 25; i++) {
            this.filledContainer.remove(this.products[0]);
        }
        assertEquals(77, this.filledContainer.size());
    }

    @Test
    public void testContainerIsEmptyEmpty_1() {
        assertTrue(this.emptyContainer.isEmpty());
    }

    @Test
    public void testContainerIsEmpty_2() {
        this.emptyContainer.add(this.products[0]);

        assertFalse(this.emptyContainer.isEmpty());
    }

    @Test
    public void testContainerIsEmptyFilled_1() {
        this.filledContainer = new Container<>();
        assertTrue(this.filledContainer.isEmpty());
    }

    @Test
    public void testContainerIsFilled_2() {
        assertFalse(this.filledContainer.isEmpty());
    }

    @Test
    public void testContainerIsFilled_3() {
        filledContainer.add(this.products[2]);
        assertFalse(this.filledContainer.isEmpty());
    }

    @Test
    public void testContainerIndexOfEmpty() {
        this.emptyContainer.add(0, this.products[0]);
        this.emptyContainer.add(1, this.products[1]);
        this.emptyContainer.add(2, this.products[2]);
        this.emptyContainer.add(3, this.products[3]);
        this.emptyContainer.add(4, this.products[0]);

        assertEquals(0, this.emptyContainer.indexOf(this.products[0]));
        assertEquals(2, this.emptyContainer.indexOf(this.products[2]));
    }

    @Test
    public void testContainerIndexOfFilled() {
        this.filledContainer.add(2, this.products[0]);
        this.filledContainer.add(3, this.products[1]);
        this.filledContainer.add(4, this.products[2]);
        this.filledContainer.add(5, this.products[3]);
        this.filledContainer.add(6, this.products[0]);

        assertEquals(0, this.filledContainer.indexOf(this.products[0]));
        assertEquals(4, this.filledContainer.indexOf(this.products[2]));
    }

    @Test
    public void testContainerLastIndexOfEmpty() {
        this.emptyContainer.add(0, this.products[0]);
        this.emptyContainer.add(1, this.products[1]);
        this.emptyContainer.add(2, this.products[2]);
        this.emptyContainer.add(3, this.products[3]);
        this.emptyContainer.add(4, this.products[0]);

        assertEquals(4, this.emptyContainer.lastIndexOf(this.products[0]));
        assertEquals(2, this.emptyContainer.lastIndexOf(this.products[2]));
    }

    @Test
    public void testContainerLastIndexOfFilled() {
        this.filledContainer.add(2, this.products[0]);
        this.filledContainer.add(3, this.products[1]);
        this.filledContainer.add(4, this.products[2]);
        this.filledContainer.add(5, this.products[3]);
        this.filledContainer.add(6, this.products[0]);

        assertEquals(6, this.filledContainer.lastIndexOf(this.products[0]));
        assertEquals(4, this.filledContainer.lastIndexOf(this.products[2]));
    }

    @Test
    public void testContainerContainsEmpty_1() {
        assertFalse(this.emptyContainer.contains(this.products[0]));
    }

    @Test
    public void testContainerContainsEmpty_2() {
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[0]);

        assertTrue(this.emptyContainer.contains(this.products[0]));
    }

    @Test
    public void testContainerContainsFilled_1() {
        assertFalse(this.filledContainer.contains(this.products[2]));
    }

    @Test
    public void testContainerContainsFilled_2() {
        assertTrue(this.filledContainer.contains(this.products[0]));
    }

    @Test
    public void testContainerContainsAllEmpty() {
        List<Product> products = new ArrayList<>();

        assertTrue(this.emptyContainer.containsAll(products));

        this.emptyContainer.add(this.products[0]);

        assertTrue(this.emptyContainer.containsAll(products));

        products.add(this.products[0]);

        assertTrue(this.emptyContainer.containsAll(products));

        products.add(this.products[1]);

        assertFalse(this.emptyContainer.containsAll(products));

        this.emptyContainer.add(this.products[1]);

        assertTrue(this.emptyContainer.containsAll(products));

        products.add(this.products[1]);

        assertTrue(this.emptyContainer.containsAll(products));

        products.add(this.products[2]);

        assertFalse(this.emptyContainer.containsAll(products));
    }

    @Test
    public void testContainerContainsAllFilled() {
        List<Product> products = new ArrayList<>();
        products.add(this.products[0]);
        products.add(this.products[1]);

        assertTrue(this.filledContainer.containsAll(products));

        products.add(this.products[2]);

        assertFalse(this.filledContainer.containsAll(products));

        this.filledContainer.add(this.products[2]);

        assertTrue(this.filledContainer.containsAll(products));
    }

    @Test
    public void testContainerToArrayEmpty_1() {
        Object[] expectedProduct = new Object[]{
                this.products[0],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.emptyContainer.toArray());
    }

    @Test
    public void testContainerToArrayEmpty_2() {
        Product[] expectedProduct = new Product[]{
                this.products[0],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.emptyContainer.toArray(new Product[2]));
    }

    @Test
    public void testContainerToArrayEmpty_3() {
        Product[] expectedProduct = new Product[10];
        expectedProduct[0] = this.products[0];
        expectedProduct[1] = this.products[1];
        expectedProduct[2] = this.products[2];
        expectedProduct[3] = this.products[3];
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.emptyContainer.toArray(new Product[10]));
    }

    @Test
    public void testContainerToArrayFilled_1() {
        Object[] expectedProduct = new Object[]{
                this.products[0],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.filledContainer.toArray());
    }

    @Test
    public void testContainerToArrayFilled_2() {
        Product[] expectedProduct = new Product[]{
                this.products[0],
                this.products[1],
                this.products[2],
                this.products[3]
        };
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.filledContainer.toArray(new Product[2]));
    }

    @Test
    public void testContainerToArrayFilled_3() {
        Product[] expectedProduct = new Product[10];
        expectedProduct[0] = this.products[0];
        expectedProduct[1] = this.products[1];
        expectedProduct[2] = this.products[2];
        expectedProduct[3] = this.products[3];
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertArrayEquals(expectedProduct, this.filledContainer.toArray(new Product[10]));
    }

    @Test
    public void testContainerSubListEmpty() {
        List<Product> expectedListProducts = new Container<>();
        expectedListProducts.add(this.products[1]);
        expectedListProducts.add(this.products[2]);
        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);

        assertArrayEquals(expectedListProducts.toArray(), this.emptyContainer.subList(1, 3).toArray());
        assertThat(expectedListProducts.toArray(), not(equalTo(this.emptyContainer.subList(1, 2).toArray())));
    }

    @Test
    public void testContainerSubListFilled() {
        List<Product> expectedListProducts = new Container<>();
        expectedListProducts.add(this.products[1]);
        expectedListProducts.add(this.products[2]);
        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);

        assertArrayEquals(expectedListProducts.toArray(), this.filledContainer.subList(1, 3).toArray());
        assertThat(expectedListProducts.toArray(), not(equalTo(this.filledContainer.subList(1, 2).toArray())));
    }

    @Test
    public void testContainerEmptyIterator() {
        Iterator<Product> iterator = this.emptyContainer.iterator();

        assertFalse(iterator.hasNext());

        this.emptyContainer.add(this.products[0]);
        this.emptyContainer.add(this.products[1]);
        this.emptyContainer.add(this.products[2]);
        this.emptyContainer.add(this.products[3]);
        iterator = this.emptyContainer.iterator();

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
    public void testContainerFilledIterator() {
        Iterator<Product> iterator = this.filledContainer.iterator();

        assertTrue(iterator.hasNext());

        this.filledContainer.add(this.products[2]);
        this.filledContainer.add(this.products[3]);
        iterator = this.filledContainer.iterator();

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
    public void testContainerFilledListIterator() {
        ListIterator<Product> listIterator = this.filledContainer.listIterator();
        assertTrue(listIterator.hasNext());
        try {
            listIterator.add(this.products[0]);
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }

        listIterator = this.filledContainer.listIterator();
        assertTrue(listIterator.hasNext());
        try {
            listIterator.set(this.products[0]);
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }

        listIterator = this.filledContainer.listIterator();
        assertTrue(listIterator.hasNext());
        try {
            listIterator.remove();
            fail("We need get exception UnmodifiableListException");
        } catch (UnmodifiableListException e) {
            assertTrue("We get exception UnmodifiableListException", true);
        }
    }
}