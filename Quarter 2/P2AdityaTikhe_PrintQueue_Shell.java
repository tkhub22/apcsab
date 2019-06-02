/***********************************************************************
Name: Aditya Tikhe
Period: 2
Date: 12/20/18   
What I Learned: From this lab I learned about the Queue structure and how to manipulate it. Specifically,
                I learned how to work with the important methods, such as add and remove, in the Queue 
                structure. It taught me firsthand how FIFO (First in first out) works. Also, I got a refresher
                on scanners as I forget how to create and manipulate them sometimes. 
        
How I feel about this lab: I feel like this lab is a good introduction to Queue. It gives a good introduction to the 
                           basic methods, while boosting my confidence on working with new material.
            
                      
Credit (person who helped me): N/A
Student(s) whom I helped (to what extent): N/A
************************************************************************/ 

import java.io.*;
import java.util.*;

public class P2AdityaTikhe_PrintQueue_Shell
{
   private static Scanner in;
   private static Queue<String> q;
   private static int jobNumber =100;
   
   public static void main(String[] args) throws Exception
   {
      q = new LinkedList<String>();
      in = new Scanner(System.in);
      String prompt = "Add, Print, Delete, eXit:  ";
      System.out.print(prompt);
      String str = in.next();
      while(!str.equals("X"))
      {
         if(str.equals("A"))
            add();
         else if(str.equals("P"))
            printJob();  
         else if(str.equals("D"))
            delete();
         else
            System.out.println("   invalid command");
         printQueue();
         System.out.print(prompt);
         str = in.next();
      }
      in.close();
   }
   //pre: none
   //post: adds a value into queue.
   public static void add()
   {
      q.add("" + jobNumber);
      jobNumber++;
   }
   
   //pre: none
   //post: prints first value in queue if there is one
   public static void printJob()
   {
      if(q.peek() == null)
         System.out.println("enter a values because queue is empty");
      else
         System.out.println(q.peek());
   }
	
   //pre: none
   //post: deletes specified input by user from queue if there is one		
   public static void delete()
   {
      if(q.size() == 0)
      {
         System.out.println("Queue is empty, can't delete anything");
         return;
      }
      System.out.println("Enter a value want removed");
      String s = in.next();//gets user input on what to delete
      if(q.contains(s))
      {
         q.remove(s);
      }
      else
         System.out.println("your value is not in the queue");
   }
   
   //pre: none
   //post: print contents of queue
   public static void printQueue()
   {
      System.out.println(q);
   }
}
