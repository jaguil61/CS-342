/* 
 * CS 342 Project 1 (Test Cases)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * GenericLinkedListTest.java
 * 
 * Tests the methods in GenericLinkedList.java
 * 
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;

class GenericLinkedListTest 
{
	static GenericLinkedList<Integer> myList;
		
	@BeforeEach
	void setup()
	{
		myList = new GenericLinkedList<>(50);	
	}
	
	@Test
	void ConstructorTest()
	{
		// Head node should 50
		assertEquals(50, myList.get(0), "Head not intialized!");
		
		// size should 1
		assertEquals(1, myList.size(), "Wrong size!");
	}
	
	@Test
	void addFirstTest()
	{
		myList.addFirst(115);
		
		Iterator<Integer> listIter = myList.iterator();
		
		//First item in the list should be 115 now
		assertEquals(115, listIter.next(), "Elements were not added at the front!");
	}
	
	@Test
	void addLastTest()
	{
		myList.addLast(0);
		myList.addLast(-7);
		
		Iterator<Integer> listIter = myList.iterator();
		
		// first item in the list should still be 50 and
		assertEquals(50, listIter.next(), "Elements were not added at the front!");
		
		listIter.next(); // iterate to last element in list
		
		// last value should be -7
		assertEquals(-7, listIter.next(), "Elements were not added at the front!");
	}
	
	@Test
	void sizeTest()
	{	
		myList.addFirst(-15);
		myList.addFirst(117);
		
		// Size of list should be +1 more than expected because the setup function adds one
		assertEquals(3, myList.size(), "Wrong Size!");
	}
	
	@Test
	void containsTest()
	{
		myList.addFirst(-150);
		myList.addFirst(8);
		myList.addFirst(-56);
		myList.addFirst(143);
		myList.addFirst(-30);
		myList.addFirst(917);
		
		
		// should find the element 917 
		assertTrue(myList.contains(917), "Element not found!");
		// should find the element -150 
		assertTrue(myList.contains(-150), "Element not found!");
		// should find the element 50 
		assertTrue(myList.contains(50), "Element not found!");
	}
	
	@Test
	void removeTest()
	{
		myList.addFirst(-150);
		myList.addFirst(8);
		myList.addFirst(-56);
		myList.addFirst(143);
		myList.addFirst(-30);
		myList.addFirst(917);
		
		// should come back true if removed 50 was removed
		assertTrue(myList.remove(50), "Element was not removed or found!");
		
		//should return false because it was removed
		assertFalse(myList.contains(50), "Element was not removed!");
		
		// should come back true if removed 50 was removed
		assertTrue(myList.remove(143), "Element was not removed or found!");
				
		// should return false because it was removed
		assertFalse(myList.contains(143), "Element was not removed!");
		
		// list size should be 5 now
		assertEquals(5, myList.size(), "Wrong size returned!");
	}
	
	@Test
	void clearTest()
	{
		myList.addFirst(-10);
		myList.addFirst(81);
		myList.addFirst(-6);
		myList.addFirst(1243);
		myList.addFirst(-10);
		myList.addFirst(97);
		
		myList.clear(); // call method
		
		//should return false because it was removed
		assertFalse(myList.contains(50), "Element was not removed!");
		
		// should return false because it was removed
		assertFalse(myList.contains(97), "Element was not removed!");
				
		// list size should be 5 now
		assertEquals(0, myList.size(), "Wrong size returned!");
	}
	
	@Test
	void getTest()
	{
		myList.addFirst(-101);
		myList.addFirst(811);
		myList.addFirst(-16);
		myList.addFirst(1243);
		myList.addFirst(-130);
		myList.addFirst(971);
		
		// should return the head element which is 971
		assertEquals(971, myList.get(0), "Wrong element returned!");
		
		// should return the element 1243
		assertEquals(1243, myList.get(2), "Wrong element returned!");
		
		// should return the tail element which is 50
		assertEquals(50, myList.get(6), "Wrong element returned!");
	}
	
	@Test
	void setTest()
	{
		myList.addFirst(-101);
		myList.addFirst(811);
		myList.addFirst(-16);
		myList.addFirst(1243);
		myList.addFirst(-130);
		myList.addFirst(971);
		
		// should return the head element which is 971
		assertEquals(971, myList.set(0, 100), "Wrong element returned!");
		
		// should return the element 1243
		assertEquals(1243, myList.set(2, 101), "Wrong element returned!");
		
		// should return the tail element which is 50
		assertEquals(50, myList.set(6, 102), "Wrong element returned!");
		
		// should return the new head element which is 100
		assertEquals(100, myList.get(0), "Wrong element returned!");
				
		// should return the new element 101
		assertEquals(101, myList.get(2), "Wrong element returned!");
			
		// should return the new tail element which is 102
		assertEquals(102, myList.get(6), "Wrong element returned!");
	}
	
	@Test
	void removeHeadTest()
	{
		// Should return the head of the list which is 50
		assertEquals(50, myList.removeHead(), "Head was not returned!");
		
		// Since there was only on element the size should be 0
		assertEquals(0, myList.size(), "Incorrect size!");
		
		myList.addFirst(-130);
		myList.addFirst(971);
		
		// Should return the head of the list which is 971
		assertEquals(971, myList.removeHead(), "Head was not returned!");
		
		// Size should be 1
		assertEquals(1, myList.size(), "Incorrect size!");
		
		// New head should be -130
		assertEquals(-130, myList.get(0), "Wrong head returned");
	}

	@Test
	void removeTailTest()
	{
		// Should return the tail of the list which is 50
		assertEquals(50, myList.removeTail(), "Tail was not returned!");
				
		// Since there was only on element the size should be 0
		assertEquals(0, myList.size(), "Incorrect size!");
		
		myList.addFirst(-130);
		myList.addFirst(971);
		myList.addFirst(71);
		
		// Should return the tail of the list which is -130
		assertEquals(-130, myList.removeTail(), "Tail was not returned!");
		
		// Size should be 2
		assertEquals(2, myList.size(), "Incorrect size!");
		
		// New tail should be -130
		assertEquals(971, myList.get(1), "Wrong tail returned");
	}
	
	@Test 
	void ForEachTest()
	{
		myList.addFirst(-10);
		myList.addFirst(91);
		myList.addFirst(7);

		int arrayIter = 0; // array iterator
		int testArr[] = {7, 91, -10, 50};
		
		for(int i: myList)
		{
			/* if Iterator was implemented correctly the values 
			 * in myList should match the ones in the testArray
			 */
			assertEquals(testArr[arrayIter], i, "Wrong values inserted!");
			
			arrayIter++; // increment array iterator
		}
	}
	
	// Tests for listIterator() and descendingIterator() in separate files
	
}
