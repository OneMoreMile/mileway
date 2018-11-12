package com.wangdian.mile.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by bigv on 3/29/2017.
 */
public class ArrayUtilTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsNotEmpty() throws Exception {
        Object[] array = new Object[]{"abc"};
        assertTrue(ArrayUtil.isNotEmpty(array));
    }

    @Test
    public void testIsEmpty() throws Exception {
        Object[] array = null;
        assertTrue(ArrayUtil.isEmpty(array));

        array = new Object[0];
        assertTrue(ArrayUtil.isEmpty(array));
    }

    @Test
    public void testConcat() throws Exception {
        Object[] array1 = new Object[]{"abc", "123","%^&"};
        Object[] array2 = new Object[]{"efg"};

        assertEquals(4, ArrayUtil.concat(array1, array2).length);
    }

    @Test
    public void testContains() throws Exception {
        Object[] array1 = new Object[]{"abc", "123","%^&"};
        assertTrue(ArrayUtil.contains(array1, "abc"));
    }
}