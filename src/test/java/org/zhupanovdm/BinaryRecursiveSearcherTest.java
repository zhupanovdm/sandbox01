package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryRecursiveSearcherTest {

    @Test
    public void testEven() {
        BinaryRecursiveSearcher<Integer> searcher = new BinaryRecursiveSearcher<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
        assertEquals(-1, searcher.search(-1));
        assertEquals(0, searcher.search(0));
        assertEquals(4, searcher.search(4));
        assertEquals(5, searcher.search(5));
        assertEquals(6, searcher.search(6));
        assertEquals(9, searcher.search(9));
        assertEquals(-1, searcher.search(10));
    }

    @Test
    public void testNonEven() {
        BinaryRecursiveSearcher<Integer> searcher = new BinaryRecursiveSearcher<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        assertEquals(-1, searcher.search(-1));
        assertEquals(0, searcher.search(0));
        assertEquals(4, searcher.search(4));
        assertEquals(5, searcher.search(5));
        assertEquals(6, searcher.search(6));
        assertEquals(7, searcher.search(7));
        assertEquals(10, searcher.search(10));
        assertEquals(-1, searcher.search(11));
    }

    @Test
    public void testEmpty() {
        BinaryRecursiveSearcher<Integer> searcher = new BinaryRecursiveSearcher<>(new Integer[] {});
        assertEquals(-1, searcher.search(1));
    }

    @Test
    public void testSingle() {
        BinaryRecursiveSearcher<Integer> searcher = new BinaryRecursiveSearcher<>(new Integer[] { 1 });
        assertEquals(-1, searcher.search(-1));
        assertEquals(0, searcher.search(1));
        assertEquals(-1, searcher.search(2));
    }

    @Test
    public void testDouble() {
        BinaryRecursiveSearcher<Integer> searcher = new BinaryRecursiveSearcher<>(new Integer[] { 1, 2 });
        assertEquals(-1, searcher.search(0));
        assertEquals(0, searcher.search(1));
        assertEquals(1, searcher.search(2));
        assertEquals(-1, searcher.search(3));
    }

    @Test
    public void testTriple() {
        BinaryRecursiveSearcher<Integer> searcher = new BinaryRecursiveSearcher<>(new Integer[] { 1, 2, 3 });
        assertEquals(-1, searcher.search(0));
        assertEquals(0, searcher.search(1));
        assertEquals(1, searcher.search(2));
        assertEquals(2, searcher.search(3));
        assertEquals(-1, searcher.search(4));
    }

}