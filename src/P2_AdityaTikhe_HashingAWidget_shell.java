//*********************************************************************************************************************************
//Name:   
//Period:                                                 Date:
//What I learned:
//How I feel about this lab:
//What I wonder:
//***********************************************************************************************************************************
 import java.util.*;
 public class P2_AdityaTikhe_HashingAWidget_shell
 {
    public static void main(String[] args)
    {
       Set<Widget> tSet = new TreeSet<Widget>();
       Set<Widget> hSet = new HashSet<Widget>();
    	
       Widget a = new Widget(2,3);   //same or different?
       Widget b = new Widget(2,3);
       Widget c = new Widget(2,3);
      // c = b;
       
       tSet.add(a); 
       tSet.add(b);
       tSet.add(c);
     
       hSet.add(a); 
       hSet.add(b);
       hSet.add(c); 
       
       System.out.println(a.hashCode()+ " "+b.hashCode() + " " + c.hashCode());
       
       System.out.println("TreeSet:  " + tSet);
       System.out.println("HashSet:  " + hSet);
    }
 }

	/*************************************** 
	Modify the Widget class so that it hashes on its
	values, not on its address.  Be sure that compareTo(),
	equals(Object) and hashCode() agree with each other.
	****************************************/

 class Widget implements Comparable<Widget>
 {
    private int myPounds, myOunces;
    public Widget()
    {
       myPounds = myOunces = 0;
    }
    public Widget(int x)
    {
       myPounds = x;
       myOunces = 0;
    }
    public Widget(int x, int y)
    {
       myPounds = x;
       myOunces = y;
    }
    public Widget(Widget arg)
    {
       myPounds = arg.getPounds();
       myOunces = arg.getOunces();
    }
    public int getPounds()
    {
       return myPounds;
    }
    public int getOunces()
    {
       return myOunces;
    }
    public void setPounds(int x)
    {
       myPounds = x;
    }
    public void setOunces(int x)
    {
       myOunces = x;
    }
    
    
 	public int compareTo(Widget w)
 	{
 		return getPounds() + getOunces()  - w.getPounds() - w.getOunces();
 	}
 	public boolean equals(Widget arg)  //shallow?
 	{
 		if(arg.getPounds() == getPounds() && arg.getOunces() == getOunces())
 			return true;
 		else
 			return false;
 	}
 	public String toString()
 	{
 		return getPounds() + " pounds and " + getOunces() + " ounces"; 
 	}
 	public boolean equals(Object arg) //deep? + creds: stack overflow
 	{
 		if(arg instanceof Widget)
 			return true;
 		else
 			return false;
 		
 	}
    public int hashCode()
    {
    	return toString().hashCode();
    }
 }
