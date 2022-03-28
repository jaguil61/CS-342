/*
 * CS 342 Project 1
 * 
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * GLLIterator.java
 * 
 * "This is the class that will be returned when the 
 * iterator() method is called from the Iterable<T> interface."
 *  
 */

import java.util.Iterator;

public class GLLIterator<E> implements Iterator<E>
{
	GenericLinkedList<E>.Node<E> curNode;

	public GLLIterator(GenericLinkedList<E>.Node<E> headNode)
	{
		curNode = headNode;
	}
	
	/*
	 * Checks to see if there is another value in the data 
	 * structure and returns true or false
	 *
	 */
	@Override
	public boolean hasNext() 
	{
		if (curNode == null) // list reached the end of the list
			return false;
		
		else
			return true;
	}
	
	/*
	 * Returns the current value in the data structure and 
	 * advances to the next item
	 * 
	 */
	@Override
	public E next() 
	{	
		E curData;
		
		curData = curNode.data; // storing value
		curNode = curNode.next; // advance to next node
		
		return curData;
	}

}
