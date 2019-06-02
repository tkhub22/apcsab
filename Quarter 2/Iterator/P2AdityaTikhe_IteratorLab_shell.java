/*****************************************************************************************************************
NAME: Aditya Tikhe      
PERIOD:  2
DUE DATE: 12/8/18
ASSIGNMENT: Iterator Lab

PURPOSE: The purpose of this lab is to use for each loops, iterators, and list iterators to go through arrays 
         and arraylists of strings and integers.

LEARNED: 
- When to use for each loops and Iterators. 
- Practice each of the methods in the Iterator class.
         
CREDITS: N/A

****************************************************************************************************************/

 // NOTE:  use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class P2AdityaTikhe_IteratorLab_shell
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {-9, 4, 2, 5, -10, 6, -4, 24, 20, -28};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("ArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      System.out.println("Replace negative numbers: " + replaceNeg(numbers));
      System.out.println("Delete zeros: " + deleteZero(numbers));
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
            "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
   // pre: an array of just int values 
	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) //for each loop
   {
      ArrayList <Integer> a = new ArrayList <Integer> ();
      for(int n : rawNumbers)
         a.add(n);
      return a;
   }
   // pre: an array of just Strings  
	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) //for each loop
   {
      ArrayList <String> a = new ArrayList <String> ();
      for(String n : rawWords)
         a.add(n);
      return a;
   }

	// pre: ArrayList a is not empty and contains only Integer objects
	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a) //Iterator
   {
      int c = 0;
      Iterator <Integer> i = a.iterator();
      while(i.hasNext())
      {
         if(i.next() < 0)
            c++;
      }
      
      return c;
   }
	// pre: ArrayList a is not empty and contains only Integer objects
	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a) //Iterator
   {
      double average = 0.0;
      Iterator <Integer> i = a.iterator();
      while(i.hasNext())
      {
         average += i.next();
      }
      average = average/a.size();
      return average;
   }
  	// pre: ArrayList a is not empty and contains only Integer objects
	// post: replaces all negative values with 0 
   public static ArrayList<Integer> replaceNeg(ArrayList<Integer> a) //Listiterator
   {
      ListIterator <Integer> i = a.listIterator();
      while(i.hasNext())
      {
         if(i.next() < 0)
         {
            i.set(0);
         }
      }
      return a;
   }
  	// pre: ArrayList a is not empty and contains only Integer objects
	// post: deletes all zeros in the ArrayList a
   public static ArrayList<Integer> deleteZero(ArrayList<Integer> a) //Iterator
   {
      Iterator <Integer> i = a.iterator();
      while(i.hasNext())
      {
         if(i.next() ==0)
         {
            i.remove();   
         }
      }
      return a; 
   }
   
   // pre: ArrayList a is not empty and contains only String objects
	// post: return ArrayList without duplicate movie titles
	// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList <String> array = new ArrayList <String> ();
      for(String str: a)
      {
         if(!array.contains(str))
            array.add(str);
      }
      return array;
   }

}

