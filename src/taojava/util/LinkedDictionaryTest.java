package taojava.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedDictionaryTest {

	LinkedDictionary<String,String> dict = new LinkedDictionary<String,String>();
	
	/** 
	 * See if we can add a few elements and get them again.
	 */
	@Test
	public void testAdd() throws Exception {
		dict.set("a", "alpha");
		assertEquals(dict.get("a"), "alpha");
		dict.set("b", "beta");
		assertEquals(dict.get("b"), "beta");
		// Let's make sure that alpha is still there.
		assertEquals(dict.get("a"), "alpha");
	} // testAdd
	
	/**
	 * Another test of the status of the dictionary; a followup
	 * to the previous test.
	 */
	public void testAdd2() throws Exception {
		// We added alpha in testAdd, so it should still be there.
		assertEquals(dict.get("a"), "alpha");
	} // testAdd2
	
	/**
	 * Test replacement
	 */
	@Test
	public void testReplace() throws Exception {
		dict.set("a", "alpha");
		assertEquals(dict.get("a"), "alpha");
		dict.set("b", "beta");
		assertEquals(dict.get("b"), "beta");
		dict.set("a", "aardvark");
		dict.set("b", "baboon");
		assertEquals(dict.get("a"), "aardvark"); // changed to "aardvark" from "alpha"
		assertEquals(dict.get("b"), "baboon");
	} // testReplace
	
	/*
	 * Test removal
	 */
	@Test
	public void testRemove() throws Exception {
		dict.set("a", "alpha");
		assertEquals(dict.get("a"), "alpha");
		dict.set("b", "beta");
		assertEquals(dict.get("b"), "beta");
		dict.set("c", "clean");
		assertEquals(dict.get("c"), "clean"); // added
		assertEquals(dict.remove("b"), "beta");
		assertEquals(dict.get("a"), "alpha");
		assertEquals(dict.get("c"), "clean");
		assertEquals(dict.containsKey("b"), false); // changed from "true" to "false"
	} // testRemove
} // LinkedDictionaryTest
