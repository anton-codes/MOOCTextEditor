/**
 * 
 */
package textgen;

import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;


/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	private MyLinkedList<String> shortList;
	private MyLinkedList<Integer> emptyList;
	private MyLinkedList<Integer> longerList;
	private MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUpBeforeEach() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<>();
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
			// Ignored
		}

		emptyList.add(1);
		emptyList.add(2);

		int n = emptyList.get(0);
		assertEquals(1, n);
		n = emptyList.get(1);
		assertEquals(2, n);

		try {
			emptyList.get(2);
			fail("Check index out of bounds");

		} catch (IndexOutOfBoundsException e) {
			// Ignored
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
	public void testRemove() {
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());


		try {
			list1.remove(list1.size());
			fail("list one index out of bound");
			emptyList.remove(0);
			fail("removal from an empty list");
		} catch (IndexOutOfBoundsException e) {
			// Ignored
		}



		emptyList.add(1);
		emptyList.add(2);

		emptyList.remove(0);

		assertEquals("Ensuring correctness of node references",emptyList.head, emptyList.head.next.prev);

		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd() {
		MyLinkedList<Integer> integers = new MyLinkedList<>();

		Assertions.assertFalse(integers.add(null));
		Assertions.assertTrue(integers.add(1));
		Assertions.assertEquals(1, integers.size());

	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{

		assertEquals("Check empty list has the length of 0 ",0, emptyList.size());


		emptyList.add(1);
		assertEquals("Check size increase after addition",1, emptyList.size());
		emptyList.remove(0);
		assertEquals("Check size decrease after removal",0, emptyList.size());
		// TODO: implement this test
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			longerList.add(longerList.size(), 1);
			fail("addition out of bounds");
		} catch (IndexOutOfBoundsException e) {
			// Ignored
		}

		int n = longerList.get(1);
		int m = longerList.get(0);

		longerList.add(1,42);

		assertEquals(42, (int)longerList.get(1));
		assertEquals(n, (int)longerList.get(2));
		assertEquals(m, (int)longerList.get(0));

		int size = longerList.size();

		longerList.add(1, 1);
		assertEquals("Testing size increase after addition",
				size + 1, longerList.size());

	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet() {

		longerList.set(3, 42);
		assertEquals(42, (int)longerList.get(3));
		assertEquals(42, (int)longerList.set(3, 55));


		try {
			emptyList.set(0, 33);
			fail("Index out of bounds");
		} catch (IndexOutOfBoundsException e) {
			// Ignored
		}
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
