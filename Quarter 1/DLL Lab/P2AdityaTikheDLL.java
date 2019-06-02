/***********************************************************************************************************************************************
 * Name: Aditya Tikhe               
 * Period: 2
 * Name of the Lab: DLL LAB
 * Purpose of the Program: The purpose of this program is to add, delete, retrieve, and manipulate DLLs using various
                           methods on a list of food. 
  * Due Date: 10/4/18
 * Date Submitted: 10/3/18
 * What I learned: 
 * 1. I learned how to use DLLs and how to work with both pointers to create various methods.
 * 2. I also practiced good coding through putting pre and post conditions, and comments to show my logic.
 * ... 
 * How I feel about this lab: I like this lab a lot because it allowed me to practice practical applications
                              and methods using DLLs.
 * What I wonder: I wonder how what is the point of using SLL when one can always use DLLs.
 * Student(s) who helped me (to what extent):  N/A
 * Student(s) whom I helped (to what extent):  N/A
 *************************************************************************************************************************************************/

public class P2AdityaTikheDLL <E>
{
   // private static nested class
   private class DLNode <E>
   {
      private E value;
      private DLNode prev;
      private DLNode next;
      public DLNode(E arg, DLNode <E> p, DLNode <E> n)
      {
         value=arg;
         prev=p;
         next=n;
      }
      public DLNode()
      {
         value=null;
         next=this;
         prev=this;
      }
      public void setValue(E arg)
      {
         value=arg;
      }
      public void setNext(DLNode <E> arg)
      {
         next=arg;
      }
      public void setPrev(DLNode <E> arg)
      {
         prev=arg;
      }
      public DLNode <E> getNext()
      {
         return next;
      }
      public DLNode <E> getPrev()
      {
         return prev;
      }
      public E getValue()
      {
         return value;
      }
   }  // end of DLNode
   
   //*********************************************************************************************  DLL class





   private int size;
   private DLNode <E> head = new DLNode <E> (); //dummy node--very useful--simplifies the code
   
   
   //pre: size is initialized
   //post: returns size of DLL
   public int size()
   {
      return size;
   }
   
   
   /* appends obj to end of list; increases size;
     @return true  */
   //pre: DLL exists
   //post: returns true if adds obj to end of list and increase size
   public boolean add(E obj)
   {
      if(size ==0)
      {
         DLNode <E> temp = new DLNode<E>(obj, head, head);
         head.setNext(temp);
         head.setPrev(temp);
         size++;
         return true;
      } 
      else
      {
         DLNode <E> temp = head;
         for(int i = 0; i <= size; i++)
         {
            temp = temp.getNext();
         }  
         
         DLNode <E> temp1 = new DLNode (obj, temp.getPrev(), temp);
         temp.getPrev().setNext(temp1);
         temp.setPrev(temp1);
      
         size++;  
         return true;
      }
   }
   
   
   /* inserts obj at position index.  increments size. 
   	*/
   //pre: DLL exists and index isn't out of bounds
   //post: insert obj at position index and increment size
   public void add(int index, E obj)                                          
   {
   
      if(index > size || index < 0) //checks if its out of bounds
         return;
      
      DLNode <E> temp = head;
      for(int i = 0; i < index; i ++)
      {
         temp = temp.getNext();
      }
      temp = temp.getPrev(); //go back one
      
      DLNode<E> value = temp.getNext(); 
      DLNode<E> temp1 = new DLNode<E>(obj, temp, value); //node to add
      temp.setNext(temp1); 
      value.setPrev(temp1); //gets next node to set prev
      
      
      size++;
   }
   
   
   /* return obj at position index.  
   	*/
   //pre: DLL exists
   //post: returns obj at position index
   public E get(int index)
   {
      if(index > size || index < 0) //checks if its out of bounds
         return null;
      DLNode<E> temp = head;
      for(int i = 0; i <= index; i++)
      {
         temp = temp.getNext();
      }
      temp = temp.getPrev(); //back one
      return temp.getValue();
   }
   
   
   /* replaces obj at position index.  
   	*/
   //pre: DLL Exists
   //post: sets the obj at the given index to the new obj 
   public void set(int index, E obj)
   {
      if(index > size || index < 0) //checks if its out of bounds
         return;
      DLNode <E> temp = head;
      for(int i = 0; i <= index; i++)
      {
         temp = temp.getNext();
      }
      temp = temp.getPrev(); //back one
      temp.setValue(obj);
   }
   
   
   /*  removes the node from position index.  decrements size.
     @return the object at position index.
    */
   //pre: DLL exists
   //post: return the object at position index and decrement size;
   public E remove(int index)
   {
      if(index > size || index < 0) //checks if its out of bounds
         return null;
         
      DLNode <E> temp = head;
      for(int i = 0; i < index; i ++)
      {
         temp = temp.getNext();
      }
      E v = temp.getValue();
      
      temp.getPrev().setNext(temp.getNext()); 
      temp.getNext().setPrev(temp.getPrev());
      //sets pointers to patch over removed node
      
      size--;
      return  v;
   }
   
   
   /* inserts obj at front of list; increases size;
     */
  //pre: DLL exists
  //post: sets obj to be the first node and increases size
   public void addFirst(E obj)
   {
      size++;
      DLNode <E> temp = new DLNode <E> (obj, head , head.getNext());
      head.getNext().setPrev(temp);
      head.setNext(temp);
   }
   
   
   /* appends obj to end of list; increases size;
       */
   //pre: DLL exists
   //post: appends obj to end of the list and increase size
   public void addLast(E obj)
   {
      if(size ==0)
      {
         DLNode <E> temp = new DLNode<E>(obj, head, head);
         head.setNext(temp);
         head.setPrev(temp);
         size++;
         
      } 
      else
      {
         DLNode <E> temp = head;
         for(int i = 0; i <= size; i++)
         {
            temp = temp.getNext();
         }  
         
         DLNode <E> temp1 = new DLNode (obj, temp.getPrev(), temp);
         temp.getPrev().setNext(temp1);
         temp.setPrev(temp1);
      
         size++;  
         
      }
   }
   
