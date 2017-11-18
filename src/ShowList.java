/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * An adapter class used by the theater to organize a collection of customers
 * who attend performances.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;


public class ShowList extends ItemList<Show, String> {
    
    /**
     * This class maintains a collection of Shows
     */
    public ShowList() {

        super();

    } // end ShowList constructor
    
    
    /**
     * This method is used to see if the client has current or upcoming shows.
     * 
     * @param clientID - The client to check on.
     * 
     * @return A boolean true if client has upcoming shows, otherwise false.
     */
    public boolean showsPending(int clientID) {
        
        Iterator<Show> iterator = list.iterator(); 
        Calendar date = new GregorianCalendar(); // get's todays date.
        Show tempShow;
        
        while(iterator.hasNext()) {
            
            tempShow = iterator.next();
            
            if(tempShow.getClientID() == clientID           // Show belongs to client
               && date.before(tempShow.getEndDate())) {     // Shows end date is after today
                    
                return true;    
                
            }
                
        }
        
        return false;
        
    } // end showsPending
    
    
    /**
     * This method is used to see if a requested block of dates is free... not 
     * currently taken by another performance.
     * 
     * @param startDate - The requested initial performance date.
     * @param endDate - The requested final performance date.
     * 
     * @return A boolean true if dates are clear, otherwise false.
     */
    public boolean checkDates(Calendar startDate, Calendar endDate) {
        
        Iterator<Show> iterator = list.iterator();
        Show tempShow;
        
        while(iterator.hasNext()) {
            
            tempShow = iterator.next();
            
            if(!(endDate.before(tempShow.getStartDate())
               || startDate.after(tempShow.getEndDate()))) {
                
                return false;
                
            }
            
        } // end while loop
        
        // Made it through, shouldn't be conflicting dates
        
        return true;
        
    } // end checkDates
    
    
} // end ShowList