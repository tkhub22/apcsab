  /*****************************************************************************************************************

NAME: Aditya Tikhe     
PERIOD: 2
DUE DATE: 2/13

PURPOSE: To learn about BXT by constructing, evaluating, and traversing through them.

WHAT I LEARNED: Apart from learning how to work with BXT, I also learned how to manipulate tokens.
                Also, I learned how to evaluate and build a BXT.
                  
                     
HOW I FEEL:
            - This lab took me a whole afternoon. This was one of the few times I completed a lab in one day. It 
               really tied everything we learned about BTs and post/in/pre evaluations and traversions. By
               learning about BXT and BST it makes me understand BT better and the purpose of them.
            
CREDITS (BE SPECIFIC: FRIENDS, PEERS, ONLINE WEBSITES, ETC.): N/A

****************************************************************************************************************/
import java.util.*;

 	/***********************************
	Represents a binary expression tree.
	The BXT can build itself from a postorder expression.  It can
	evaluate and print itself. It also prints an inorder string and a preorder string.  
	************************************/
   	
	/*******************
	Driver for a binary expression tree class.
	Input: a postfix string, each token separated by a space.
	**********************/
public class P2_AdityaTikhe_BXT_shell
{
   public static void main(String[] args)
   {
      BXT tree = new BXT();
      Scanner sc = new Scanner(System.in);
      System.out.print("Input postfix string: ");
      String s =  sc.nextLine();   // 14 -5 / ,   3 4 5 + *  ,  2 3 + 5 / 4 5 - *
      
      //test
      
      
      
      tree.buildTree(s);
      
      tree.display();
      
      System.out.print("Infix order:  ");
      tree.inorderTraverse();
      
      System.out.print("\nPrefix order:  ");
      tree.preorderTraverse();
      
      System.out.print("\nEvaluates to " + tree.evaluateTree());
      
   }
}
	

 	/***********************************
	Represents a binary expression tree.
	The BXT can build itself from a postorder expression.  It can
	evaluate and print itself. It also prints an inorder string and a preorder string.  
	************************************/
class BXT
{
   public static final String operands = "+-*/";
   public static final String letters = "abcdefghijklmnopqrstuvwxyz";
   private int count;
   private TreeNode root;
   
   public BXT()
   {
      count = 0;
      root = null;
   }
   public BXT(Object obj)
   {
      count = 1;
      root = new TreeNode(obj, null, null);
   }
      /***********************
   	Builds a BXT from a postfix expression.  Uses a helper stack of TreeNodes.
   	****************************/
      //pre: the input string is a proper post fix expression seperated by spaces
      //post: builds a BXT with the given post string
   public void buildTree(String str)
   {
      if(format(str) == false)
         return;
      
      Stack <TreeNode> stack = new Stack <TreeNode> ();
      StringTokenizer tok = new StringTokenizer(str);
      while(tok.hasMoreTokens())
      {
         String token = tok.nextToken();
         if(!isOperator(token))
         { 
            stack.push(new TreeNode(token, null, null));
         }
         else
         {
            TreeNode one = stack.pop();
            TreeNode two = stack.pop();
            TreeNode inner = new TreeNode(token, two, one);
            stack.push(inner);
         }
      }
      root = stack.peek();
      
         //enter code here
   }
   
   //pre: given an expression makes sure its a valid one (using only numbers and operands)
   //post: returns true if it and false if it isn't
   private boolean format (String x) //checks if invalid input
   {
      for (int k = 0; k < x.length(); k++) 
      {
         for(int inner = 0; inner < letters.length(); inner++)
         {
            if(x.charAt(k) == letters.charAt(inner))
            {
               System.out.println("use only numbers and operands not letters!");
               return false;
            }
         }
      }
      return true;
   
   }
   //pre:
   //post: returns the evaluation of the BXT
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode root)  //recursive
   {
      if(root == null)
         return 0;
      else
      {
         if(isOperator((String)root.getValue())) //the operator will always be root 
         {
            double left = evaluateNode(root.getLeft());
            double right = evaluateNode(root.getRight());
            return computeTerm((String)root.getValue(), right, left);
         }
         
         else 
         {
           // if(!isOperator((String)root.getValue()))
               return Double.parseDouble((String)root.getValue());
         }
      }
      //return 0;
   }
   
   
   private double computeTerm(String s, double a, double b)
   {
          //enter code here
      char ch = s.charAt(0);
      if(ch == '+')
         return a + b;
      if(ch == '-')
         return b-a;
      if(ch == '*')
         return a*b;
      if(ch == '/')
         return b/a;
      return 0;
   }
   private boolean isOperator(String s)
   {
          //enter code here
      if(operands.contains(s))
         return true;
      return false;
   }
     //display() from TreeLab
   public void display()
   {
      display(root, count);
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
	 
     // inorder traverse
   public void inorderTraverse()
   {
      inorderTraverse(root);
   }
   public static void inorderTraverse(TreeNode t)
   {
      if(t.getLeft() != null)
         inorderTraverse(t.getLeft());
      System.out.print(t.getValue()+ "");
      if(t.getRight() != null)
         inorderTraverse(t.getRight());
   }
   	 
     // preorder traverse
   public void preorderTraverse()
   {
      preorderTraverse(root);
   }
   
   public static void preorderTraverse(TreeNode t)
   {
      System.out.print(t.getValue()+ "");
      if(t.getLeft()!=null)
         preorderTraverse(t.getLeft());
      if(t.getRight()!=null)
         preorderTraverse(t.getRight());
   }
}


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