
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ItemList<T extends Matchable<K>, K> implements Serializable {
    
    protected List<T> list;
    
    /**
     * This class maintains a collection of elements.
     */
    public ItemList() {
        
        list  = new LinkedList<T>();
        
    } // end ItemList constructor
    
    
    /**
     * This method will add an element to the collection.
     * 
     * @param element - The element to add.
     * 
     * @return - A boolean true if element was added successfully, false if not.
     */
    public boolean insertElement(T element) {

        return list.add(element);
        
    } // end insertClient
    
    
    /**
     * This method will remove an element from the collection.
     * 
     * @param identifier - The unique identifier of the element to remove.
     * 
     * @return - A boolean true if element was removed successfully, false if not.
     */
    public boolean removeElement(K identifier) {
        
        Iterator<T> iterator = list.iterator();
        
        while (iterator.hasNext()) {

            if (iterator.next().matches(identifier)) {

                iterator.remove();
                return true;

            }
            
        }
        
        return false;
        
    } // end removeElement
  
    
    /**
     * This method is used to search to see if a specific element already
     * exists.
     * 
     * @param identifier - The unique identifier of the element in question.
     * 
     * @return - A boolean true if the element was found, false if not.
     */
    public boolean searchElements(K identifier) {
        
        Iterator<T> iterator = list.iterator(); 
        T tempElement;
        
        while(iterator.hasNext()) {
            
            tempElement = iterator.next();
            
            if(tempElement.matches(identifier)) {
                
                return true;    
                
            }
                
        }
        
        return false;
        
    } // end searchElements
    
    
    /**
     * This method is used to search the list and if found, return a reference
     * to the specified element.
     * 
     * @param identifier - The elements unique identifier.
     * 
     * @return - If found, a reference to the element, otherwise null.
     */
    public T getElement(K identifier) {
        
        Iterator<T> iterator = list.iterator();
        T tempElement;
        
        while(iterator.hasNext()) {
            
            tempElement = iterator.next();
            
            if(tempElement.matches(identifier)) {
                
                return tempElement;
                
            }
            
        }
        
        return null;
        
    } // end findElement
    
    
    /**
     * This method return an iterator for the ItemList.
     * 
     * @return - An Iterator. 
     */
    public Iterator<T> getItemIterator() {
        
        return list.iterator();
        
    } // end getListIterator
    
    
    /**
     * This method will return the size of the list.
     * 
     * @return - An int representing the number of elements stored.
     */
    public int size() {
        
        return list.size();
        
    }// end size
    
    
} // end ItemList