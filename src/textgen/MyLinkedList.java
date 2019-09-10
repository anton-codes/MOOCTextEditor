package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E>  {
	LLNode<E> head;
	LLNode<E> tail;
	private int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {

		head = new LLNode<>();
		tail = new LLNode<>();

		head.prev = null;
		tail.next = null;

		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) {

		if (element == null)
			return false;

		LLNode<E> node = new LLNode<>(element);

		node.next = tail;
		node.prev = tail.prev;

		tail.prev.next = node;
		tail.prev = node;

		size++;
		return true;
	}



	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(Integer.toString(index));

		LLNode<E> node = head;
		for (int i = -1; i < index; i++) {
			node = node.next;
		}
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) {
		if (element == null)
			throw new IllegalArgumentException("null");

		if (size == 0) {
			add(element);
			return;
		}

		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(Integer.toString(index));

		LLNode<E> currentNode = head;
		for (int i = -1; i < index; i++) {
			currentNode = currentNode.next;
		}

		// add a new node before the current node
		LLNode<E> node = new LLNode<>(element);

		node.next = currentNode;
		node.prev = currentNode.prev;

		currentNode.prev.next = node;
		currentNode.prev = node;

		size++;

	}


	/** Return the size of the list */
	public int size() {
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) {


		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(Integer.toString(index));

		LLNode<E> node = head;
		for (int i = -1; i < index; i++) {
			node = node.next;
		}

		node.prev.next = node.next;
		node.next.prev = node.prev;

		node.next = null;
		node.prev = null;

		size--;
		return node.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException(Integer.toString(index));

		LLNode<E> node = head;
		for (int i = -1; i < index; i++) {
			node = node.next;
		}

		E oldData = node.data;
		node.data = element;

		return oldData;
	}

	void print() {
		LLNode node = head;
		System.out.print("{ ");
		while (node.next.data != null) {
			System.out.print(node.next.data + " ");
			node = node.next;
		}
		System.out.println("}");
	}
}

class LLNode<E> {
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) {
		this.data = e;
		this.prev = null;
		this.next = null;
	}

	/**
	 * Default constructor
	 */
	public LLNode() {
		data = null;
		next = null;
		prev = null;
	}


}
