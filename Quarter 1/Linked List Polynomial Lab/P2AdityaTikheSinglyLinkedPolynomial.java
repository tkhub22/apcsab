/**
Name: Aditya Tikhe
Due: Saturday 9/30/18
Date Submitted: 9/30/18
What I learned: I learned alot about type casting, and brushing up working with objects.     
How I feel about this lab: This lab was very complicated, and focused more on polymorphism and other topics 
                           rather than Linked Lists, in my opinion.   
What I wonder:     
Credits (person/websites):  
Students I helped:
**/

import java.lang.Math;

public class P2AdityaTikheSinglyLinkedPolynomial implements Polynomial 
{  


    
   private class ListNode<Term> 
   {  
      private Term value;    
      private ListNode<Term> next; 
   
      public ListNode(Term t, ListNode<Term> n)
      {
         value = t;
         next = n;
      }
      
      public Term getValue()
      {
         return value;
      }
      
      public ListNode<Term> getNext()
      {
         return next;
      }
      
      public void setValue(Term newTerm)
      {
         value = newTerm;
      }
      
      public void setNext(ListNode<Term> n)
      {
         next = n;
      }
   }  //end of ListNode<Term>  
   
   
   
   
   
   private static class Term
   {
      private double exponent;
      private double coeff;
      
      public Term(double c, double e)
      {
         exponent = e;
         coeff = c;
      }
   
      public double getExponent()
      {
         return exponent;
      }
     
      public double getCoeff()
      {
         return coeff;
      }
     
      public void setExponent(double x)
      {
         exponent = x;
      }
     
      public void setCoeff(double x)
      {
         coeff = x;
      }
   
      public boolean equals(Term other)
      {
         return this.coeff == other.getCoeff() && this.exponent == other.getExponent();
      }
   
      public String toString()
      {
         return coeff + "x^" + exponent;
      }
   }  // Term 

   
   
   private ListNode<Term> head;
   
   public P2AdityaTikheSinglyLinkedPolynomial(double[] terms)
   {
      head = new ListNode<Term> (new Term(terms[0], terms[1]), null);
      ListNode <Term> h = head;
      for(int i = 2; i < terms.length; i = i+2) {
         h.setNext(new ListNode <Term> (new Term(terms[i], terms[i+1]), null));
         h = h.getNext();
      }
   }
   
   public P2AdityaTikheSinglyLinkedPolynomial(ListNode<Term> x)
   {
      head = x;
      
   }
   
   //copy constructor
   public P2AdityaTikheSinglyLinkedPolynomial(Polynomial p)
   {    
      P2AdityaTikheSinglyLinkedPolynomial x = (P2AdityaTikheSinglyLinkedPolynomial) p;
      head = x.getHead();
   }
   
   
   
   
   
     
   public ListNode<Term> getHead()
   {
      return head;
   }
   
   public void setHead(ListNode <Term> node)
   {
      head.setValue(node.getValue());
      head.setNext(node.getNext());
   }
   public static int size(ListNode <Term> head) 
   {

      int s = 0;
      ListNode <Term> temp = head; 
      while(temp != null)
      {
         s++;
         temp = temp.getNext();
      }
      
      return s;
   }
   
   /**
   pre-condition: this is not null
   post-condition: returns a double that is the answer if parameter x is plugged in as x
                   in the polynomial
   **/
   public double evaluate(double x)
   {  
      if(x==0) //checks if x = 0 to return constant
         return head.getValue().getCoeff();
      
      double sum = 0.0;
      ListNode<Term> temp = head;
      while(temp!=null)
      {
         sum += temp.getValue().getExponent() * Math.pow(x, temp.getValue().getExponent());
         temp = temp.getNext();
      }
      return sum;
   }
   
