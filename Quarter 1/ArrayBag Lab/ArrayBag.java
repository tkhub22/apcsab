/*
Aditya Tikhe P:1
*/
public class ArrayBag implements Bag
{
   private Object [] items;
   private int numItems;
   
   //constructors
   public ArrayBag ( int num)
   {
      numItems = num;
      items = new Object [numItems];
   }
   
   //getters and setters
   public int getNumItems()
   {
      return numItems;
   }
   public void setNumItems(int n)
   {
      numItems = n;
   }
   public Object getItems()
   {
      return items;
   }
   public void setItems(Object [] i)
   {
      items = i;
   }
   
   //methods
   public boolean add(Object item)
   {
      for(int i = 0; i < items.length; i++)  //loops through array
         {
            if(items[i] == null)                //checks if index is empty
               {
                  items[i] = item;              //add item to list
                  return true;
               }
               
         }
         return false;
   }
   
   public boolean remove(Object item)
   {
      for(int i = 0; i < items.length; i++)
         {
            if(items[i] == item) 
               {
                  items[i] = null;
                  return true;
               }
         }
         return false;
   }
   
   public boolean contains(Object item)
   {
      for(int i = 0; i < items.length; i++)
         {
            if(items[i] == item)      
               {
                  return true;
               }
         }
         return false;
   }
   
   public int numItems()
   {
      int count = 0;
      for(int i = 0; i < items.length; i++)
         {
            if(items[i] != null)
               count++;
         }
         return count;
   }
 
   public Object grab()
   {
      return items[(int)Math.random() * items.length];
   }
   
   public Object[] toArray()
   {
      Object [] newArray = new Object [items.length];
      for(int i = 0; i < items.length; i++)
         {
              newArray[i] = items[i];
         }
      return newArray;
   }
   
   public String toString()
   {
      return "items: " + items + "\nNumber of Items: " + numItems;  
   }
   
   
   //driver
   public static void main (String [] args)
   {
      ArrayBag a = new ArrayBag(5);
      a.add(2);
      a.add("hello");
      a.add(1);
      a.add(1);
      
      //checks if there is hello 
      a.remove("hello"); 
      System.out.println("is there \"hello\" " + a.contains("hello"));
      
      //adds "hello" back in
      a.add("hello");
      
      System.out.println(a.numItems());
      
      System.out.println(a.grab());
      
      System.out.println("checking if toArray works"); 
      Object [] aa = a.toArray();
      for(int i = 0; i < aa.length; i ++)
         {
            System.out.println(aa[i]);
         }
   }
}

/*
output

 is there "hello" false
 4
 2
 checking if toArray works
 2
 hello
 1
 1
 null
*/