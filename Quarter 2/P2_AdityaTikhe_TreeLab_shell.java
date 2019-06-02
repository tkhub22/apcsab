/**
* Name: Aditya Tikhe                  
* Period: 2
* 
* Purpose of the Program: The purpose of this program is to interact with a binary tree through various 
                           traversions. Also, to aquire basic information about the tree such as the 
                           number of nodes. 
* Date Submitted: 1/21
* 
* What I learned:  I learned how to work with the TreeNode data structure. Also, I understood recursion more
                  which was always a sore subject for me. 
* 
* How I feel about this lab: 
                  - I liked this lab because it let me apply what concepts I have learned in class. I understood
                     the theory of how using a BT worked, and after doing this lab I understood how you would write 
                     actual code with it. For example the traversing methods verified the pre, post, and in traversions
                     we did by hand.
                     
                  - For the methods in which it asked to count the leaves or grandparents, it really made me think for a 
                     while. I honestly went through so much paper, trying to understand how the logic would work. This eventually
                     lead to some guessing and random recursive calls to eventually get to the correct output. (I understood how 
                     to do it after though)
                     
                  - I started this lab the day you made it available on BB, so I immediately looked at the shell. This gave me alot 
                     of time to think about the logic for the lab. If I was ever bored, I found myself thinking about this lab.
                     
                  - For the level order traversal, I thought about it logically, looped through each level and printed each layer 
                  from left to right. Instead of using a queue.
                              
* 	
* What I wonder: 
            - At times I got frustrated and didn't understand what was happening, I thought about, "what is even the point of BT
               anyway?" I understand it is a data structure and that it can represent data. But I feel like there aren't much real 
               life applications to using this data structure. So I guess my question would be, "What is the optimal and practical 
               case to use a BT?"

* Credits: 
            - Online for some logic.

* Students whom I helped (to what extent): N/A
*/


import java.util.*;                     //for the queue interface
public class P2_AdityaTikhe_TreeLab_shell       
{
   public static void main(String[] args)
   {
      String s = "XCOMPUTERSCIENCE";
   		
      TreeNode root = new TreeNode("" + s.charAt(1), null, null);
         
       // The root node has index 1
       // The second level nodes' indices: 2, 3
       // The third level nodes' indices: 4,5,6,7
       // Idea: based on the index of the node, log (pos) of base 2 calculates the level of the
       //          node: root (index 1) on level 1. Node with index 2 or 3 is on level 2
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, (int)(1 + Math.log(pos) / Math.log(2)));
      
      // NOTE: The following 3 lines are supposed to further show you how insert works. You
      //             uncomment them and see how the tree looks like with these 3 additional nodes
      insert(root, "A", 17, 5); 
      insert(root, "B", 18, 5); 
      insert(root, "C", 37, 6); //B's right child
      
      // display the tree sideway; see the sample output at the end of this file
      display(root, 0);
      
      System.out.print("\nPreorder: ");
      preorderTraverse(root);
      System.out.print("\nInorder: ");
      inorderTraverse(root);
      System.out.print("\nPostorder: ");
      postorderTraverse(root);
      
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Grandparents = " + countGrandParentNodes(root));  // count the number grandparent nodes
      System.out.println("Only childs = " + countSingleChildNodes(root));	   // count the number of nodes that has only 1 child
         
      System.out.println("\nDepth = " + depth(root));                    
      System.out.println("Height = " + height(root));
   
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
      
