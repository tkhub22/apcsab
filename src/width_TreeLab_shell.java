/**
test
*/
/*
testttt
*/
import java.util.*;                     //for the queue interface
public class width_TreeLab_shell       
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
      
      System.out.println("\nheight: " + height(root));
      System.out.print("\nwidth: " + width(root));
      
      System.out.println("\ndisplay levelorder ");
      displayLevelOrder(root);
      
      System.out.println("\n\nmin : " + min(root));
      
      System.out.println("descednant?: " + isD(root, root.getLeft()));
      
      
      System.out.println("\n\n heap \n\n");
      TreeNode heap = new TreeNode("A",null,null);
      insert(heap, "B", 2, 2);
      insert(heap, "C", 3, 2);
      insert(heap, "D", 4, 3);
      insert(heap, "E", 5, 3);
      insert(heap, "F", 6, 3);
      display(heap,0);
      
      String [] arr = new String [7];
      heapArray(heap, arr, 1);
      
      //System.out.println(heap.getRight().getValue());
      
      for(int i = 1; i < arr.length; i++)
      {
    	  System.out.print(arr[i] + " ");
      }
      
   } // end of main
   
   public static void heapArray(TreeNode t, String [] s, int level )
   {   
	   if(t == null)
		   return;
	   if(t!= null)
		   s[level] = "" + t.getValue();
	   
	   heapArray(t.getLeft(), s, level *2 );
	   heapArray(t.getRight(), s, level* 2 + 1);
   }
   
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
      
      
   public static void preorderTraverse(TreeNode t)
   {
   }
   
   
   
   public static void inorderTraverse(TreeNode t)
   {
        
   }
   
   
   public static void postorderTraverse(TreeNode t)
   {
        
   }
   
   
   public static int countNodes(TreeNode t)
   {
      return -1;
   }
   
   
   public static int countLeaves(TreeNode t)
   {
      return -1;
   }
   
   
   public static int countGrandParentNodes(TreeNode t)
   {
      return -1;
   }
   
   
   public static int countSingleChildNodes(TreeNode t)
   {
      return -1;
   }
   public static int depth(TreeNode t)
   {
      return -1;
   }
      
 
    public static boolean isD (TreeNode first, TreeNode second)
    {
      if(first == null)
         return false;
      if(first.equals(second))
         return true;
      return isD(first.getRight(), second) || isD(first.getLeft(), second);
    }  
   
   
   public static Object min(TreeNode t)
   {
      char L, R;
      char current = ((String)t.getValue()).charAt(0);
      if(t.getLeft()!=null)
      {
         L= min(t.getLeft()).toString().charAt(0);
         if(L < current)
            current = L;
      }
      
      if(t.getRight() !=null)
      {
         R = min(t.getRight()).toString().charAt(0);
         if(R< current)
            current = R;
      }
      return current;
         
   }
   
      
   public static Object max(TreeNode t)
   {
      return null;
   }
   
   /*****************************************************************************************************
        This method is not recursive.  
        Hint: Use a local queue to store the children of the current node.
   *****************************************************************************************************/
   public static void displayLevelOrder(TreeNode t)
   {
      TreeNode current = t;
      String s = "";
      Queue <TreeNode> q = new LinkedList <TreeNode>();
      q.add(current);
      while(!q.isEmpty())
      {
         if(q.peek().getLeft() != null)
            q.add(q.peek().getLeft());
         if(q.peek().getRight() != null)
            q.add(q.peek().getRight());
         s += q.remove().getValue().toString();
      }
      System.out.print(s);
   }
   
   public static int height(TreeNode t)
   {
      if(t== null)
         return 0;
      else{
         int left = height(t.getLeft());
         int right = height(t.getRight());
         
         if(left > right)
            return left +1;
         else
            return right + 1;
      }
   }
   
   //rewrite this method using queue tmrw (2/8)
   public static int width(TreeNode t)
   {
      int max = 0;
      int w;
      int h = height(t)+1;
      for(int i = 0; i < h; i ++)
      {
         w = widthHelper(t,i);
         if(w> max)
            max = w;
      } 
      return max;
   }
   
   public static int widthHelper(TreeNode t, int level)
   {
      if(t == null)
         return 0;
      if(level == 1)
         return 1;
      else if (level > 1)
         return widthHelper(t.getLeft(), level-1) + widthHelper(t.getRight(), level-1);
      return 0;
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
    
    Depth =45
    Height = 4
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