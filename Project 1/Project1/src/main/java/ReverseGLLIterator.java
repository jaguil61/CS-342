/*
 * CS 342 Project 1
 * 
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * ReverseGLLIterator.java
 *
 * "This is the class that will be returned when 
 * the descendingIterator() method is called in 
 * the GenericLinkedList class."
 *  
 */

import java.util.Iterator;

public class ReverseGLLIterator<E> implements Iterator<E>
{
	GenericLinkedList<E>.Node<E> curNode;
	GenericLinkedList<E>.Node<E> prevNode;
	
	public ReverseGLLIterator(GenericLinkedList<E>.Node<E> headNode)
	{
		curNode = headNode;
		
		while (curNode.next != null) //stop loop when tail is reached
		{
			prevNode = curNode; // keep track of the previous node
			curNode = curNode.next;
			curNode.prev = prevNode; // link previous node to back to the current node 
		}
	}
	
	@Override
	public boolean hasNext() 
	{
		if (curNode == null)  // list reached the end of the list
			return false;
		
		else
			return true;
	}

	@Override
	public E next() 
	{
		E curData = curNode.data;
		curNode = curNode.prev;
		
		return curData;
	}

}