      System.out.println("\nBy Level: ");
      displayLevelOrder(root);     // level by level display of the nodes (starts from left to right for nodes on the same level)
      
   } // end of main
   
   
   // insert a new node in the tree based on the node's level
   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
         //  then 1 << 4 will insert four 0-bits at the right of 1 (binary representation of 1 is 1. 
         // 1 << 4 results in 10000 (in binary)
         if((pos & (1 << k)) == 0) 
            p = p.getLeft();      // What does this do? Answer this question first.  What does '&' do? Google it!!!!
         else                        //  We did not learn this in AP CS A!  :
            p = p.getRight();    // What does this do? Answer this question first.
            
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   } // end of insert
      
      
   /*****************************************************************************************************   
     postcondition: display the tree sideway   
   *****************************************************************************************************/   
   public static void display(TreeNode t, int level)
   {
      if(t == null)
         return;
      display(t.getRight(), level + 1); //recurse right
         
      for(int k = 0; k < level; k++)
         System.out.print("\t");
      System.out.println(t.getValue());
         
      display(t.getLeft(), level + 1); //recurse left
   }  // end of display
      
   //pre: given root node t
   //post: print the tree from root t in pre order traverse
   public static void preorderTraverse(TreeNode t)
   {
      System.out.print(t.getValue() + " ");
      if(t.getLeft() != null)
         preorderTraverse(t.getLeft());
      if(t.getRight() != null)
         preorderTraverse(t.getRight());
   }
   
   
   //pre: given root node t
   //post: print the tree from root t in in order traverse
   public static void inorderTraverse(TreeNode t)
   {
      if(t.getLeft()!=null)
         inorderTraverse(t.getLeft());
      System.out.print(t.getValue() + " ");
      if(t.getRight()!=null)
         inorderTraverse(t.getRight());
   }
   
   //pre: given root node t
   //post: print the tree from root t in post order traverse
   public static void postorderTraverse(TreeNode t)
   {
      if(t.getLeft()!= null)
         postorderTraverse(t.getLeft());
      if(t.getRight()!=null)
         postorderTraverse(t.getRight());
      System.out.print(t.getValue() + " ");
   
   }
   
   //pre: given root node t
   //post: returns the number of nodes in the BT
   public static int countNodes(TreeNode t)
   {
      if(t!=null)
         return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
      else
         return 0;
   }
   
   //pre: given root node t
   //post: returns the number of leaves in the BT
   public static int countLeaves(TreeNode t)
   {
      if(t == null)
         return 0;
      else if(t.getRight() == null && t.getLeft() == null)
         return 1;
      else
         return countLeaves(t.getRight()) + countLeaves(t.getLeft());
   }
   
   //pre: given root node t
   //post: returns the number of grandparent nodes in the BT
   public static int countGrandParentNodes(TreeNode t) //like depth but extra condition to check if grandparent
   {
      if(t == null)
         return 0;
      if(t.getLeft() == null && t.getRight() == null)
         return 0;
      else
         {
            int left = countGrandParentNodes(t.getLeft());
            int right = countGrandParentNodes(t.getRight());
            
            if(left > right)
               return (left + 1);
            else 
               return (right + 1);
         }
      
   }
   
   //pre: given root node t
   //post: returns the number of single child nodes in the BT
   public static int countSingleChildNodes(TreeNode t)
   {
      int sum = 0;
      if(t== null)
         return 0;
      else if(t.getLeft() != null && t.getRight() != null)
            sum += 1+ countSingleChildNodes(t.getRight());
      
      else if(t.getLeft() != null && t.getRight() == null)
            sum += 1+ countSingleChildNodes(t.getLeft());
      return sum;
   }
   
   //pre: given root node t
   //post: returns the depth of the BT
   public static int depth(TreeNode t) 
   {
      if(t == null)
         return -1; //return 0
      else
      {
         int left = depth(t.getLeft());
         int right = depth(t.getRight());
         
         if(left>right)
            return (left + 1);
         else 
            return (right +  1);
      }
   }
   
   //pre: given root node t
   //post: returns the height of the BT   
   public static int height(TreeNode t)
   {
      return depth(t);
   }
      
   
   //pre: given root node t
   //post: returns the object with the smallest value from the BT
   public static Object min(TreeNode t)
   {
      if(t == null)
         return null;
      else{
         String v = (String)t.getValue();
         String L = "z";
         String R = "z";
         
         if(t.getLeft() != null)
            L = (String)min(t.getLeft());
         if(t.getRight() != null)
            R = (String)min(t.getRight());
         if(L.compareTo(v) < 0)
            v = L;
         if(R.compareTo(v) < 0)
            v = R;
         return v;
      }  
   }
   
   //pre: given root node t
   //post: returns the object with the biggest value from the BT   
   public static Object max(TreeNode t) //review
   {
      if(t== null)
         return null;
      else {
         String v = (String)t.getValue();
         String L = "A";
         String R = "A";
         if(t.getLeft() != null)
            L = (String) max(t.getLeft());
         if(t.getRight() != null)
            R = (String) max(t.getRight());
         if(L.compareTo(v) > 0)
            v = L;
         if (R.compareTo(v) > 0)
            v = R;
         return v;
      }
   }
   
   /*****************************************************************************************************
        This method is not recursive.  
        Hint: Use a local queue to store the children of the current node.
   *****************************************************************************************************/
   
   //pre: given root node t
   //post: prints the level order of the BT
   public static void displayLevelOrder(TreeNode t)
   {
      int height = height(t) + 1;
      for(int i = 0; i < height; i++)
         printLevel(t,i);
   }
   
   //pre: given root node t and a valid level
   //post: prints the nodes of the given level
   //helper recursive method 
   public static void printLevel (TreeNode t, int level)
   {
      if(t == null) 
         return;
      else if (level == 1)
         System.out.print(t.getValue() );
      else{
         printLevel(t.getLeft(), level-1);
         printLevel(t.getRight(), level-1);
      } 
   }
}  // end of TreeLab_shell


   /***************************************************
	  
      ----jGRASP exec: java Lab01
    
    			E
    		E
    			C
    	M
    			N
    		T
    			E
    C
    			I
    		U
    			C
    	O
    			S
    					C
    				B
    		P
    				A
    			R
    
    Preorder: C O P R A S B C U C I M T E N E C E 
    Inorder: R A P B C S O C U I C E T N M C E E 
    Postorder: A R C B S P C I U O E N T C E E M C 
    
    Nodes = 18
    Leaves = 8
    Grandparents = 5
    Only childs = 3
    
    Depth = 5
    Height = 5
    Min = A
    Max = U
    
    By Level: 
    COMPUTERSCIENCEABC
   
    *******************************************************/
	  /* TreeNode class for the AP Exams */

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
