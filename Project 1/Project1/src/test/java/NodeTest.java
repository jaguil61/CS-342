/* 
 * CS 342 Project 1 (Test Cases)
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * NodeTest.java
 * 
 * Tests nested Node class in GenericLinkedList.java
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NodeTest {

	static GenericLinkedList<Integer> myList;
	
	@BeforeEach
	void setup()
	{
		myList = new GenericLinkedList<>(50);	
	}
	
	@Test
	void ConstructorTest()
	{
		GenericLinkedList<Integer>.Node<Integer> curNode = myList.new Node<>(-210);
		
		// curNodes data value should be -210
		assertEquals(-210, curNode.data, "Node not initialized!"); 
	}
	
	@Test 
	void LinkTest()
	{
		// Initiate nodes
		GenericLinkedList<Integer>.Node<Integer> prevNode = myList.new Node<>(60);
		GenericLinkedList<Integer>.Node<Integer> curNode = myList.new Node<>(20);
		GenericLinkedList<Integer>.Node<Integer> nextNode = myList.new Node<>(100);
		
		// Link the nodes together
		curNode.prev = prevNode;
		curNode.next = nextNode;
		
		// curNodes.next data value should now be 60
		assertEquals(60, curNode.prev.data, "Prev node not linked!");
		
		// curNodes.prev data value should now be 100
		assertEquals(100, curNode.next.data, "Next node not linked!");
	}
	
}
