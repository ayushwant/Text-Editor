/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		
		try {
			shortList.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.remove(8);
			fail("Check out or Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		emptyList.add(1);
		shortList.add("Z");
		list1.add(99);
		
		assertEquals("Check 0th", (Integer)1, emptyList.get(0));
		try {
			emptyList.get(1);
			fail("Check out or Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		assertEquals("Check 2nd", "Z", shortList.get(2));
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		try {
			shortList.get(3);
			fail("Check out or Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		assertEquals("Check 3rd", (Integer)99 , list1.get(3));
		
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("size before", 2, shortList.size());
		assertEquals("size before", 3, list1.size());
		
		shortList.add(2,"C");
		list1.add(2,33);
		shortList.add("Z");
		list1.add(99);
		
		assertEquals("size later", 4, shortList.size());
		assertEquals("size before", 5, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test

		shortList.add(2,"C");
		list1.add(2,33);
		shortList.add("Z");
		list1.add(99);
		
		assertEquals("Check 1st", "B", shortList.get(1));
		assertEquals("Check 2nd", "C", shortList.get(2));
		assertEquals("Check 3rd", "Z", shortList.get(3));
		
		assertEquals("Check 3rd", (Integer)42 , list1.get(3));
		assertEquals("Check 2nd", (Integer)33 , list1.get(2));
		assertEquals("Check 1st", (Integer)21 , list1.get(1));
		assertEquals("Check 4th", (Integer)99 , list1.get(4));
		
		try {
			shortList.add(-1,"k");
			fail("Check out of Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.add(9,"l");
			fail("Check out of Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.add(1,null);
			fail("Check out of Bounds");
		}
		catch (NullPointerException x) {}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		list1.set(0, 1);
		list1.set(1, 37);
		list1.set(2, 55);
	    
		assertEquals("Check 3rd", (Integer)1 , list1.get(0));
		assertEquals("Check 2nd", (Integer)37 , list1.get(1));
		assertEquals("Check 1st", (Integer)55 , list1.get(2));
		
		try {
			shortList.set(8,"99");
			fail("Check out or Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.set(-1,"6");
			fail("Check out or Bounds");
		}
		catch (IndexOutOfBoundsException e) {}
		
		try {
			shortList.set(1,null);
			fail("Check out of Bounds");
		}
		catch (NullPointerException x) {}
	}
	
	
	// TODO: Optionally add more test methods.
	
}
