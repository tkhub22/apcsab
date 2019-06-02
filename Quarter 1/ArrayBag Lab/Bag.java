public interface Bag
{
   boolean add(Object item);
   boolean remove(Object item);
   boolean contains(Object item);
   int numItems();
   Object grab();
   Object[] toArray();
}
