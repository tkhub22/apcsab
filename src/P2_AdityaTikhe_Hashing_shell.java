/*
 * Name: Aditya Tikhe P:2
 * Date: 3/12/19
 * 
 * What I learned:  
   a. How HashMaps work
   b. Different collisions and how to take care of them (linear, quadratic, chaining)
   c. how to search for values in a hash map.
   ...
 * How I feel about this lab:  This lab showed me how hashing, hash code, and hash maps work. I was very confused about 
 * 								quadratic probing before I did this lab, so by doing this lab I was forced to understand
 * 								how it worked. 
 * 							   Also, this lab and its probing/searching methods were pretty easy to understand and work out, but 
 * 								creating the constructor and toString was sort of tricky. Furthermore, I struggle with recursion
 * 								so doing the linear and quadratic probing methods were guess and check for me. 
 * 						
 * What I wonder: Is there a better way to search for a value after using linear or quadratic probing?
 * 					Also, is there anyway to print the values in the linked list when using chaining. 
 * 						^ attempted to figure out how to delete nodes from chaining but got confused 
 * 					
 * 
 * Credits: N/A
 */

 /*****************************************************************************  
 Assignment:  A simple hashing program is given below. Compile 
 and run.  Notice that collisions occur.  You are to implement 
 three different collision schemes, namely, linear probing,quadratic 
 probing and chaining (use the LinkedList class).
 *****************************************************************************/
import java.util.*;

public class P2_AdityaTikhe_Hashing_shell
{
	
   public static void main(String[] args){
      
      Scanner keyboard = new Scanner(System.in);  
      System.out.print("Enter the size of the hash table (enter 20):  ");
      int arrayLength = keyboard.nextInt();       // Use 20
      keyboard.nextLine();
      
      System.out.println("Choose a structure. 1 for linear probing, 2 for quadratic probing and 3 for chaining:");
      String collisionDealing = keyboard.nextLine();
      int useProbing = Integer.parseInt(collisionDealing); //come here for probing techniques
      //boolean useProbing = true;
      
      
      // Create a table of Objects or a table of raw linked list using ListNode
      HashTable table = new HashTable(arrayLength, useProbing);
      System.out.println("The hash table: " + table);
      
      System.out.print("\nEnter the number of items(enter 15):  ");
      int numItems = keyboard.nextInt();        
      keyboard.nextLine();
      
      System.out.print("\nThe Load Factor is " + (double)numItems/arrayLength);
      System.out.println();
      
      if(table.LoadFactor((double)numItems/arrayLength))
      {
         System.out.println("The load factor for a linear probe or quadratic probe cannot be greater than 1. Try again.");
         System.exit(0);
      }
      
      //
      System.out.println ("Start entering info into the hash table\n");
      System.out.println ("Enter " + numItems + " values.");
      
      // NOTE: In this lab, we don't have key-value pair. We just 
      // store an int to illustrate how hashing works
      
      // Insert objects in the hash table
      for(int i = 0; i < numItems; i++)
         table.add(keyboard.nextLine());
         
      System.out.println();
      System.out.print("Start searching ==> search for? ");
      String key = keyboard.nextLine();
      
      if(table.contains(key))
         System.out.println(key + " found");
      else
         System.out.println(key + " NOT found");
      
      
      System.out.println(table);
   }  // main
   
}  // HashCode
   
   
class HashTable
{
	
   private int size;             // size of the hash table
   private int useProbing; 
   private Object[] table;
   
   private static int LINEAR_PROBE = 1;
   private static int QUADRATIC_PROBE = 2;
   private static int CHAINING = 3;	
   
   // pre-condition: when called puts appropriate parameters
   // post-condition: initializes the array to either a linked list or an array of Objects 
   public HashTable(int numSlots, int useProbing)
   {
	   size = numSlots;
	   this.useProbing = useProbing;
	   if(useProbing == LINEAR_PROBE || useProbing == QUADRATIC_PROBE)
		   table = new Object [size];
	   else
		   table = new ListNode [size];
   }//constructor
	
   
   // pre-condition:
   // post-condition: returns true if the load capacity needs needs to be increased
   public boolean LoadFactor(double loadfactor)
   {
      if((useProbing==LINEAR_PROBE || useProbing == QUADRATIC_PROBE)&&(loadfactor>1))
         return true;
      return false;
   }
   
