/*
 * CS 342 Project 1
 * 
 * Jose M. Aguilar 
 * Jaguil61@uic.edu
 * 
 * GLListIterator.java
 *  
 *  "This is the class that will be returned when
 *  the listIterator(int index) method is called 
 *  in the GenericLinkedList class."
 *  
 */

import java.util.ListIterator;

public class GLListIterator<E> implements ListIterator<E>
{	
	GenericLinkedList<E>.Node<E> curNode;
	GenericLinkedList<E>.Node<E> prevNode;
	int curIndex;
	
	public GLListIterator(GenericLinkedList<E>.Node<E> headNode, int index) 
	{
		curIndex = index;
		curNode = headNode;
		
		int indexCounter = 0;
		
		while (indexCounter != index) //stop loop when the index is reached
		{
			prevNode = curNode; // keep track of the previous node
			curNode = curNode.next;
			curNode.prev = prevNode; // link previous node to back to the current node 

			indexCounter++;
		}
	}

	@Override
	public boolean hasNext() 
	{
		if (curNode == null) // list reached the end of the list
			return false;
		else
			return true;
	}

	@Override
	public E next() 
	{
		E curData = curNode.data;
		curNode = curNode.next;
		curIndex++; // increment index
		
		return curData;
	}

	@Override
	public boolean hasPrevious() 
	{
		if (curNode == null) // list reached the end of the list
			return false;
		
		else
			return true;
	}

	@Override
	public E previous() 
	{
		E curData = curNode.data;
		curNode = curNode.prev;
		curIndex--; // increment index
		
		return curData;
	}

	@Override
	public int nextIndex() 
	{
		int potIndex = curIndex; // the potential index if next() would be called
		
		return potIndex + 1;
	}

	@Override
	public int previousIndex() 
	{	
		int potIndex = curIndex; // the potential index if previous() would be called
		
		return potIndex - 1;
	}

	// Do not need to implement
	@Override 
	public void remove() {
		// TODO Auto-generated method stub
		
	}
	
	// Do not need to implement
	@Override
	public void set(E e) {
		// TODO Auto-generated method stub
		
	}
	
	// Do not need to implement
	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
	}

}
