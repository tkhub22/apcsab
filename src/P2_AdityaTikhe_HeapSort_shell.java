
/*
//Name:   Aditya Tikhe
//Date: 3/3/18
// What I learned: I learned about heap sort
// How I feel about this lab: I like this lab because it is a fundamental sorting algorithm, and by building it, I understand how it works.
 * 								I broke this lab into parts and realized it made it easy to complete and retain the information. 
 * 
 * Credits:
 * 	looked online for visualizations of heap sort and how it works
 *  On the way to the UVA Hackathon, a TJ student helped me make my program more efficient with the heapdown method
*/
import java.text.DecimalFormat;

public class P2_AdityaTikhe_HeapSort_shell {
	public static final int SIZE = 10;
	public static final String pattern = "###.##";

	public static void main(String[] args) {
		// Part 1: Given a heap, sort it. Do this part first.

		double heap1[] = { -1, 99, 80, 85, 17, 30, 84, 2, 16, 1 };
		display(heap1);
		sort(heap1);
		display(heap1);
		
		System.out.println("\npart 2\n\n");

		// Part 2: Generate 10 random numbers, make a heap, sort it.
		 double[] heap = new double[SIZE+1];

		 createRandom(heap);
		 display(heap);
		makeHeap(heap,SIZE);
		display(heap);
		sort(heap);
		display(heap);
		
	}

	// ******* Part 1 ******************************************
	public static void display(double[] array) {
		for (int k = 1; k < array.length; k++)
			System.out.print(array[k] + "  ");
		System.out.println("\n");
	}
	//pre:
	//post: sorts the array by putting the value at index 1 and heaping down
	public static void sort(double[] array) {
		for(int i= array.length-1; i >= 1; i--)
		{
			swap(array, i, 1); //puts at first element and heaps down
			heapDown(array,i-1,1);
		}
	}

	public static void swap(double[] array, int a, int b) {
		double temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}
	//pre: takes 3 param
	//post: heaping down the value of the array at index 1
	public static void heapDown(double[] array, int size, int index) { //different from priority queue heapdown 
		if((size/2) <index || index<1)//check if index out of bounds
			return;
		int left  = 2*index;
		int right = 2*index +1;
		if(right <= size && array[right] > array[left] && array[right] > array[index])
		{
			swap(array, right, index);
			heapDown(array, size, right); //recurse right
		}
		if(array[left] > array[index])
		{
			swap(array, left, index);
			heapDown(array,size,left);
		}
		
	}

	// ****** Part 2 *******************************************
	//pre: array[0] is not taken into account
	//post:	turn into a array based heap
	public static void makeHeap(double[] array, int size) { //makes into heap
		for(int i = size/2 ; i > 1; i--) //backwards heapdown
			heapDown(array,size,i-1);
	}

	// Generate 100 random numbers (between 1 and 100, formatted to 2 decimal
	// places)
	//pre: array[0] is not taken into account
	//post: fill the array with random numbers between 1 and 100 
	public static void createRandom(double[] array) {
		for (int i = 1; i <= SIZE; i++) {
			DecimalFormat decimalFormat = new DecimalFormat(pattern);
			double random = (Math.random() * 100 + 1);
			String format = decimalFormat.format(random);
			array[i] = Double.parseDouble(format);
		}
	}
}