   /**
   pre-condition: this and other are not null
   post-condition: returns a SinglyLinkedPolynomial that is the sum of this and other and
                   is in highest to lowest exponent order
   **/   
   public Polynomial plus(Polynomial other)
   {
      ListNode <Term> h = reverse(head);
      P2AdityaTikheSinglyLinkedPolynomial l = new P2AdityaTikheSinglyLinkedPolynomial((P2AdityaTikheSinglyLinkedPolynomial)other);
      ListNode <Term> c = reverse(l.getHead());
      ListNode <Term> temp = null;
      int small;
      if(size(h) > size(c))
      {
         small = size(c);
      }
      else
      {
         small = size(h);
         h = reverse(((P2AdityaTikheSinglyLinkedPolynomial)other).getHead());
         c = reverse(head);
      }
      
      int count = 0;
      while(head != null)
      {
         if(count < small)
         {
            if(c.getValue().getExponent() == h.getValue().getExponent())
            {
               temp = new ListNode(new Term(h.getValue().getCoeff() + c.getValue().getCoeff(), h.getValue().getExponent()), temp);
               h = h.getNext();
               c = c.getNext();
               count++;
            }
            else
            {
               temp = new ListNode(c.getValue(), temp);
               c = c.getNext();
               count++;
            }
         }
         else
         {
            temp = new ListNode(h.getValue(), temp);
            h = h.getNext();
         }
      }
      return new P2AdityaTikheSinglyLinkedPolynomial(temp);
   }
   
   /**
   pre-condition: this and other are not null
   post-condition: returns a SinglyLinkedPolynomial that is the difference of this and other and is
                   in highest to lowest exponent order
   **/   
   public Polynomial minus(Polynomial other)
   {
      ListNode <Term> h = reverse(head);
      P2AdityaTikheSinglyLinkedPolynomial l = new P2AdityaTikheSinglyLinkedPolynomial((P2AdityaTikheSinglyLinkedPolynomial)other);
      ListNode <Term> c = reverse(l.getHead());
      ListNode <Term> temp = null;
      int small;
      if(size(h) > size(c))
      {
         small = size(c);
      }
      else
      {
         small = size(h);
         h = reverse(((P2AdityaTikheSinglyLinkedPolynomial)other).getHead());
         c = reverse(head);
      }
      
      int count = 0;
      while(head != null) //go through each exponent and subtract 
      {
         if(count < small)
         {
            if(c.getValue().getExponent() == h.getValue().getExponent())
            {
               temp = new ListNode(new Term(h.getValue().getCoeff() - c.getValue().getCoeff(), h.getValue().getExponent()), temp);
               h = h.getNext();
               c = c.getNext();
               count++;
            }
            else
            {
               temp = new ListNode(c.getValue(), temp);
               c = c.getNext();
               count++;
            }
         }
         else
         {
            temp = new ListNode(h.getValue(), temp);
            h = h.getNext();
         }
      }
      return new P2AdityaTikheSinglyLinkedPolynomial(temp);
   }
   
   
   /**
   pre-condition: this is not null
   post-condition: returns a SinglyLinkedPolynomial that is the derivative of this and is
                   in highest to lowest exponent order
   **/
   public Polynomial derivative()
   {
      ListNode<Term> temp = head;
      {
         temp.setValue(new Term(temp.getValue().getExponent() * temp.getValue().getCoeff(), temp.getValue().getExponent() -1));
         temp = temp.getNext();
      }  
      return new P2AdityaTikheSinglyLinkedPolynomial(head);
   }

   /**
   pre-condition: this and other are not null
   post-condition: return a new SinglyLinkedPolynomial in highest to lowest degree order
                   that is the product of this and other
   **/   
   public Polynomial multiply(Polynomial other)
   {
      int s = size(head);
      double [] temp = new double [s]; 
      ListNode <Term> o;
      ListNode <Term> i;
      int count = 0;
      
      P2AdityaTikheSinglyLinkedPolynomial ot = (P2AdityaTikheSinglyLinkedPolynomial)other;
      
      for(o = head; o!= null; o = o.getNext()) //nested for loop to foil
      {
         for(i = ot.getHead(); i != null; i = i.getNext())
         {
            temp[count] = o.getValue().getCoeff() * i.getValue().getCoeff();
            count++;
         }
      }
      P2AdityaTikheSinglyLinkedPolynomial n = new P2AdityaTikheSinglyLinkedPolynomial(temp);
      return n;     
   }
   