   // pre-condition:
   // post-condition: adds obj to the specific type of array based on the probing sequence defined.
   public void add(Object obj)
   {
      int code = Math.abs(obj.hashCode());
      System.out.println("Hash Code: " + code);
      int index = code % size;
      // has an empty slot
      if(table[index] == null) //if no collision here
      {
    	  if(useProbing == LINEAR_PROBE || useProbing == QUADRATIC_PROBE) //for linear/quadratic
    		  table[index] = obj;
    	 else
    	  table[index] = new ListNode(obj, null);
      }
      // start probing sequence
      else
      {
         if(useProbing==LINEAR_PROBE)
         {   /*Linear Probing*/
        	 
            // call linearProbe() to find index of the next available space
            // put the object in the table using the index
        	 table[linearProbe(index)] = obj;
        	 
            // add the object at location "index"
         }   
         
         
         else if(useProbing== QUADRATIC_PROBE)
         {
            // call quadraticProbe() to find index of the next available space
            // store the object in the table using the index         
        	 table[quadraticProbe(index, 1)] = obj;
         }
         else
         {    /*Chaining*/
              // call the chaining method
        	 table[index] = chaining(index, obj);
         }
      }
      
   }//add
   
   
   // post: returns true if obj can be found in the table
   public boolean contains(Object obj)
   {
   	
      int code = Math.abs(obj.hashCode());  // Question: Which hashCode function are we using here?
                                            //           The one in the Integer class? How does it work?
      int index = code % size;
   	
      if(useProbing==LINEAR_PROBE) //loop through the table and echeck if every value is the same
      {
    	  for(int i = 0; i < table.length; i ++)
    	  {
    		  if(table[i] != null && table[i].equals(obj))
    			  return true;
    	  }
    	  return false;
      
      }
      else if(useProbing== QUADRATIC_PROBE)
      {
    	  for(int i = 0; i < table.length; i ++)
    	  {
    		  if(table[i] != null && table[i].equals(obj))
    			  return true;
    	  }
    	  return false;
      }
      else  // chaining
      {
    	  ListNode temp = (ListNode) table[index]; //checks the index and checks the linked list within it
    	  while(temp != null)
    	  {
    		  if(temp.getValue().equals(obj))
				  return true;
    		  temp = temp.getNext();
    	  }
    	  return false;
      }
   }//contains
   
 /*  implement collision resolution methods one at a time */
 /*  implement this method recursively */
   //post: returns index through linear probing
   public int linearProbe (int index)
   {
	   if(index < table.length  && table[index] == null)
		   return index;
	   else if (index == table.length-1) // to wrap around if it is the last possible index
		   return linearProbe(0);
	   else
		   return linearProbe(index +1);
   }//linearProbe
   
   /*  implement this method recursively */
   //returns index using quadratic probing
   public int quadraticProbe(int index, int n) //fix array out of bounds exception for adding same value bunch of times
   {
	   if(index < table.length  && table[index] == null) //move this back down to third if it doesnt work
		   return index;
	   else if(index == 0)//when its 0
		   return quadraticProbe(index + (int)Math.pow(n,n), n+1);
	   else if(index > table.length-1) //when quadratic probing gets out of bounds and needs to loop around 
	   {
		   index = index % (table.length -1);
		   return quadraticProbe(index, n);
	   }
	    
	   else if(index == table.length-1)
		   return quadraticProbe(0, n);
	   else
		   return quadraticProbe(index + (int)Math.pow(n,n), n+1);
   } // quadraticProbe

   // Note: each table element is a singly linked list of Objects
   //post; adds object to appropriate index through chaining.
   public Object chaining(int index, Object obj) //not recursive.
   {
	   ListNode head;
		if (table[index] == null)
			head = new ListNode(obj, null);
		else 
		{
			head = (ListNode) (table[index]); //typcast
			while (head.getNext() != null) //find next available node
			{
				head = head.getNext();
			}
			head.setNext(new ListNode(obj, null)); 
		}
		return table[index];
   }//chaining
     
   public String toString()
   {
      String result = "{ ";
      if(useProbing== LINEAR_PROBE || useProbing== QUADRATIC_PROBE)
      {
    	  for(int i = 0; i < table.length ; i ++)
    	  {
    		  result +="  " + table[i];
    	  }
      }
      else  // table of linked lists
      {
    	  for (int i = 0; i < table.length; i++) {

				if (table[i] != null)
				{
					result += ((ListNode) table[i]).getValue() + " ";
					//how to get values in linked list in the index
					/*
					ListNode a = ((ListNode) table[i]);
					while(a.getNext() != null)
					{
						result += ">" + a.getNext().getValue();
					}
					*/
				}
				else
					result += "null ";
			}
      }
      result +=" }";
      return result;
   } //toString
   
   
   private static class ListNode <E> 
   {    
      private E value;    
      private ListNode <E>  next; 
      public ListNode  (E  initValue, ListNode <E> initNext)    
      { 
         value = initValue; 
         next = initNext; 
      }  
      public E getValue()  
      { 
         return value; 
      }    
   
      public ListNode <E> getNext() 
      { 
         return next;  
      } 
   
      public void setValue(E theNewValue)
      { 
         value = theNewValue;
      }
   
      public void setNext(ListNode  <E> theNewNext)
      { 
         next = theNewNext; 
      }
   
   }  // end of ListNode
}  // HashTable


