/**
 * Name:   Aditya Tikhe	                        Period: 2
 * Name of the Lab: Polynomial Lab HashMap version
 * Purpose of the Program: To remake the Polynomial lab with a hash map implementation
 * Due Date: 3/28
 * Date Submitted: 3/28 
 * What I learned:I learned a lot through this lab besides understanding how hash maps work. I understand the power
 * 					and functionality of hash maps and how key value pairs truly work. 
 *  
 * How I feel about this lab: I feel really good about this lab because unlike other polynomial labs, this one came
 * 							much easier for me. 
 * `						 I even had time to add an extra method in the polynomial class to calculate the derivative!
 
 * What I wonder: is there an even better data structure to do the polynomial lab with?
 *
 * Most Difficult Method: There was no real difficult method for me because a hashmap really made this lab much easier than
 * 							the other ones. I did have some difficulty about accessing some objects... Lastly, an annoying 
 * 							method for me was the toString() because I feel like there was a much easier way to do it instead
 * 							of bruteforcing it. 
 
 * Credits: Zach Wang helped me a little to understand a little bit of the fundamentals of "this.", but the general 
 * 			principle I figured out myself.
 */
import java.util.*;
public class P2_AdityaTikhe_Polynomial_shell
{
   public static void main(String[] args)
   {
      Polynomial poly = new Polynomial();
      poly.makeTerm(1, -4);
      poly.makeTerm(3, 2);
      poly.makeTerm(0, 2);
      System.out.println(poly.toString());
		double evaluateAt = 2.0;
      System.out.println(" Evaluated at "+ evaluateAt +": " +poly.evaluateAt(evaluateAt));
   	   	
      Polynomial poly2 = new Polynomial();
      poly2.makeTerm(1, -5);
      poly2.makeTerm(4, 2);
      poly2.makeTerm(0, -3);
      poly2.makeTerm(2, 1); 
      System.out.println(poly2.toString());
   	
      System.out.println(poly.add(poly2));
      System.out.println(poly.multiply(poly2));
      System.out.println(" derivative of  " + poly2.toString() + " is: " + poly2.derivative());
   }
}


class Polynomial
{
	private Map <Integer, Integer> map = new TreeMap<Integer, Integer>();
	
	public Polynomial()
	{
		
	}
	public Polynomial(Map <Integer, Integer> m)
	{
		map = m;
	}
	
	public void makeTerm (Integer exp, Integer co)
	{
		map.put(exp, co);
	}
	
	public String toString()
	{
		Map <Integer, Integer> reverseExp = ((TreeMap<Integer, Integer>) map).descendingMap(); // to reverse the degree of the exponent so highest exp comes first
		String s = "";
		for(Integer exp : reverseExp.keySet())
		{
			if(exp == 0)
			{
					s+= " "+ reverseExp.get(exp) + " + ";
			}
			else if(exp == 1)
			{
				if(reverseExp.get(exp) == 1)
					s+= " x + ";
				else
					s += " "+ reverseExp.get(exp) + "x" + " +" ;
			}
			else
			{
				if(reverseExp.get(exp) == 1)
					s+= " " + "x^" + exp + " +" ;
				else
					s += " "+ reverseExp.get(exp) + "x^" + exp + " +" ;
			}
		}
		s = s.substring(0, s.length()-2);
		return s;
	}

	public double evaluateAt(double d)
	{
		double answer = 0.0;
		for(Integer exp : map.keySet())
		{
			answer += Math.pow(d, (double)exp) * map.get(exp);
		}
		
		return answer;
	}
	
	
	public String add (Polynomial p)
	{	
		Map <Integer, Integer> addMap = new TreeMap<Integer, Integer>();
		
		for(Integer exp : (p.map.keySet())) //p.map gets the map implementation of polynomial
		{
			if(this.map.containsKey(exp)) //adds all the like terms
			{
				addMap.put(exp, this.map.get(exp) + p.map.get(exp));
			}
			else //else dumps the rest of the values from param polynomial into sum
			{
				addMap.put(exp, p.map.get(exp));
			}
		}
		
		for(Integer exp : this.map.keySet()) //dumps the rest of the values into sum
		{
			if(!addMap.containsKey(exp))
				addMap.put(exp, this.map.get(exp));
		}
		
		Polynomial pp = new Polynomial(addMap);
		return pp.toString(); 
	}
	
	public String multiply(Polynomial p)
	{
		Map <Integer, Integer> productMap = new TreeMap<Integer, Integer>();
		
		for(Integer exp1 : p.map.keySet())
		{
			for(Integer exp2 : this.map.keySet())
			{
				if(!productMap.containsKey(exp1 + exp2))
				{
					productMap.put(exp1 + exp2, p.map.get(exp1) * this.map.get(exp2)); //adds exponents and multiplies coefficients
				}
				else {
					productMap.put(exp1 + exp2, productMap.get(exp1 + exp2) + p.map.get(exp1) * this.map.get(exp2)); //combines like terms
				}
			}
		}
		Polynomial pp = new Polynomial(productMap);
		return pp.toString(); 
	}
	
	public String derivative()
	{
		Map <Integer, Integer> dMap = new TreeMap<Integer, Integer>();
		
		for(Integer exp : this.map.keySet())
		{
			if(!(exp == 0))
			{
				dMap.put(exp-1, exp * this.map.get(exp));
			}
		}
		Polynomial pp = new Polynomial(dMap);
		return pp.toString(); 
	}
	
}
/*  
expected output
   2x^3 + -4x + 2
   10.0
   2x^4 + x^2 + -5x + -3
   2x^4 + 2x^3 + x^2 + -9x + -1
   4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6
 */

/*
 output:
 2x^3 + -4x + 2 
 Evaluated at 2.0: 10.0
 2x^4 + x^2 + -5x + -3 
 2x^4 + 2x^3 + x^2 + -9x + -1 
 4x^7 + -6x^5 + -6x^4 + -10x^3 + 22x^2 + 2x + -6 
 derivative of   2x^4 + x^2 + -5x + -3  is:  8x^3 + 2x + -5 
 */
