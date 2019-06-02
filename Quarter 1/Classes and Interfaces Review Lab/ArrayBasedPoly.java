/*
Aditya Tikhe
9/8/18
What I learned:
a. I learned to work more with interfaces
b. I also advanced my understanding with objects and their polymorphism aspect.
c. I brushed up on using Java, and creating logic.
*/
public class ArrayBasedPoly implements Polynomial
{
   private double [] polynomial;
   
   //constructors
   public ArrayBasedPoly(double [] po)
   {
      polynomial = po;
   }
   public ArrayBasedPoly (int x, int y)
   {
      polynomial = new double[y + 1];
      polynomial[y] = x;
   }
   
   //methods
   public double [] getPolynomial ()
   {
      return polynomial;
   }
   
   public int getDegree()
   {
      return polynomial.length;
   }

   public double getCoefficients(int x)
   {
      return polynomial[x];
   }
   
   public double evaluate (int x)
   {
      double sum = 0.0;
      if(x == 0)
         {
            return polynomial[0];
         }
      for(int i = 0; i< polynomial.length; i++)
      {
         
         sum = getCoefficients(i)* Math.pow(x, polynomial.length - i);
      }
      return sum;
   }

   public ArrayBasedPoly add(ArrayBasedPoly other)
   {
      ArrayBasedPoly poly;
      double [] a;
      double [] ax;
      int shortArray;
      
      if(other.getPolynomial().length > polynomial.length)
      {   
         a = new double[other.getPolynomial().length]; //length of long array
         ax = other.getPolynomial(); //values of long array
         shortArray = polynomial.length;
      }
      else
      {
         a = new double[polynomial.length]; //Checks which array is bigger then sets new array to that size
         ax = polynomial;
         shortArray = other.getPolynomial().length;
      }
        
      for(int i = 0; i < shortArray; i++) //Adds both arrays upto the short length
      {
         a[i] = polynomial[i] + other.getPolynomial()[i];
      }
      
      for(int x = shortArray; x < a.length; x++) //adds remaining part of the long polynomial to the new array
      {
         a[x] = ax[x];
      }
      poly = new ArrayBasedPoly(a);
      return poly; 
   }
   
  
   public ArrayBasedPoly subtract(ArrayBasedPoly other) //4x^3 + 3x^2 - 6x^1 – 3
   {
      ArrayBasedPoly poly;
      double [] a;
      double [] ax;
      int shortArray;
      
      if(other.getPolynomial().length > polynomial.length)
      {   
         a = new double[other.getPolynomial().length]; //length of long array
         ax = other.getPolynomial(); //values of long array
         shortArray = polynomial.length;
      }
      else
      {
         a = new double[polynomial.length]; //Checks which array is bigger then sets new array to that size
         ax = polynomial;
         shortArray = other.getPolynomial().length;
      }
        
      for(int i = 0; i < shortArray; i++) //subtracts both arrays upto the short length
      {
         a[i] = polynomial[i] - other.getPolynomial()[i];
      }
      
      for(int x = shortArray; x < a.length; x++) //adds remaining part of the long polynomial to the new array
      {
         a[x] = ax[x];
      }
      poly = new ArrayBasedPoly(a);
      return poly;
   }
   
   
   
   
   public ArrayBasedPoly derivative () 
   {
      ArrayBasedPoly poly;
      double [] dArray = new double [polynomial.length - 1]; 
      int temp = polynomial.length - 1;
      int exp = polynomial.length - 1;
      
      for(int i = dArray.length - 1; i >= 0; i --)
      {
         dArray[i] = polynomial[temp--] * exp--;
      }
      poly = new ArrayBasedPoly(dArray);
      return poly;
   }
   
   public String toString()
   {
      String s = "";
      for(int i = polynomial.length-1; i>= 0; i--)
      {
         if(polynomial[i] != 0)
            if(i==0)
               s += polynomial[i];
            else
               s += " " + polynomial[i] + "x^" + i + " +";
         
      }
      return s;
   }
}