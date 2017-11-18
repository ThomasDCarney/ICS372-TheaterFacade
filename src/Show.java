/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Description: Group Project #1, A simple theater management system. <br>
 * Due: 10/16/2015 <br><br>
 * 
 * This class represents a single show. Shows are performed by clients at
 * the theater.
 * 
 * @author Micah Sidley, Mark Scherr, Tom Carney
 * @version 1.0
 * @since 10/03/2015
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Show implements Serializable, Matchable<String> {

    private String      title;
    private Client      performingClient;
    private Calendar    startDate;
    private Calendar    endDate;
    private float       price;

    
    /**
     * Represents a single show.
     * 
     * @param title - The title of the show.
     * @param clientID - The id of the client performing the show.
     * @param startDate - The initial performance date.
     * @param endDate - The final performance date.
     */
    public Show(String name, Client performingClient, 
                Calendar startDate, Calendar endDate, 
                float price) {

        this.title = name;
        this.performingClient = performingClient;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;

    } // end Client constructor
    
    
    /**
     * This method returns the ID of the performing client.
     * 
     * @return - The performing client's ID.
     */
    public int getClientID() {
        
        return performingClient.getID();
        
    } // end getClientID
    
    
    /**
     * This method returns the initial performance date for the show.
     * 
     * @return - The initial performance date of the show.
     */
    public Calendar getStartDate() {
        
        return startDate;
        
    } // end getStartDate
    
    
    /**
     * This method return the final performance date for the show.
     * 
     * @return - The final performance date of the show.
     */
    public Calendar getEndDate() {
        
        return endDate;
        
    } // end getEndDate
    
    
    /**
     * This method will provide the show's price
     * 
     * @return - The show's price.
     */
    public float getPrice() {
        
        return price;
        
    } // end getPrice
    

    /**
     * This method will determine if an element with provided identifier matches this one.
     * 
     * @param identifier - The identifier to check against.
     */
    @Override
    public boolean matches(String identifier) {
        
        return this.title.equals(identifier);
        
    } // end matches
    
    
    /**
     * This method will return a string representation of the show.
     * 
     * @return - A string representation of the show.
     */
    @Override
    public String toString() {
        
        DecimalFormat dollars = new DecimalFormat("#0.00");
        String answer = "Title: " + title + ", Peformed by: " + performingClient.getName()+ 
                        ", Ticket Price: $" + dollars.format(price);
        
        answer += "\n\tStart Date: " + startDate.get(Calendar.YEAR) + "/" +
                                       (startDate.get(Calendar.MONTH) + 1) + "/" +
                                       startDate.get(Calendar.DAY_OF_MONTH);
        
        answer += ", End Date: " + endDate.get(Calendar.YEAR) + "/" +
                                   (endDate.get(Calendar.MONTH) + 1) + "/" +
                                   endDate.get(Calendar.DAY_OF_MONTH);
        
        return  answer;
        
    } // end toString
    
} // end Show