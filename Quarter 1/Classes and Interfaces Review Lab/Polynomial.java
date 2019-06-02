public interface Polynomial
{  
   //methods
   public int getDegree();

   public double getCoefficients(int x);
   
   public double evaluate (int x);

   public ArrayBasedPoly add(ArrayBasedPoly other);
   
   public ArrayBasedPoly subtract(ArrayBasedPoly other);
   
   public ArrayBasedPoly derivative (); 

}