   /**
   pre-condition: head and node aren't null
   post-condition: node is removed from the list passed
   **/
   public void remove(ListNode<Term> head, ListNode<Term> node)
   {     
      // while loop through and iterate and stuff and then if node is in the head. set next previous equal to next.next
      
      ListNode<Term> temp1; //pointer 1 to loop through
      ListNode<Term> temp2; //pointer 2 to loop through
      Term a = node.getValue(); //if the first is value
      
      if (head.getValue() == a) //checks if its the first value
      {
         head = null; 
      } 
      for (temp1 = head, temp2 = head.getNext(); temp2 != null; temp1 = temp1.getNext(), temp2 = temp2.getNext()) 
      {
         if (temp2.getValue().equals(a))         
            temp1.setNext(temp2.getNext()); //skips over removed node
      }      
   }
   
   /**
   pre-condition: head is not null
   post-condition: return head of the reversed list
   **/   
   public ListNode<Term> reverse(ListNode<Term> head)
   {
      ListNode<Term> temp1 = head;
      ListNode<Term> temp2;
      ListNode<Term> temp3 = null;
      
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
   
   @Override public boolean equals(Object other)
   {
   /*
   logic: try to make both strings, and then use .equals method to see if they same
   */
      String first = toString();
      String last = other.toString();
 

      if(first.equals(last))
         return true;
      else
         return false;
         
   }
   
   public String toString()
   { 
     
      String s = "";

      while(head != null)
      {
         if(head.getNext() != null)
         {
            s += (int)(head.getValue().getCoeff()) + "x^" + (int)(head.getValue().getExponent()) + " + ";   
         }
         else
         {
            s += (int)(head.getValue().getCoeff());
         }
         
         head = head.getNext();
      }
      return s;
   
   }
   public static void main(String[] args)  
   {  
      
      double[] arr = {4,3,3,2,1,0};
      Polynomial p1 = new P2AdityaTikheSinglyLinkedPolynomial(arr);  // 4x^3 + 3x^2 + 1
      System.out.println("p1(x) =     " + p1);
   
      double[] arr2 = {-5,1,-2,0};
      Polynomial p2 = new P2AdityaTikheSinglyLinkedPolynomial(arr2);   // - 5x – 2
      System.out.println("p2(x) =     " + p2);                 // p2(x) = - 5x^1 – 2
      System.out.println("p1 and p2 are the same: " + p1.equals(p2));
      
      double[] arr3 = {-4,1};
      Polynomial p3   = new P2AdityaTikheSinglyLinkedPolynomial(arr3);  // coeff, exp = -4x
      System.out.println("p3(x) =     " + p3);
   
      Polynomial p    = p1.plus(p2).plus(p2);   // 4x^3 + 3x^2 - 10x – 3
      System.out.println("p(x)  =     " + p);       // p(x) = 4x^3 + 3x^2 - 10x^1 – 3
   
      Polynomial p4   = p.minus(p3);   // 4x^3 + 3x^2 - 6x^1 – 3   <====
      System.out.println("p4(x) =     " + p4);
   
      Polynomial p5   = p4.derivative();   // 12x^2 + 6x^1 - 6   <====
      System.out.println("p5(x) =     " + p5);
            
      Polynomial clone = new P2AdityaTikheSinglyLinkedPolynomial(p5);
      System.out.println("clone(x) =     " + clone);
      System.out.println("p5 and clone are the same: " + p5.equals(clone));
      
      Polynomial clone2 = p5;
      System.out.println("clone2(x) =    " + clone2);
      System.out.println("p5 and clone 2 are the same: " + p5.equals(clone2));
      
      Polynomial product = p1.multiply (p2);
      System.out.println("product of p1(x) and p2(x) is     " + product);
      
      System.out.println ("p5(0) = " + p5.evaluate(0));
      System.out.println ("p5(1) = " + p5.evaluate(1));
   }
}  //end of SinglyLinkedPolynomial

