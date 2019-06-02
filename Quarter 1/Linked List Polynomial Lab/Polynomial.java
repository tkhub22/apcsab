public interface Polynomial
{   
   Polynomial plus(Polynomial other);
   
   Polynomial minus(Polynomial other);
   
   Polynomial multiply(Polynomial other);
   
   Polynomial derivative ();
   
   double evaluate(double x);

}