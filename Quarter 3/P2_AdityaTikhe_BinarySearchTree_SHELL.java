/*****************************************************************************************************************
NAME:  Aditya Tikhe    
PERIOD:2
DUE DATE: 1/30
turned in: 1/30

PURPOSE: The purpose of this lab is to create the basic methods of a BST. 

WHAT I LEARNED:  I learned that the BST is like a binary tree with the values organized within the tree.
                  Finding the max and min are easier because they are at either ends of the tree. Another
                  interesting thing I found from working with this lab is that printing from smallest to 
                  largest is like the inorder logic of a BT.   

HOW I FEEL ABOUT THIS LAB: I feel like this lab wasn't too challenging, but also a good introduction to BST. One
                           mehtod I found confusing was the insert method. It took me a while to implement, even
                           though the logic was easy. 
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITE): n/a except for class discussions.

****************************************************************************************************************/
import java.util.Scanner;
	/****************************************************************
	Practice with a Binary Search Tree. Uses TreeNode.
    Prompt the user for an input string.  Build a Binary Search Tree 
	using Comparables.  Display it as a sideways tree (take the code 
	from the Tree Lab).  Prompt the user for a target and search the tree 
	for it.  Display the tree's minimum and maximum values.  Print 
	the data in order from smallest to largest.
	*****************************************************************/
public class P2_AdityaTikhe_BinarySearchTree_SHELL
{
   public static void main(String[] args)
   {
          // your code goes here   
      Scanner console = new Scanner (System.in);
      System.out.println ("Enter a word you want to be converted into a BST");
      String input = console.nextLine();
      TreeNode t = format(input);
      display(t, 0);
      System.out.println("\nInput target: ");
      String target = console.nextLine();
      if(find(t, target) == true)
         System.out.println(target + " found");
      else
         System.out.println(target + " not found");
      System.out.println("Min " + min(t));
      System.out.println("Max " + max(t));
      smallToLarge(t);  
   }
   
   //pre: given a string 
   //post: returns the root node and inserts each character from the string into the BST
   public static TreeNode format(String s)
   {
      TreeNode t = new TreeNode(s.substring(0,1));
      for(int i = 1; i < s.length()-1; i ++)
      {
         insert(t,s.substring(i, i+1));
      }
      insert(t,s.substring(s.length()-1));
      return t;
   }
   
   	/****************************************************************
   	Recursive algorithm to build a BST:  if the node is null, insert the 
   	new node.  Else, if the item is less, set the left node and recur to 
   	the left.  Else, if the item is greater, set the right node and recur 
   	to the right.   
   	*****************************************************************/
      
   //pre: object passed in parameter is a string
   //post: returns the treenode, and inserts a string in the appropriate place
   public static TreeNode insert(TreeNode t, String s)
   { 
      if(t==null){
         t = new TreeNode(s, null, null);
         return t;
      }
      else if(s.compareTo((String)t.getValue()) <= 0){
         t.setLeft((insert(t.getLeft(), s)));
      }
      else {
         t.setRight(insert(t.getRight(), s));
      }
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
   	
     /***************************************************************
      Iterative algorithm:  create a temporary pointer p at the root.  
   	While p is not null, if the p's value equals the target, return true.  
   	If the target is less than the p's value, go left, otherwise go right.   
   	If the target is not found, return false. 
      
   	Find the target. Recursive algorithm:  If the tree is empty, 
   	return false.  If the target is less than the current node 
   	value, return the left subtree.  If the target is greater, return 
   	the right subtree.  Otherwise, return true.   
   . ****************************************************************/    
   
   //pre: x is comparable
   //post: return if Comparable x is in the Tree
   public static boolean find(TreeNode t, Comparable x)
   {
      if(t == null)
         return false;
      if(x.compareTo((String)t.getValue()) < 0)
         return find(t.getLeft(), x);
      if(x.compareTo((String)t.getValue()) > 0)
         return find(t.getRight(), x);
      return true;
   }
   
     /***************************************************************
   	starting at the root, return the min value in the BST.   
   	Use iteration.   Hint:  look at several BSTs. Where are 
   	the min values always located?  
   	***************************************************************/
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
   /*****************************************************************
   	starting at the root, return the max value in the BST.  
   	Use recursion!
    *****************************************************************/
    //pre: none
   //post: returns the value of the max value in the BST
   public static Object max(TreeNode t)
   {
      if(t==null)
         return null;
      if(t.getRight() == null)
         return t.getValue();
      else
         return max(t.getRight());
      
   }
   
   //pre: none
   //post: prints all the values from small to large in a BST
   public static void smallToLarge(TreeNode t) //LCR
   {
      if(t.getLeft()!=null)
         smallToLarge(t.getLeft());
      System.out.print(t.getValue() + " ");
      if(t.getRight()!=null)
         smallToLarge(t.getRight());
   }
}  //end of class






//TreeNode
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}