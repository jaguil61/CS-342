/*
 * CS 342 Project 1
 * 
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * GenericLinkedList.java
 * 
 * "In this simplified version, your data structure is a doubly 
 * linked list. Your implementation must be generic, like ArrayList, 
 * as to allow for different types when each data structure
 * object is instantiated."
 *  
 */

import java.lang.Iterable;
import java.util.Iterator;
import java.util.ListIterator;

public class GenericLinkedList<T> implements Iterable<T>
{
	private Node<T> head; // This is the head of the list
	private int length = 0; // The length of the list
	
	// Constructor
	public GenericLinkedList(T val) 
	{
		this.head = new Node<>(val);
		this.length++;
	}
	
	//This inner class is to be used to create nodes, in the linked list class
	class Node<T> 
	{
		T data;
		Node<T> next;
		Node<T> prev;
		
		// Constructor
		public Node(T val)
		{
			this.data = val;
		}
	}
	
	/*
	 *  Auto generated because The type GenericLinkedList<T> 
	 *  must implement the inherited abstract method Iterable<T>.iterator()
	*/
	@Override
	public Iterator<T> iterator() 
	{
		return new GLLIterator<>(this.head);
	}
	
	// inserts the specified element at the beginning of the list
	public void addFirst(T e)
	{
		Node<T> newNode = new Node<>(e); // new Node with passed element
		
		if (this.head == null) // head is null so add at the start
		{
			this.head = newNode;
			this.length++; // increase the size of the list since a node was added
		}
		
		else // head is not null so swap the head with the new node and move the old head node to next
		{
			Node<T> tempNode = this.head;
			this.head = newNode;
			this.head.next = tempNode;
			this.length++; // increase the size of the list since a node was added
		}
	}
	
	// inserts the specified element at the end of the list
	public void addLast(T e)
	{
		Node<T> newNode = new Node<>(e); // new Node with passed element
		
		if (this.head == null)
		{
			this.head = newNode;
			this.length++;
		}
		
		else // head was not null
		{
			Node<T> tempNode = this.head; // used for traversal
			
			// keep moving next until the tail is found (next value should should be null)
			while (tempNode.next != null)
				tempNode = tempNode.next;
			
			// once the tail is found make the next value equal to the new node
			tempNode.next = newNode;
			this.length++;
		}
	}
	
	// returns the number of elements in the list
	public int size()
	{	
		return this.length;
	}
	
	// returns true if the list contains the specified element
	public boolean contains(T e)
	{
		Node<T> tempNode = this.head;
		
		if (this.head == null) // empty list
			return false;
			
		while (tempNode != null) // traverse until the end of the list is hit
		{	
			if (tempNode.data.equals(e))
				return true; // matching element was found so exit method
			
			// else data does not match so keep going
			tempNode = tempNode.next;
		}
		
		return false; // the end was reached and element was not found
	}
	
	// removes the first occurrence of the specified element and returns true or returns false if the element does not exist.
	public boolean remove(T e)
	{
		// check if value is in the list
		if (contains(e) == false) 
			return false; // element not found
		
		// head is the only node in the list
		else if (this.head.data.equals(e) && this.head.next == null)
		{
			this.head = null;
			this.length = 0; // empty list
			
			return true;
		}
		
		// the head is the one that needs to be deleted 
		else if (this.head.data.equals(e))
		{
			this.head.data = this.head.next.data;
			this.head.next = this.head.next.next;
			this.length--; // empty list
					
			return true;
		}
		
		
		// else element must be in the list so traverse until found
		Node<T> prevNode = this.head;
		
		// keep looping until the node with the correct element is found
		while (prevNode != null && !prevNode.next.data.equals(e)) // prevNode.next is new current node
			prevNode = prevNode.next;
		
		// link the previous nodes next node to the node after the current node
		prevNode.next = prevNode.next.next;
		
		this.length--;
		
		return true; // node was removed
	}
	
	// removes all elements from the list
	public void clear()
	{
		this.head = null; 
		this.length = 0;
	}
	
	// returns the element at the specified index or null if the index is out of bounds
	public T get(int index)
	{
		// check bounds
		if (index > this.length - 1 || index < 0) // index is out of bounds
			return null;
		
		// check the head element
		else if (index == 0)
			return this.head.data;
		
		
		// else keep going through the rest of the list
		int indexCounter = 0;
		Node<T> tempNode = this.head;
		
		// else search for the element at the given index
		while (indexCounter != index) //stop loop when the index is reached
		{
			tempNode = tempNode.next;
			indexCounter++;
		}
		
		return tempNode.data;
	}
	
	/*
	 * Replace the element at specified position in the list with the 
	 * specified element and return the element previously at the specified position.
	 * Return null if index is out of bounds
	 */ 
	public T set(int index, T element)
	{
		Node<T> tempNode = this.head;
		T dataFound;
		
		// Checks the bounds
		if (index > this.length - 1 || index < 0) 
			return null; // invalid index 			
		
		// the head is the one that needs to be deleted/replaced
		else if (index == 0)
		{
			dataFound = tempNode.data; // store old element
			this.head.data = element; // assign new element
			
			return dataFound; // return what used to be
		}
		
		// else keep going through the rest of the list
		int indexCounter = 0;
		
		// else search for the element at the given index
		while (indexCounter != index) //stop loop when the index is reached
		{
			tempNode = tempNode.next;
			indexCounter++;
		}
		
		dataFound = tempNode.data; // store old element
		tempNode.data = element; // assign new element
		
		return dataFound;
	}
	
	// retrieves and removes the head of the list
	public T removeHead()
	{
		if (this.head == null) // empty list
			return null;
		
		T dataFound = this.head.data; // store head element
		
		this.head = this.head.next; // assign next node as new head
		
		this.length--;
		
		return dataFound;
	}
	
	// retrieves and removes the tail of the list
	public T removeTail()
	{		
		Node<T> tempNode = this.head; // used for traversal
		T dataFound;
		
		if (tempNode == null) // empty list
			return null;
		
		// Only one element in list. Element is both head/tail
		else if(tempNode.next == null)
		{
			dataFound = tempNode.data;
			this.head = null;
			this.length --;
			
			return dataFound;
		}
			
		// keep moving next until the tail is found (next value should should be null)
		while (tempNode.next != null)
			tempNode = tempNode.next;
			
		// once the tail is found store it's data and remove it
		dataFound = tempNode.data;
		tempNode = null;
		this.length--;
		
		return dataFound;
	}
		
	// TODO: returns a list-iterator of the elements of this list starting at the specified position.
	public ListIterator<T> listIterator(int index)
	{
		return new GLListIterator<T>(this.head, index);
	}
	
	
	// returns an iterator over the elements of the list in reverse order (tail to head)
	public Iterator<T> descendingIterator()
	{
		return new ReverseGLLIterator<>(this.head);
	}
	
}

