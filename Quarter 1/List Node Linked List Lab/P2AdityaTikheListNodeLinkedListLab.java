/**
* Name: Aditya Tikhe                       
* Period: 2
* Name of the Lab: ListNode Linked List Lab
* Purpose of the Program: The purpose of this program is to press keys and have it correspond with various
                           methods that work with Link Lists such as printing it or reversing the values.
* Due Date: 9/22
* Date Submitted: 9/21
* What I learned: In this lab I learned how to use Linked Lists and the syntax associated with it. Also, this lab
                  allowed me to sharpen my logic on how to approach problems.
* How I feel about this lab: I thoroughly enjoyed this lab because it allowed me to think of creative approaches
                              to each method. (probably my favorite lab from CS A and AB). The lab compiled out 
                              of the box which is a nice conveniance because it allowed me to focus on learning
                              linked lists. 
* What I wonder:  I wonder what more can I do with Linked List, and how I can use ArrayList in conjunction with
                  Linked Lists like in Worksheet 0.
* Credits:  I used the internet to find the syntax on how to manipulate Linked Lists. I came up with the
            logic myself though for every method except for the reverse one. In that method I dervived
            some logic from the a website because I was confused for a whole day on it. Anthony also helped with a
            little bit of technical issues, and understanding of recursion.
* Students whom I helped (to what extent): I did not help any students, except talk about the labs with
                                           my class mates on how to apprach the methods.
* I did both extra methods, reverse and !
*/
import java.util.NoSuchElementException;
import java.util.Scanner;

public class P2AdityaTikheListNodeLinkedListLab
{

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

