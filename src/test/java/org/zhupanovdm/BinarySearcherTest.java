package org.zhupanovdm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearcherTest {

    @Test
    public void testEven() {
        BinarySearcher<Integer> searcher = new BinarySearcher<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 });
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
        BinarySearcher<Integer> searcher = new BinarySearcher<>(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
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
        BinarySearcher<Integer> searcher = new BinarySearcher<>(new Integer[] {});
        assertEquals(-1, searcher.search(1));
    }

    @Test
    public void testSingle() {
        BinarySearcher<Integer> searcher = new BinarySearcher<>(new Integer[] { 1 });
        assertEquals(-1, searcher.search(-1));
        assertEquals(0, searcher.search(1));
        assertEquals(-1, searcher.search(2));
    }

    @Test
    public void testDouble() {
        BinarySearcher<Integer> searcher = new BinarySearcher<>(new Integer[] { 1, 2 });
        assertEquals(-1, searcher.search(0));
        assertEquals(0, searcher.search(1));
        assertEquals(1, searcher.search(2));
        assertEquals(-1, searcher.search(3));
    }

    @Test
    public void testTriple() {
        BinarySearcher<Integer> searcher = new BinarySearcher<>(new Integer[] { 1, 2, 3 });
        assertEquals(-1, searcher.search(0));
        assertEquals(0, searcher.search(1));
        assertEquals(1, searcher.search(2));
        assertEquals(2, searcher.search(3));
        assertEquals(-1, searcher.search(4));
    }

}