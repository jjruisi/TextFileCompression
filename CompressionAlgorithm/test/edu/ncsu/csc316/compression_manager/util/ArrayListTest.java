package edu.ncsu.csc316.compression_manager.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the array list class
 * @author John Ruisi
 *
 */
public class ArrayListTest {

	/**
	 * Tests the constructor
	 */
	@Test
	public void testArrayList() {
		ArrayList<String> a = new ArrayList<String>();
		assertEquals(a.size(), 0);
		assertTrue(a.isEmpty());
	}
	
	/**
	 * Tests the add method
	 */
	@Test
	public void testAdd() {
		ArrayList<String> a = new ArrayList<String>();
		
		a.add("0");
		a.add("1");
		a.add("2");
		a.add("3");
		a.add("4");
		a.add("5");
		a.add("6");
		a.add("7");
		a.add("8");
		a.add("9");
		a.add("10");
		
		assertEquals(a.size(), 11);
		assertFalse(a.isEmpty());
	}
	
	/**
	 * Tests the get method
	 */
	@Test
	public void testGet() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("0");
		assertEquals(a.get(0), "0");
		
		try {
			a.get(14);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}
		
		try {
			a.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}
	}
	
	/**
	 * Test the remove method
	 */
	@Test
	public void testRemove() {
		ArrayList<String> a = new ArrayList<String>();
		a.add("0");
		a.add("1");
		a.add("2");
		
		assertEquals(a.remove(0), "0");
		
		try {
			a.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}
		
		try {
			a.remove(123);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(e.getMessage(), "Invalid Index");
		}
	}
}
