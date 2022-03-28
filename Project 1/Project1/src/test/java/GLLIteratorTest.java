/* 
 * CS 342 Project 1 (Test Cases)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * GLLIteratorTest.java
 * 
 * Tests the iterator interface as well as the iterator() method 
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

class GLLIteratorTest 
{
	static GenericLinkedList<Integer> myList;
		
	@BeforeEach
	void setup()
	{
		myList = new GenericLinkedList<>(50);	
	}
	
	@Test
	void hasNextTest()
	{	
		Iterator<Integer> listIter = myList.iterator();
		
		listIter.next(); // Move to next otherwise it'd be true because it reads in the curNode
		
		// Should be false because there should be nothing after the head 
		assertFalse(listIter.hasNext(), "There is something next!");
	}
	
	
	
	@Test
	void nextTest()
	{
		myList.addFirst(-17);
		
		Iterator<Integer> listIter = myList.iterator();
		
		// First call should give the head (50) and next call should give null
		assertEquals(-17, listIter.next(), "Correct element not given!");
		assertEquals(50, listIter.next(), "Element was not moved next!");
		
	} 
}
