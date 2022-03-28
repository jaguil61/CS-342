/* 
 * CS 342 Project 1 (Test Cases)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * GLListIteratorTest.java
 * 
 * Tests the listIterator as well as the listIterator() method from GenericLinkedList
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ListIterator;

public class GLListIteratorTest {
	
	static GenericLinkedList<Integer> myList;
	
	@BeforeEach
	void setup()
	{
		myList = new GenericLinkedList<>(50); // 5
		myList.addFirst(20);  				  // 4
		myList.addFirst(5);   				  // 3
		myList.addFirst(190); 				  // 2
		myList.addFirst(64);  				  // 1
		myList.addFirst(12);  				  // 0
	}
	
	@Test
	void hasNextTest()
	{
		
		
		ListIterator<Integer> listIter = myList.listIterator(2); // Start at 190
		
		listIter.next();
		
		// should return true because the next element is 5
		assertTrue(listIter.hasNext(), "No next value!");
	}
	
	@Test
	void nextTest()
	{
		ListIterator<Integer> listIter = myList.listIterator(3); // Start at 5
		
		// Value should be 5
		assertEquals(5, listIter.next(), "Incorrect value!");
		// Value should 10
		assertEquals(20, listIter.next(), "Incorrect value!");
	}
	
	@Test
	void hasPreviousTest()
	{
		ListIterator<Integer> listIter = myList.listIterator(5); // Start at 50
		
		listIter.previous();
		
		// should return return true because 20 is the value before 50
		assertTrue(listIter.hasPrevious(), "No previous value!");
	}
	
	@Test
	void previousTest()
	{
		ListIterator<Integer> listIter = myList.listIterator(4); // Start at 20
		
		// should return 20
		assertEquals(20, listIter.previous(), "Wrong value returned!");
		//should return 5
		assertEquals(5, listIter.previous(), "Wrong value returned!");
	}
	
	@Test
	void nextIndex()
	{
		ListIterator<Integer> listIter = myList.listIterator(2); // Start at 20
		
		// next index should be 3
		assertEquals(3, listIter.nextIndex(), "Wrong index returned!");
	}
	
	void previousIndex()
	{
		ListIterator<Integer> listIter = myList.listIterator(2); // Start at 20
		
		// next index should be 1
		assertEquals(1, listIter.nextIndex(), "Wrong index returned!");
	}
}