   public static void main(String [] args)
   {
      Scanner input = new Scanner (System.in);
      ListNode <Integer> h = new ListNode( 5, null);
      h= new ListNode(4, h);
      h= new ListNode(3, h);
      h= new ListNode(2, h);
      h= new ListNode(1, h);
        
      char option ;
      do
      {
         option = menu();
         if( option  == 'a')
         {
            System.out.println("list: ");
            printLinkedList(h);
         }	
         else if(option == 'b')
         {
            System.out.println("The List has atleast two element?");
            System.out.println(hasTwo(h));
         }	
         else if( option  =='c')
         {
            System.out.print("The size of the array is: ");
            System.out.println(size(h));
         }	
         else if( option  == 'd')
         {
            h = removeFirst(h);
            System.out.print("New list is: ");
            printLinkedList(h);
         }
         else  if( option  == 'e')
         {
            System.out.println("Enter number: ");
            int num = input.nextInt();
            add(h, new Integer(num));
            System.out.println("New list is: ");
            printLinkedList(h);
         }
         else if( option  == 'f')
         {
            h = reverseList(h);
            System.out.println("Reverse is: ");
            printLinkedList(h);
         }
         else if( option  == 'g')
         {
            h = rotate(h);
            System.out.println("rotated array is: ");
            printLinkedList(h);
         }
         else if( option  == 'h')
         {
            printLinkedList(h);
            System.out.println("\nmiddle node is: "+middleNode(h).getValue());
         }
         
         else if( option  == 'i')
         {
            h = removeLast(h);
            System.out.print("New list is: ");
            printLinkedList(h);
         }
         else if (option == 'j') {
            System.out.println("Enter number: ");
            int num = input.nextInt();
            h = remove(h, num);
            System.out.println("New List: ");
            printLinkedList(h);
         }
         else if (option == 'k') {
            System.out.println("Split Lists: ");
            split(h);
         }
      
      } while (option != 'z');
   
   }  // end of main
   //pre:
   //post: prints list
   public static void printLinkedList(ListNode <Integer> head)
   {
      ListNode <Integer> temp;
      System.out.println();
      for(temp = head; temp!= null; temp = temp.getNext()) 
      {
         System.out.println(temp.getValue());
      }
   
   }
   //pre:
   //post: return a boolean value that checks if the list has two or more elements
   public static boolean hasTwo(ListNode  <Integer> head)
   {
      int count = 0;
      ListNode <Integer> temp;
      for(temp = head; temp != null; temp = temp.getNext())
      {
         count++;
      }
      if(count >= 2)
         return true;
      else
         return false;
   
   }
   //pre:
   //post: returns an int of the size
   public static int size(ListNode <Integer> head)
   {
      int s = 0;
      ListNode <Integer> temp;
      for(temp = head; temp != null; temp = temp.getNext())
      {
         s++;
      }
      
      return s;
   }
   //pre:
   //post: removes the first node
   public static ListNode <Integer> removeFirst(ListNode <Integer> head)
   {	
      if(head == null)
         return null;
      return head = head.next;
   }
   //pre:
   //post: removes the last node
   public static ListNode <Integer> removeLast(ListNode <Integer>  head)
   {	
      ListNode <Integer> temp1;
      ListNode <Integer> temp2;
      if(size(head) == 1 || size(head) ==0 ) //checks if there is a last node to return
         return null;
     
      for(temp1 = head, temp2 = head.getNext(); temp2 != null; temp1 = temp1.getNext(), temp2 = temp2.getNext()) //loops through using the pointers 
      {
         if(temp2.getNext() == null) //checks if there is a next value
         {
            temp1.setNext(null); //sets it to null so when printing theres nothing.
            break;
         }
      }
      return head;
   }
   //pre: integer exists within list, but still works if it isn't there
   //post: removes an integer from list.
   public static ListNode <Integer> remove(ListNode head, Integer value)
   {	
      ListNode<Integer> temp1;
      ListNode<Integer> temp2; //1 & 2 to iterate through the linkedlist
      ListNode<Integer> temp3; //if the first is value
      
      if (head.getValue() == value) //checks if its the first value
      {
         temp3 = new ListNode (head.getValue(), null);
         head = head.getNext();
         return temp3;
         
      } 
      for (temp1 = head, temp2 = head.getNext(); temp2 != null; temp1 = temp1.getNext(), temp2 = temp2.getNext()) 
      {
         if (temp2.getValue() == value)         
            temp1.setNext(temp2.getNext()); //skips over removed node
      }
      return head;    
   }
   //pre:
   //post: adds a int to the list.
   public static void add(ListNode <Integer> head, Integer value)
   {
      ListNode <Integer> temp1;
      ListNode <Integer> temp2;
      for(temp1=head, temp2 = head.getNext(); temp2 != null; temp1 = temp1.getNext(), temp2 = temp2.getNext())
      { //loops through to get last node 
         if(temp2 == null)
            break;
      }
      
      temp1.setNext(new ListNode <Integer> (value, null)); //attaches new node to last 
   }
   //pre:
   //post: reverses the list order
   public static ListNode <Integer>  reverseList(ListNode <Integer> head)
   {
      ListNode<Integer> temp1 = head;
      ListNode<Integer> temp2;
      ListNode<Integer> temp3 = null;
      
      while(temp1 != null)
      {
         temp2 = temp1.getNext(); //sets to next
         temp1.setNext(temp3); 
         temp3 = temp1; //flips
         temp1 = temp2; //flips   
      }
      
      head = temp3;
      return head;
   }
   //pre:
   //post: puts the first node as the last one
   public static ListNode <Integer> rotate(ListNode <Integer> head)
   {
      if(size(head) == 0)
         return null;
      if(size(head) == 1)
         return head;
         
      int add = head.getValue(); //puts value in a temp
      
      head = head.next; //deletes
      add(head, add);
      return head;
      
   }
   //pre: 
   //post: retrieves the middle node if an odd numbered list, and returns the middle node closest to the end of the list if its an even numbered array
   public static ListNode <Integer> middleNode(ListNode <Integer>head)
   {
      int s = size(head);
      if(s == 0)
         return null;
      if(s == 1)
         return head;
         
      ListNode <Integer> temp = null; 
      if(s % 2 == 0) //checks if array is even number long
      {
         temp = head;
         for (int i = 1; i < (s / 2 + 1); i++)
            temp = temp.getNext();
      }
         
      if(s % 2 == 1)
      {
         temp = head;
         for(int i = 0; i < (s/2); i++) //checks if odd number long 
            temp = temp.getNext();
      }
      return temp;
   }
   
   
   //pre:
   //post: prints the values at the odd indexes of a list and the values at the even indexes of the list

   
   public static <Integer> ListNode split(ListNode <Integer> head){
      
      ListNode <Integer> odds = null;
      ListNode <Integer> evens = null;
      odds = recursiveSplit(head); //odd recursive call
      printLinkedList((ListNode<java.lang.Integer>)odds); //had to type cast because compiler complained that .integer wouldn't be same as lang
      if(head.getNext() != null)
      {
         evens = recursiveSplit(head.next); //even recursive call
         printLinkedList((ListNode<java.lang.Integer>)evens);
      }
      head.next = null;
      return head;
   }
   
   public static <Integer> ListNode recursiveSplit(ListNode <Integer> head){
      if (head.getNext() == null || head.getNext().getNext() == null)
         return new ListNode(head.getValue(), null);
      return new ListNode(head.getValue(), recursiveSplit(head.next.next));
   }
   


   public static char menu()
   {
      Scanner input = new Scanner (System.in);
      System.out.println("\n====> What would you like to do?");
      System.out.println("a) Print list");
      System.out.println("b) Check if list has at least two nodes");
      System.out.println("c) Get size of the list");
      System.out.println("d) Remove first node");
      System.out.println("e) Add a node");
      System.out.println("f) Reverse");
      System.out.println("g) Rotate");
      System.out.println("h) Get middle node");
      System.out.println("i) Remove last node");
      System.out.println("j) Remove a node");
      System.out.println("k) split a list");
      System.out.println("z) Quit?");
      String choice = input.next();
      return choice.charAt(0);   
   }  // end of menu
}


