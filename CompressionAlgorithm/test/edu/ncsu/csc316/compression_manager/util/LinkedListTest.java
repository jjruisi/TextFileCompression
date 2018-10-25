package edu.ncsu.csc316.compression_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the linked list class
 * @author John Ruisi
 *
 */
public class LinkedListTest {

	/**
	 * Tests the constructor
	 */
	@Test
	public void testLinkedList() {
		LinkedList<String> a = new LinkedList<String>();
		assertNotNull(a);
	}
	
	/**
	 * Tests the size method
	 */
	@Test 
	public void testSize() {
		LinkedList<String> b = new LinkedList<String>();
		assertEquals(b.size(), 0);
		
		b.add("hello");
		assertEquals(b.size(), 1);
		
		b.remove(0);
		assertEquals(b.size(), 0);
	}
	
	/**
	 * Tests the is empty method
	 */
	@Test
	public void testIsEmpty() {
		LinkedList<String> c = new LinkedList<String>();
		assertTrue(c.isEmpty());
		
		c.add("hello");
		assertFalse(c.isEmpty());
	}
	
	/**
	 * Tests the get index method
	 */
	@Test
	public void testGetIdx() {
		LinkedList<String> d = new LinkedList<String>();
		d.add("1");
		d.add("2");
		d.add("3");
		
		assertEquals(d.get("55"), -1);
		assertEquals(d.get("1"), 2);
		assertEquals(d.get("2"), 1);
		assertEquals(d.get("3"), 0);
		
	}
	
	/**
	 * Tests the get element method
	 */
	@Test
	public void testGetData() {
		LinkedList<String> e = new LinkedList<String>();
		e.add("1");
		e.add("2");
		e.add("3");
		
		assertEquals(e.get(0), "3");
		assertEquals(e.get(1), "2");
		assertEquals(e.get(2), "1");
		
		try {
			e.get(-1);
			fail();
		} catch (IllegalArgumentException ia) {
			assertEquals(ia.getMessage(), "Invalid Index");
		}
		
		try {
			e.get(23);
			fail();
		} catch (IllegalArgumentException ia) {
			assertEquals(ia.getMessage(), "Invalid Index");
		}
		
	}
	
	/**
	 * Tests the remove method
	 */
	@Test
	public void testRemove() {
		LinkedList<String> f = new LinkedList<String>();
		f.add("1");
		f.add("2");
		f.add("3");
		
		f.remove(2);
		assertEquals(f.get(0), "3");
		
		f.remove(1);
		assertEquals(f.get(0), "3");
		
		f.remove(0);
		assertTrue(f.isEmpty());
		
		try {
			f.remove(-1);
			fail();
		} catch (IllegalArgumentException ia) {
			assertEquals(ia.getMessage(), "Invalid Index");
		}
		
		try {
			f.remove(23);
			fail();
		} catch (IllegalArgumentException ia) {
			assertEquals(ia.getMessage(), "Invalid Index");
		}
	}

}
