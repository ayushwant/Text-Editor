package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() 
	{
		// TODO: Implement this method
		size=0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		head.next=tail;
		tail.prev=head;
		
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
		LLNode<E> x = tail.prev;
		LLNode<E> y = new LLNode<E>(element);
		x.next = y;
		y.prev = x;
		y.next = tail;
		tail.prev = y;
		this.size++;
		
		
		return false;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method.
		if(this.size==0 || index<0 || index>=this.size)
		{
			throw new IndexOutOfBoundsException("check");
		}
		
		LLNode<E> a = head.next;
		for(int i=0; i<index; i++)
		{
			a = a.next;
		}
		
		return a.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if(index<0 || index>this.size&&size!=0 ) 
		{ throw new IndexOutOfBoundsException("check"); }
		
		if(element == null)
		{ throw new NullPointerException(); }
		
		LLNode<E> a = head.next;
		for(int i=0; i<index; i++) a=a.next;
		
		LLNode<E> x = a.prev;
		LLNode<E> y = new LLNode<E>(element);
		
		x.next = y;
		y.prev = x;
		y.next =a;
		a.prev = y;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		int num=1;
		
		LLNode<E> a = head.next;
		
		while(a.next!=tail)
		{
			a=a.next;
			num++;
		}
		
		return num;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if(this.size==0 || index<0 || index>=this.size) 
		{ throw new IndexOutOfBoundsException("check"); }
	
		LLNode<E> a = head.next;
		for(int i=0; i<index; i++) 
		{ a = a.next; }
		LLNode<E> x = a.prev;
		LLNode<E> y = a.next;
		x.next = y;
		y.prev = x;

		E ret = a.data;
		
		return ret;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException
	{
		// TODO: Implement this method
		if(this.size==0 || index<0 || index>=this.size) 
		{ throw new IndexOutOfBoundsException("check"); }
		
		if(element == null)
		{ throw new NullPointerException(); }
	
		LLNode<E> a = head.next;
		for(int i=0; i<index; i++) 
		{ a = a.next; }
		a.data = element;
		
		return a.data;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