   //pre: DLL exists
   //post: returns the first value
   public E getFirst()
   {
      E v= head.getNext().getValue();
      return v;
   }
   
   
   //pre: DLL exists
   //post: returns the last value
   public E getLast()
   {
      DLNode <E> temp = head;
      for(int i = 0; i <= size; i++)
      {
         temp = temp.getNext();
      }  
      temp = temp.getPrev();
      
      E v = temp.getValue();    
      return v;
   }
   
   
   //pre: DLL exists
   //post: removes first node and decrements the size of the list. returns the first removed node
   public E removeFirst()
   {
      E v = head.getNext().getValue();
      head = head.getNext(); //sets next
      head.setPrev(null); //sets previous to null
      size--;
      return v;
   }
   
   
   //pre: DLL exists
   //post: removes last node and decrements the size of the lsit and return the last removed node
   public E removeLast()
   {
      E v;
      DLNode <E> temp = head;
      for(int i = 0; i <= size; i++)
      {
         temp = temp.getNext();
      }  
      temp = temp.getPrev(); //gets last
      v = temp.getValue(); //gets last value to return 
      
      temp.setValue(null);
      
      size--;
      return v;
   }
   
   
   //toString
   public String toString()
   {
      String list = "";
      DLNode<E> temp = head.getNext();
      for (int i = 0; i <= size; i++)
      {
         if (temp.getValue() != null) 
         {
            list += temp.getValue() + " ";
            temp = temp.getNext();
         }
      }
      return list; 
   }





   public static void main(String args[])
   {
      P2AdityaTikheDLL <String> list = new P2AdityaTikheDLL <String> ();	
   
      list.addLast("Apple");
      list.addLast("Banana");
      list.addLast("Cucumber");
      list.add("Dumpling");
      list.add("Escargot");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      Object obj = list.remove(3);
      System.out.println(list);
      System.out.println("Size: " +list.size());
      System.out.println("Removed "+ obj);
      System.out.print("Add at 3:   ");
      list.add(3,"Cheese");
      System.out.println(list);
      System.out.println("Get values at 1 and first: " + list.get(1)+" and " + list.getFirst());
      System.out.println("No change: " +list);
      System.out.println( list.removeFirst() + " is now removed!"); 
      System.out.println(list);
      System.out.print("Add first:  ");
      list.addFirst("Anchovie");
      System.out.println(list);
      System.out.println("Size: " + list.size());
      System.out.print("Set the second:  ");
      list.set(2, "Bread");
      System.out.println(list);
      
      
      System.out.println("\n\n\n\nbelow is for test purposes to show other methods work and program doesn't crash");
      System.out.println(list);
      System.out.println("last:  " + list.getLast());
      list.removeLast();
      System.out.println("\nremove last");
      System.out.println(list);
      list.removeLast();
      System.out.println("\nremove last");
      System.out.println(list);
      list.removeLast();
      System.out.println("\nremove last");
      System.out.println(list);
      list.removeLast();
      System.out.println("\nremove last");
      System.out.println(list);
      list.removeLast();
      System.out.println("\nremove last");
      System.out.println(list);
      list.removeLast();
      System.out.println("\nremove last");
      System.out.println(list);
      
   }
}


