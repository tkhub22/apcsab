  /*****************************************************************************************************************
NAME:   Aditya Tikhe    
PERIOD:  2
DUE DATE: ??

PURPOSE:  The purpose of this lab is to write the method to delete a node from a BST, and make rearrange 
          the rest of the tree accordingly.

WHAT I LEARNED:   
               - I learned how to use recursion better. 
               - Through guess and check, I learned how to make the delete method for the BST
               - Understand what deleting various nodes in the tree does to the rest of the tree.

HOW I FEEL ABOUT THIS LAB: This lab was very similar to the previous one in editing a BST. I actually enjoyed
                           this lab because it wasn't too hard, and fun to see the visualization of the tree
                           being manipulated.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITES && what kind of 
         help did you get from the source?): I did not use any online sources because the algorithm was given in 
                                             class.

****************************************************************************************************************/
import java.util.Scanner;
/****************************************************************
Practice with a Binary Search Tree. Uses TreeNode.
Step 1: Prompt the user for an input string.  
Step 2: Build a Binary Search Tree using Comparables.  
Step 3: Display it as a sideways tree (take the code from TreeLab).  
Step 4: Prompt the user for a target and delete that node, if it exists. 
*****************************************************************/
public class P2_AdityaTikhe_BinarySearchTreeDelete_shell
{
   public static void main(String[] args)
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("Input string: ");
      String s = sc.nextLine();   // Use	ECSBPWANR
   	         				   							    
      TreeNode t = null;
      for(int x = 0; x<s.length(); x++)
         t = insert(t, s.substring(x, x+1));
      display(t, 0);
   
      while (true)
      {
         System.out.print("Delete? \n");
         String target = sc.next();
         if( contains( t, target ) )
         {
            t = delete( t, target );
            System.out.println("\n" + target+" deleted.\n");
         }
         else
            System.out.println("\n" + target+" not found\n");
         display(t, 0);   
      }     
   }
   
	/**************************
	Recursive algorithm to build a BST:  if the node is null, insert the 
	new node.  Else, if the item is less, set the left node and recur to 
	the left.  Else, if the item is greater, set the right node and recur 
	to the right.   
	*****************************/
   public static TreeNode insert(TreeNode t, String s)
   {  	
      if(t==null)
         return new TreeNode(s);
      if(s.compareTo(t.getValue()+"")<0)
         t.setLeft(insert(t.getLeft(), s));
      else
         t.setRight(insert(t.getRight(), s));
      return t;
   }
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
      display(t.getLeft(), level + 1); //recurse left
   }
   
   public static boolean contains( TreeNode current, String target )
   {
      while(current !=null)
      {
         int compare = target.compareTo((String)current.getValue());
         if( compare == 0 )
            return true;
         else if(compare<0)
            current = current.getLeft();
         else if(compare>0)
            current = current.getRight();
      }
      return false;
   }
   
   //pre: takes a string target
   //post: deletes the node and adjusts the tree accordingly so its still a BST
   public static TreeNode delete(TreeNode t, String target)
   {
      
      if(t == null)
         return null;
         
      else if(target.compareTo((String)t.getValue()) < 0)
         t.setLeft(delete(t.getLeft(), target));
      else if(target.compareTo((String)t.getValue()) > 0)
         t.setRight(delete(t.getRight(), target));
      else
         {
            //t.getValue() == v
            //ndoe thats leaf or one child
            if(t.getLeft() == null)
               return t.getRight();
            else if (t.getRight() == null)
               return t.getLeft();
            
            
            //if node has 2 children
            t.setValue(min(t.getRight()));
            
            //recursive call 
            t.setRight(delete(t.getRight(), (String)t.getValue()));
         }
      return t;
      
   } // end of delete
   
   
      //pre: none
   //post: returns the value of the least value in the BST
   public static Object min(TreeNode t)
   {
      if(t==null)
         return null;
      if(t.getLeft() ==  null)
         return t.getValue();
      else
         return min(t.getLeft());
         
   }
   
}  // end of class



/*  Sample Runs

 Input string: ECSBPWANR
 		W
 	S
 			R
 		P
 			N
 E
 	C
 		B
 			A
 Delete? A
 
 A deleted.
 		W
 	S
 			R
 		P
 			N
 E
 	C
 		B
 Delete? E
 
 E deleted.
 		W
 	S
 			R
 		P
 			N
 C
 	B
 Delete? S
 
 S deleted.
 		W
 	R
 		P
 			N
 C
 	B
 Delete? N
 
 N deleted.
 		W
 	R
 		P
 C
 	B
 Delete? 


*/