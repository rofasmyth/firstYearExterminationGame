import java.util.*;
public class LinkedListQueue
{
   private LinkedList q;
	
   /**Constructs an empty queue that uses a linked list.*/
   public LinkedListQueue(){
      q = new LinkedList();
   }  
	
   /**Adds an item to the tail of the queue.
	  @param o the item to add*/
   public void add(Object o) //enqueue - or add to end of queue
   {
      q.addLast(o);
   }
   
   /**Removes an item from the head of the queue.
	@return the removed item  */
   public Object remove() { //deque - remove from start of q
      return q.removeFirst();
   }
 	
   /**Gets the number of items in the queue.
	 	@return the size*/
   public int size()    {
      return q.size();
   }
  
  //look at the object at start of queue but doesn't remove it  
   public Object peek(){
      return q.peek();
   }
   
   //Method to shuffle the list.
   public void shuffle()
   {
      Collections.shuffle(q);
   }
   
   //Method to sort the list.
   public void sort()
   {
      Collections.sort(q);
   }
}  