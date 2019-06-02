/*
Aditya Tikhe P:2
9/3/18
What I learned:
a. I reviewed how to do basic Java functions like writing a constructor, getters, and various methods.
b. I also reviewed debugging, and how to troubleshoot my program.
*/
public class RationalNum
{
   private int x;
   private int y;
   
   //constructors
   public RationalNum (int numer, int denom)
   {
      x = numer;
      y = denom;
   }
   
   //getter methods
   public int getX ()
   {
      return x;
   }
   
   public int getY()
   {
      return y;
   }

   //simplify
   public RationalNum simplify ()
   {
   
      int gcd = getGCD(x, y);
      return new RationalNum(x/gcd, y/gcd);
      
   }
   
   public static int getGCD(int a, int b)
   {
   
      if (b==0) 
         return a;
      else
         return getGCD(b, a % b);
   }
   
   //methods
   public RationalNum add (RationalNum other)
   {
      int numerX = other.getX();
      int denomY = other.getY();
      int numerator = y * numerX + x * denomY;
      int denominator = y * denomY;
      RationalNum ans = new RationalNum(numerator, denominator);
      return ans.simplify();     
   }
   
   public RationalNum subtract (RationalNum other)
   {
      int numerX = other.getX();
      int denomY = other.getY();
      int numerator = (x * denomY)-(y * numerX);
      int denominator = y * denomY;
      RationalNum ans = new RationalNum(numerator, denominator);
      return ans.simplify();    
   }
   
   public RationalNum multiply (RationalNum other)
   {
      int numerX = other.getX();
      int denomY = other.getY();
      int numerator = numerX * x;
      int denominator = y * denomY;
      RationalNum ans = new RationalNum(numerator, denominator);
      return ans.simplify();     
   }
   
   public RationalNum divide (RationalNum other)
   {
      int numerX = other.getX();
      int denomY = other.getY();
      int numerator = x * denomY;
      int denominator =  numerX * y;
      RationalNum ans = new RationalNum(numerator, denominator);
      return ans.simplify();     
   }
   
   public void setY (int denom)
   {
      y = denom;
   }
   
   public boolean equals (RationalNum other)
   {
      
      int numerX = other.getX();
      int denomY = other.getY();
      
      boolean flag = false;
      if (numerX == denomY)
         flag = true;
      return flag; 
   }
   
   public String toString()
   {
      return "" + x +"/" + y;
   }
   
   public static void main(String [] args) {
      RationalNum r1 = new RationalNum(52, 36);
      System.out.println("r1 = " + r1);
      System.out.println("r1 simplified = " + r1.simplify());
      
      RationalNum r2 = new RationalNum(7, 39);
      r2.setY(0);
      System.out.println("r2 = " + r2 + "\n");
      r2.setY(14);
   
      System.out.println("r2 = " + r2 + "\n");
      System.out.println("r1 + r2: " + r1.add(r2));
      System.out.println("r1 - r2: " + r1.subtract(r2));
      System.out.println("r1 * r2: " + r1.multiply(r2));
      System.out.println("r1 / r2: " + r1.divide(r2));
      System.out.println("r1 equals r2: " + r1.equals(r2));
      
   }

}
/*
 r1 = 52/36
 r1 simplified = 13/9
 r2 = 7/0
 
 r2 = 7/14
 
 r1 + r2: 35/18
 r1 - r2: 17/18
 r1 * r2: 13/18
 r1 / r2: 26/9
 r1 equals r2: false
*/