/****************************************************
//Name:   Aditya Tikhe P:2
//Date: 2/22/19
// What I learned: I learned how heaps work. Heaps are a lot like binary trees. 
 * 	Inserting a heap and deleting a heap are both very interesting as you have to readjusts the rest of the tree to make sure it follows the definition of a heap
 
// How I feel about this lab:
 - I liked this lab as it applied what I learned in class. I understand how heaps work with an array implementation with the indexing short cut taught in class.
 - This lab took me 2 days but it was very enjoyable to work through the reheapUp and down methods. 

// I am wondering (the what-if moment):
 * 	Since I am completing the finishing touches such as making the scanner for this lab after we learned about heap sort. I was wondering how to efficiently
    code it. 
 * Also I want to make the best/efficient code for heap up and down because I feel like my code is slightly more clunky than it needs to be. 
 
// Credits: n/a
****************************************************/
import java.util.*;

public class P2AdityaTikhe_HeapPriorityQueue_shell<E extends Comparable> {
	private static final int DEFAULT_CAPACITY = 1024;
	private E items[]; // use a 1-D array instead of ArrayList
	private int numItems; // number of elements in items

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the initial length of the heap: ");
		int length = sc.nextInt();
		P2AdityaTikhe_HeapPriorityQueue_shell heap = new P2AdityaTikhe_HeapPriorityQueue_shell(length);
		int choice = 0;
		while (choice != 3) {
			System.out.println(
					"Enter (a number) what you want to do with the heap.\n(1). add an element\n(2). remove an element\n(3). quit\n");
			try{
			choice = sc.nextInt();
			}
			catch (Exception e)
			{
				System.out.println("Enter a valid input");
			}
			if (choice == 1) {
				System.out.println("Enter what you want to add: ");
				Comparable a = sc.next();
				heap.add(a);
				System.out.println(heap);
			}
			if (choice == 2) {
				heap.remove();
				System.out.println(heap);
			}
			if (choice == 3) {
				break;
			}	
		}
	}

	public P2AdityaTikhe_HeapPriorityQueue_shell() {
		numItems = 0;
		items = (E[]) new Object[DEFAULT_CAPACITY];
	}

	public P2AdityaTikhe_HeapPriorityQueue_shell(int initialCapacity) {
		numItems = 0;
		items = (E[]) new Comparable[initialCapacity];
	}

	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		else
			return false;

	}

	public E peek() {
		if (!isEmpty())
			return items[1];
		else
			return null;
	}

	public E remove() {
		if (isEmpty())
			return null;
		E removedVal = peek();
		items[1] = items[numItems]; // moves last to first
		items[numItems] = null;
		numItems--;
		reheapDown();
		return removedVal;
	}

	public boolean add(E obj) {
		if (numItems >= items.length - 1)
			doubleCapacity();
		numItems++;
		int insertIndex = numItems;
		// ^ inserts into last spot.

		if (numItems == 1) // if nothing there
		{
			items[insertIndex] = obj;
			return true;
		}

		items[insertIndex] = obj;

		reheapUp();
		return true;
	}

	private void reheapDown() {
		int index = 1;
		int small = 1;
		while (index * 2 <= numItems) {
			small = index * 2;

			if ((index * 2 + 1) <= numItems && items[index * 2].compareTo(items[index * 2 + 1]) > 0) // if right is within bounds and less than left
				small = index * 2 + 1;

			if (items[index].compareTo(items[small]) > 0) {
				swap(index, small);
			} else
				break;
			index = small;
		}

	}

	private void reheapUp() {
		int predIndex = numItems / 2;
		if (items[numItems].compareTo(items[predIndex]) > 0) // if already in right spot
			return;
		else {
			// System.out.println("wrong spot");
			int index = numItems;
			while (items[index].compareTo(items[predIndex]) < 0) {
				swap(index, predIndex);
				index = index / 2;
				predIndex = predIndex / 2;
				if (predIndex == 0)
					return;
				if (predIndex == 1 && ((items[index].compareTo(items[predIndex]) < 0)))
					swap(index, predIndex);

			}
		}
	}

	public void swap(int first, int second) {
		E temp = items[first];
		items[first] = items[second];
		items[second] = temp;
	}

	private void doubleCapacity() {
		E[] temp = (E[]) new Comparable[items.length * 2];
		for (int i = 0; i < items.length; i++) {
			temp[i] = items[i];
		}
		items = temp;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < items.length; i++) {
			if (items[i] != null)
				s += " " + items[i];
		}
		return s;
	}

} // HeapPriorityQueue_shell
