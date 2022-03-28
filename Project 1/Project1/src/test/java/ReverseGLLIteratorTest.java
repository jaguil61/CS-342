/* 
 * CS 342 Project 1 (Test Cases)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * ReverseGLLIteratorTest.java
 * 
 * Tests the reverse iterator as well as the descendingIterator() method
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

public class ReverseGLLIteratorTest {
	
	static GenericLinkedList<Integer> myList;
	
	@BeforeEach
	void setup()
	{
		myList = new GenericLinkedList<>(50);	
	}
	
	@Test
	void hasNextTest()
	{	
		Iterator<Integer> revListIter = myList.descendingIterator();
		
		revListIter.next(); // Move to next otherwise it'd be true because it reads in the curNode
		
		// Should be false because there should be nothing after the head 
		assertFalse(revListIter.hasNext(), "There is something next!");
	}
		
	@Test
	void nextTest()
	{
		myList.addFirst(-17);
		
		Iterator<Integer> revListIter = myList.descendingIterator();
		
		// First call should give the head (50) and next call should give null
		assertEquals(50, revListIter.next(), "Correct element not given!");
		assertEquals(-17, revListIter.next(), "Element was not moved next!");
	}
}
